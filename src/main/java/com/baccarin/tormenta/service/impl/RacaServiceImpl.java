package com.baccarin.tormenta.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baccarin.tormenta.domain.Raca;
import com.baccarin.tormenta.exception.RegistroComRelacionamentoException;
import com.baccarin.tormenta.exception.RegistroNaoEncontradoException;
import com.baccarin.tormenta.repository.RacaRepository;
import com.baccarin.tormenta.service.PersonagemService;
import com.baccarin.tormenta.service.RacaService;
import com.baccarin.tormenta.vo.personagem.PersonagemRequest;
import com.baccarin.tormenta.vo.personagem.PersonagemResponse;
import com.baccarin.tormenta.vo.raca.RacaRequest;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class RacaServiceImpl implements RacaService {

	private final RacaRepository repository;
	private final PersonagemService personagemService;

	@Override
	public List<Raca> buscarListaTodasRacas() {
		return repository.findAll();
	}

	@Override
	public void excluirRaca(RacaRequest request) throws Exception {

		PersonagemRequest filtro = new PersonagemRequest();
		filtro.setIdClasse(request.getIdRaca());
		List<PersonagemResponse> personagensComRacaInformada = personagemService.buscaListaPersonagemByFiltro(filtro);

		if (personagensComRacaInformada.isEmpty()) {
			Raca raca = repository.findById(request.getIdRaca()).orElseThrow(
					() -> new RegistroNaoEncontradoException("Não foi encontrada a raça com código informado."));
			repository.delete(raca);
		} else {
			throw new RegistroComRelacionamentoException(
					"Impissível excluir a raça pois existem persongens relacionados a ela.");
		}

	}

}