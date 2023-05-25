package com.baccarin.tormenta.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.baccarin.tormenta.domain.ClasseArmadura;
import com.baccarin.tormenta.domain.Habilidade;
import com.baccarin.tormenta.domain.Personagem;
import com.baccarin.tormenta.enums.Sexo;
import com.baccarin.tormenta.exception.RegistroIncompletoException;
import com.baccarin.tormenta.exception.RegistroNaoEncontradoException;
import com.baccarin.tormenta.repository.ClasseArmaduraRepository;
import com.baccarin.tormenta.repository.ClasseRepository;
import com.baccarin.tormenta.repository.HabilidadeRepository;
import com.baccarin.tormenta.repository.PersonagemRepository;
import com.baccarin.tormenta.repository.RacaRepository;
import com.baccarin.tormenta.repository.TendenciaRepository;
import com.baccarin.tormenta.repository.UsuarioRepository;
import com.baccarin.tormenta.resource.PersonagemFiltro;
import com.baccarin.tormenta.service.PersonagemService;
import com.baccarin.tormenta.util.Util;
import com.baccarin.tormenta.vo.classe.ClasseRequest;
import com.baccarin.tormenta.vo.personagem.PersonagemRequest;
import com.baccarin.tormenta.vo.personagem.PersonagemResponse;
import com.baccarin.tormenta.vo.raca.RacaRequest;
import com.baccarin.tormenta.vo.usuario.UsuarioRequest;

import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PersonagemServiceImpl implements PersonagemService {

	private final PersonagemRepository personagemRepository;
	private final RacaRepository racaRepository;
	private final ClasseRepository classeRepository;
	private final TendenciaRepository tendenciaRepository;
	private final HabilidadeRepository habilidadeRepository;
	private final ClasseArmaduraRepository classeArmaduraRepository;
	private final UsuarioRepository usuarioRepository;

	private final Util util;

	@Override
	public List<PersonagemResponse> buscaListaTodosPersonagem() {
		return personagemRepository.findListTodosPersonagens();
	}

	@Override
	public List<PersonagemResponse> buscaListaPersonagemByFiltro(PersonagemFiltro request) throws Exception {

		StringBuilder sb = new StringBuilder();

		sb.append(" select new com.baccarin.tormenta.vo.personagem.PersonagemResponse"
				+ " ( p.id, p.nome, classe.nome, raca.nome, p.fortitude, p.reflexo,"
				+ " p.vontade, p.nivel , p.vidaAtual, p.vidaTotal, p.sexo) "
				+ " from Personagem p join p.classe classe join p.raca raca where p.id > 0 ");

		if (Objects.nonNull(request.getClasses()) && !request.getClasses().isEmpty()) {
			sb.append(" AND classe.id in ( :idsClasse ) ");
		}

		if (Objects.nonNull(request.getRacas()) && !request.getRacas().isEmpty()) {
			sb.append(" AND raca.id in ( :idsRacas )");
		}

		if (Objects.nonNull(request.getUsuarios()) && !request.getUsuarios().isEmpty()) {
			sb.append(" AND p.usuario.id in ( :idsUsuarios )");
		}

		if (Objects.nonNull(request.getId()) && request.getId() != 0) {
			sb.append(" AND p.id = :idPersonagem ");
		}

		
		
		TypedQuery<PersonagemResponse> query = util.getEntityManager().createQuery(sb.toString(),
				PersonagemResponse.class);

		if (Objects.nonNull(request.getId()) && request.getId() != 0) {
			query.setParameter("idPersonagem", request.getId());
		}

		if (Objects.nonNull(request.getClasses()) && !request.getClasses().isEmpty()) {
			query.setParameter("idsClasse",
					request.getClasses().stream().map(ClasseRequest::getId).collect(Collectors.toList()));
		}

		if (Objects.nonNull(request.getRacas()) && !request.getRacas().isEmpty()) {
			query.setParameter("idsRacas",
					request.getRacas().stream().map(RacaRequest::getId).collect(Collectors.toList()));
		}

		if (Objects.nonNull(request.getUsuarios()) && !request.getUsuarios().isEmpty()) {
			query.setParameter("idsUsuarios",
					request.getUsuarios().stream().map(UsuarioRequest::getId).collect(Collectors.toList()));

		}
		
		
		

		return query.getResultList();
	}

	@Override
	public void excluirPersonagem(PersonagemRequest request) throws Exception {
		Personagem personagem = personagemRepository.findById(request.getId())
				.orElseThrow(() -> new RegistroNaoEncontradoException("Personagem não encontrado!"));
		personagemRepository.delete(personagem);
	}

	@Override
	public void salvarPersonagem(PersonagemRequest request) throws Exception {
		validaSalvar(request);

		Personagem personagem = new Personagem();
		if (Objects.nonNull(request.getId())) {
			personagem = personagemRepository.findById(request.getId())
					.orElseThrow(() -> new RegistroNaoEncontradoException("Personagem não encontrado."));
		}

		if (Objects.nonNull(request.getNome()) && !request.getNome().isBlank()) {
			personagem.setNome(request.getNome());
		}

		if (Objects.nonNull(request.getSexo())) {
			Sexo sexo = Sexo.valueOf(request.getSexo());
			personagem.setSexo(sexo);
		}

		if (Objects.nonNull(request.getIdRaca()) && request.getIdRaca() != 0) {
			personagem.setRaca(racaRepository.findById(request.getIdRaca()).get());
		}

		if (Objects.nonNull(request.getIdClasse()) && request.getIdClasse() != 0) {
			personagem.setClasse(classeRepository.findById(request.getIdClasse()).get());
		}

		if (Objects.nonNull(request.getIdTendencia()) && request.getIdTendencia() != 0) {
			personagem.setTendencia(tendenciaRepository.findById(request.getIdTendencia()).get());
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

		if (Objects.nonNull(request.getHabilidade())) {
			Habilidade habilidade = new Habilidade();
			if (Objects.nonNull(request.getHabilidade().getId())) {
				habilidade = habilidadeRepository.findById(request.getHabilidade().getId())
						.orElseThrow(() -> new RegistroNaoEncontradoException("Habilidade não encontrada."));
			}

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

		if (Objects.nonNull(request.getClasseArmadura())) {

			ClasseArmadura classeArmadura = new ClasseArmadura();

			if (Objects.nonNull(request.getClasseArmadura().getId())) {
				classeArmadura = classeArmaduraRepository.findById(request.getClasseArmadura().getId())
						.orElseThrow(() -> new RegistroNaoEncontradoException("Classe Armadura não encontrada."));
			}

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

		personagem.setUsuario(usuarioRepository.findById(request.getIdUsuario()).get());

		personagemRepository.save(personagem);

	}

	@Override
	public List<PersonagemResponse> buscarListaPersonagensByEmail(UsuarioRequest request) throws Exception {

		if (StringUtils.isBlank(request.getEmail())) {
			throw new RegistroIncompletoException("Necessário informar o e-mail do usuário.");
		}

		return personagemRepository.findByUsuarioEmail(Util.criptografar(request.getEmail()));
	}

	private void validaSalvar(PersonagemRequest request) throws Exception {
		if (Objects.nonNull(request.getId())) {
			personagemRepository.findById(request.getId())
					.orElseThrow(() -> new RegistroNaoEncontradoException("Personagem não encontrado."));
		} else {
			if (StringUtils.isBlank(request.getNome())) {
				throw new RegistroIncompletoException("Atributo nome faltando para salvar personagem.");
			}

			if (Objects.isNull(request.getSexo())) {
				throw new RegistroIncompletoException("Atributo sexo faltando para salvar personagem.");
			}

			if (Objects.nonNull(request.getIdRaca()) && request.getIdRaca() != 0) {
				racaRepository.findById(request.getIdRaca())
						.orElseThrow(() -> new RegistroNaoEncontradoException("Raça não encontrada!"));
			} else {
				throw new RegistroIncompletoException("Atributo raça faltando para salvar personagem.");
			}

			if (Objects.nonNull(request.getIdClasse()) && request.getIdClasse() != 0) {
				classeRepository.findById(request.getIdClasse())
						.orElseThrow(() -> new RegistroNaoEncontradoException("Classe não encontrada!"));
			} else {
				throw new RegistroIncompletoException("Atributo classe faltando para salvar personagem.");
			}

			if (Objects.nonNull(request.getIdTendencia()) && request.getIdTendencia() != 0) {
				tendenciaRepository.findById(request.getIdTendencia())
						.orElseThrow(() -> new RegistroNaoEncontradoException("Tendencia não encontrada!"));
			} else {
				throw new RegistroIncompletoException("Atributo tendência faltando para salvar personagem.");
			}

			if (Objects.nonNull(request.getHabilidade()) && Objects.nonNull(request.getHabilidade().getId())) {
				habilidadeRepository.findById(request.getHabilidade().getId())
						.orElseThrow(() -> new RegistroNaoEncontradoException("Habilidade não encontrada."));
			}

			if (Objects.nonNull(request.getClasseArmadura()) && Objects.nonNull(request.getClasseArmadura().getId())) {
				classeArmaduraRepository.findById(request.getClasseArmadura().getId())
						.orElseThrow(() -> new RegistroNaoEncontradoException("Classe Armadura não encontrada."));

			}

			if (Objects.nonNull(request.getIdUsuario())) {
				usuarioRepository.findById(request.getIdUsuario())
						.orElseThrow(() -> new RegistroNaoEncontradoException("Usuário não encontrado."));
			} else {
				throw new RegistroIncompletoException("Atributo usuário faltando para salvar personagem.");
			}
		}

	}

}
