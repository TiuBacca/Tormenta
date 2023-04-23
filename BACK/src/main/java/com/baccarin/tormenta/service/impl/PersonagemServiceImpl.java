package com.baccarin.tormenta.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.baccarin.tormenta.domain.Classe;
import com.baccarin.tormenta.domain.ClasseArmadura;
import com.baccarin.tormenta.domain.Habilidade;
import com.baccarin.tormenta.domain.Personagem;
import com.baccarin.tormenta.domain.Raca;
import com.baccarin.tormenta.domain.Tendencia;
import com.baccarin.tormenta.domain.Vida;
import com.baccarin.tormenta.enums.Sexo;
import com.baccarin.tormenta.exception.RegistroNaoEncontradoException;
import com.baccarin.tormenta.repository.ClasseArmaduraRepository;
import com.baccarin.tormenta.repository.ClasseRepository;
import com.baccarin.tormenta.repository.HabilidadeRepository;
import com.baccarin.tormenta.repository.PersonagemRepository;
import com.baccarin.tormenta.repository.RacaRepository;
import com.baccarin.tormenta.repository.TendenciaRepository;
import com.baccarin.tormenta.service.PersonagemService;
import com.baccarin.tormenta.util.Util;
import com.baccarin.tormenta.vo.personagem.PersonagemRequest;
import com.baccarin.tormenta.vo.personagem.PersonagemResponse;

import jakarta.persistence.Column;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PersonagemServiceImpl implements PersonagemService {

	private final PersonagemRepository repository;
	private final RacaRepository racaRepository;
	private final ClasseRepository classeRepository;
	private final TendenciaRepository tendenciaRepository;
	private final HabilidadeRepository habilidadeRepository;
	private final ClasseArmaduraRepository classeArmaduraRepository;

	private final Util util;

	@Override
	public List<PersonagemResponse> buscaListaTodosPersonagem() {
		return repository.findListTodosPersonagens();
	}

	@Override
	public List<PersonagemResponse> buscaListaPersonagemByFiltro(PersonagemRequest request) throws Exception {

		StringBuilder sb = new StringBuilder();

		sb.append(" select new com.baccarin.tormenta.vo.personagem.PersonagemResponse"
				+ " ( p.id, p.nome, classe.nome, raca.nome) from Personagem p join p.classe classe join p.raca raca where p.id > 0 ");

		if (Objects.nonNull(request.getIdClasse())) {
			sb.append(" AND classe.id = :idClasse");
		}

		if (Objects.nonNull(request.getIdRaca())) {
			sb.append(" AND raca.id = :idRaca");
		}

		if (Objects.nonNull(request.getIdUsuario()) && request.getIdUsuario() != 0) {
			sb.append(" AND p.usuario.id = :usuario ");
		}

		TypedQuery<PersonagemResponse> query = util.getEntityManager().createQuery(sb.toString(),
				PersonagemResponse.class);

		if (Objects.nonNull(request.getIdRaca())) {
			query.setParameter("idRaca", request.getIdRaca());
		}
		if (Objects.nonNull(request.getIdClasse())) {
			query.setParameter("idClasse", request.getIdClasse());
		}
		if (Objects.nonNull(request.getIdUsuario()) && request.getIdUsuario() != 0) {
			query.setParameter("usuario", request.getIdUsuario());

		}

		return query.getResultList();
	}

	@Override
	public void excluirPersonagem(PersonagemRequest request) throws Exception {
		Personagem personagem = repository.findById(request.getId())
				.orElseThrow(() -> new RegistroNaoEncontradoException("Personagem não encontrado!"));
		repository.delete(personagem);
	}

	@Override
	public void salvarPersonagem(PersonagemRequest request) throws RegistroNaoEncontradoException {
		Personagem personagem = repository.findById(request.getId()).orElse(new Personagem());

		if (Objects.nonNull(request.getNome()) && !request.getNome().isBlank()) {
			personagem.setNome(request.getNome());
		}

		if (Objects.nonNull(request.getSexo())) {
			Sexo sexo = Sexo.valueOf(request.getSexo());
			personagem.setSexo(sexo);
		}

		if (Objects.nonNull(request.getIdRaca()) && request.getIdRaca() != 0) {
			Raca raca = racaRepository.findById(request.getIdRaca())
					.orElseThrow(() -> new RegistroNaoEncontradoException("Raça não encontrada!"));
			personagem.setRaca(raca);
		}

		if (Objects.nonNull(request.getIdClasse()) && request.getIdClasse() != 0) {
			Classe classe = classeRepository.findById(request.getIdClasse())
					.orElseThrow(() -> new RegistroNaoEncontradoException("Classe não encontrada!"));
			personagem.setClasse(classe);
		}

		if (Objects.nonNull(request.getIdTendencia()) && request.getIdTendencia() != 0) {
			Tendencia tendencia = tendenciaRepository.findById(request.getIdTendencia())
					.orElseThrow(() -> new RegistroNaoEncontradoException("Tendencia não encontrada!"));
			personagem.setTendencia(tendencia);
		}

		if (Objects.nonNull(request.getVida())) {
			Vida novaVida = new Vida(request.getVida().getPontosTotais(), request.getVida().getPontosAtuais());
			personagem.setVida(novaVida);
		}

		if ((Objects.nonNull(request.getFortitude()) && request.getFortitude() != 0)) {
			personagem.setFortitude(request.getFortitude());
		}

		if ((Objects.nonNull(request.getReflexo()) && request.getReflexo() != 0)) {
			personagem.setReflexo(request.getReflexo());
		}

		if ((Objects.nonNull(request.getVontade()) && request.getVontade() != 0)) {
			personagem.setVontade(request.getVontade());
		}

		if ((Objects.nonNull(request.getNivel()) && request.getNivel() != 0)) {
			personagem.setNivel(request.getNivel());
		}

		if ((Objects.nonNull(request.getTamanho()) && request.getTamanho() != 0)) {
			personagem.setTamanho(request.getTamanho());
		}

		if (Objects.nonNull(request.getHabilidade()) && request.getHabilidade().getIdHabilidade() != 0) {
			Habilidade habilidade = habilidadeRepository.findById(request.getHabilidade().getIdHabilidade())
					.orElse(new Habilidade());
			if (Objects.nonNull(request.getHabilidade().getForca()) && request.getHabilidade().getForca() != 0) {
				habilidade.setForca(request.getHabilidade().getForca());
			}

			if (Objects.nonNull(request.getHabilidade().getDestreza()) && request.getHabilidade().getDestreza() != 0) {
				habilidade.setDestreza(request.getHabilidade().getDestreza());
			}

			if (Objects.nonNull(request.getHabilidade().getConstituicao())
					&& request.getHabilidade().getConstituicao() != 0) {
				habilidade.setConstituicao(request.getHabilidade().getConstituicao());
			}

			if (Objects.nonNull(request.getHabilidade().getInteligencia())
					&& request.getHabilidade().getInteligencia() != 0) {
				habilidade.setInteligencia(request.getHabilidade().getInteligencia());
			}

			if (Objects.nonNull(request.getHabilidade().getSabedoria())
					&& request.getHabilidade().getSabedoria() != 0) {
				habilidade.setSabedoria(request.getHabilidade().getSabedoria());
			}

			if (Objects.nonNull(request.getHabilidade().getCarisma()) && request.getHabilidade().getSabedoria() != 0) {
				habilidade.setCarisma(request.getHabilidade().getCarisma());
			}
			personagem.setHabilidade(habilidade);
		}

		if (Objects.nonNull(request.getClasseArmadura()) && request.getClasseArmadura().getIdClasseArmadura() != 0) {
			ClasseArmadura classeArmadura = classeArmaduraRepository
					.findById(request.getClasseArmadura().getIdClasseArmadura()).orElse(new ClasseArmadura());

			if (Objects.nonNull(request.getClasseArmadura().getBonusArmadura())
					&& request.getClasseArmadura().getBonusArmadura() != 0) {
				classeArmadura.setBonusArmadura(request.getClasseArmadura().getBonusArmadura());
			}

			if (Objects.nonNull(request.getClasseArmadura().getBonusEscudo())
					&& request.getClasseArmadura().getBonusEscudo() != 0) {
				classeArmadura.setBonusEscudo(request.getClasseArmadura().getBonusEscudo());
			}

			if (Objects.nonNull(request.getClasseArmadura().getOutros())
					&& request.getClasseArmadura().getOutros() != 0) {
				classeArmadura.setOutros(request.getClasseArmadura().getOutros());
			}

			if (Objects.nonNull(request.getClasseArmadura().getTotal())
					&& request.getClasseArmadura().getTotal() != 0) {
				classeArmadura.setTotal(request.getClasseArmadura().getTotal());
			}
			personagem.setClasseArmadura(classeArmadura);
		}

		repository.save(personagem);

	}

}
