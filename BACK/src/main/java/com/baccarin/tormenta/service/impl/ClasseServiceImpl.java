package com.baccarin.tormenta.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.baccarin.tormenta.domain.Classe;
import com.baccarin.tormenta.domain.HabilidadeClasse;
import com.baccarin.tormenta.exception.RegistroDuplicadoException;
import com.baccarin.tormenta.exception.RegistroIncompletoException;
import com.baccarin.tormenta.exception.RegistroNaoEncontradoException;
import com.baccarin.tormenta.repository.ClasseRepository;
import com.baccarin.tormenta.repository.HabilidadeClasseRepository;
import com.baccarin.tormenta.service.ClasseService;
import com.baccarin.tormenta.util.Util;
import com.baccarin.tormenta.vo.classe.ClasseRequest;
import com.baccarin.tormenta.vo.classe.ClasseResponse;
import com.baccarin.tormenta.vo.habilidade.HabilidadeClasseResponse;

import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ClasseServiceImpl implements ClasseService {

	private final ClasseRepository classeRepository;
	private final HabilidadeClasseRepository habilidadeClasseRepository;
	private final Util util;

	@Override
	public void salvarClasse(ClasseRequest request) throws Exception {
		validaSalvarClasse(request);
		classeRepository.save(new Classe(request));
	}

	@Override
	public void excluirClasse(ClasseRequest request) throws Exception {
		validaExcluirClasse(request);
		classeRepository.deleteById(request.getId());
	}

	private void validaSalvarClasse(ClasseRequest request) throws Exception {

		if (Objects.nonNull(request.getId()) && request.getId() != 0) {
			Classe classeBanco = classeRepository.findById(request.getId())
					.orElseThrow(() -> new RegistroNaoEncontradoException("Classe não encontrada."));
			if (StringUtils.isNotBlank(request.getNome()) && !request.getNome().equals(classeBanco.getNome())) {
				List<Classe> classeMesmoNome = classeRepository.findByNome(request.getNome());
				if (!classeMesmoNome.isEmpty()) {
					throw new RegistroDuplicadoException("Ja existe uma classe com este nome cadastrada.");
				}

			}

		} else {
			if (StringUtils.isBlank(request.getNome())) {
				throw new RegistroIncompletoException("Atributo nome faltando para salvar classe.");
			} else {
				List<Classe> classeMesmoNome = classeRepository.findByNome(request.getNome());
				if (!classeMesmoNome.isEmpty()) {
					throw new RegistroDuplicadoException("Ja existe uma classe com este nome cadastrada.");
				}

			}
			if (StringUtils.isBlank(request.getDescricao())) {
				throw new RegistroIncompletoException("Atributo descrição faltando para salvar classe.");
			}
		}

	}

	private void validaExcluirClasse(ClasseRequest request) throws Exception {
		if (Objects.isNull(request.getId())) {
			throw new RegistroIncompletoException("Atributo id está faltando.");
		} else {
			classeRepository.findById(request.getId())
					.orElseThrow(() -> new RegistroNaoEncontradoException("Classe não encontrada."));
		}
	}

	@Override
	public List<ClasseResponse> buscarListaClassesByFiltro(ClasseRequest request) throws Exception {
		StringBuilder sb = new StringBuilder();

		sb.append(" select new com.baccarin.tormenta.vo.classe.ClasseResponse ( c.id, c.nome , c.descricao  , COALESCE( c.pontosBaseVida, 0),  COALESCE (c.pontosBaseAtaque,0) ) "
				+ " from Classe c where c.id > 0");

		if (StringUtils.isNotBlank(request.getNome())) {
			sb.append(" AND UPPER(c.nome) like UPPER(:texto) OR UPPER(c.descricao) like UPPER(:texto) ");
		}

		sb.append(" ORDER BY c.nome ASC, c.descricao ASC");

		TypedQuery<ClasseResponse> query = util.getEntityManager().createQuery(sb.toString(), ClasseResponse.class);

		if (StringUtils.isNotBlank(request.getNome())) {
			query.setParameter("texto", "%".concat(request.getNome()).concat("%").toUpperCase());
		}

		return query.getResultList();
	}

	@Override
	public List<HabilidadeClasseResponse> buscaListaHabilidadesByClasse(ClasseRequest request) throws Exception {
		if (Objects.nonNull(request.getId()) && request.getId() != 0) {
			Classe classe = classeRepository.findById(request.getId())
					.orElseThrow(() -> new RegistroNaoEncontradoException("Classe não encontrada."));
			List<HabilidadeClasse> habilidades = habilidadeClasseRepository.findByClasse(classe);
			List<HabilidadeClasseResponse> habilidadesResponse = habilidades.stream().map(habilidade -> {
				return new HabilidadeClasseResponse(habilidade);
			}).collect(Collectors.toList());
			return habilidadesResponse;
		}

		return Collections.emptyList();
	}
}
