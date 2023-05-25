package com.baccarin.tormenta.service.impl;

import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.baccarin.tormenta.domain.Raca;
import com.baccarin.tormenta.exception.RegistroComRelacionamentoException;
import com.baccarin.tormenta.exception.RegistroDuplicadoException;
import com.baccarin.tormenta.exception.RegistroIncompletoException;
import com.baccarin.tormenta.exception.RegistroNaoEncontradoException;
import com.baccarin.tormenta.repository.RacaRepository;
import com.baccarin.tormenta.resource.PersonagemFiltro;
import com.baccarin.tormenta.service.PersonagemService;
import com.baccarin.tormenta.service.RacaService;
import com.baccarin.tormenta.util.Util;
import com.baccarin.tormenta.vo.personagem.PersonagemResponse;
import com.baccarin.tormenta.vo.raca.RacaFiltro;
import com.baccarin.tormenta.vo.raca.RacaRequest;
import com.baccarin.tormenta.vo.raca.RacaResponse;

import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class RacaServiceImpl implements RacaService {

	private final RacaRepository racaRepository;
	private final PersonagemService personagemService;

	private final Util util;

	@Override
	public void salvarRaca(RacaRequest request) throws Exception {
		validaSalvarRaca(request);
		racaRepository.save(Raca.builder().nome(request.getNome()).build());
	}

	@Override
	public List<RacaResponse> buscarListaTodasRacas() {
		return racaRepository.buscarListaTodasRacas();
	}

	@Override
	public void excluirRaca(RacaRequest request) throws Exception {
		validarExcluirRaca(request);
		racaRepository.deleteById(request.getId());
	}

	public List<RacaResponse> buscaListaRacaByFiltro(RacaFiltro filtro) {

		StringBuilder sb = new StringBuilder();
		sb.append("select new com.baccarin.tormenta.vo.raca.RacaResponse( r.id, r.nome, r.descricao ) from Raca r where r.id > 0 ");

		if (StringUtils.isNotBlank(filtro.getNome())) {
			sb.append(" AND UPPER(r.nome) ilike UPPER(:nome) ");
		}

		sb.append(" ORDER BY r.nome ASC , r.descricao ASC ");
		Query query = util.getEntityManager().createQuery(sb.toString());

		if (StringUtils.isNotBlank(filtro.getNome())) {
			query.setParameter("nome", "%".concat(filtro.getNome()).concat("%").toUpperCase());
		}
		return query.getResultList();

	}

	private void validaSalvarRaca(RacaRequest request) throws Exception {
		if (Objects.isNull(request.getNome()) || request.getNome().isBlank()) {
			throw new RegistroIncompletoException("Atributo nome faltando para salvar raça.");
		} else {
			List<RacaResponse> lista = buscaListaRacaByFiltro(RacaFiltro.builder().nome(request.getNome()).build());
			if (Objects.nonNull(lista) && !lista.isEmpty()) {
				throw new RegistroDuplicadoException("Raça ja cadastrada.");
			}
		}
	}

	private void validarExcluirRaca(RacaRequest request) throws Exception {

		if (Objects.nonNull(request.getId())) {
			PersonagemFiltro filtro = new PersonagemFiltro();
			filtro.getRacas().add(RacaRequest.builder().id(request.getId()).build());
			List<PersonagemResponse> personagensComRacaInformada = personagemService
					.buscaListaPersonagemByFiltro(filtro);
			if (personagensComRacaInformada.isEmpty()) {
				racaRepository.findById(request.getId())
						.orElseThrow(() -> new RegistroNaoEncontradoException("Raça não encontrada."));
			} else {
				throw new RegistroComRelacionamentoException(
						"Impissível excluir a raça pois existem persongens relacionados a ela.");
			}
		} else {
			throw new RegistroIncompletoException("Atributo id faltando para excluir raça.");
		}

	}

}