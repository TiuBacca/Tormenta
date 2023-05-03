package com.baccarin.tormenta.service;

import java.util.List;

import com.baccarin.tormenta.exception.RegistroNaoEncontradoException;
import com.baccarin.tormenta.resource.PersonagemFiltro;
import com.baccarin.tormenta.vo.personagem.PersonagemRequest;
import com.baccarin.tormenta.vo.personagem.PersonagemResponse;

public interface PersonagemService {

	List<PersonagemResponse> buscaListaTodosPersonagem();

	void excluirPersonagem(PersonagemRequest request) throws Exception;

	void salvarPersonagem(PersonagemRequest request) throws Exception;

	List<PersonagemResponse> buscaListaPersonagemByFiltro(PersonagemFiltro request) throws Exception;

}
