package com.baccarin.tormenta.service;

import java.util.List;

import com.baccarin.tormenta.resource.PersonagemFiltro;
import com.baccarin.tormenta.vo.personagem.PersonagemRequest;
import com.baccarin.tormenta.vo.personagem.PersonagemResponse;
import com.baccarin.tormenta.vo.usuario.UsuarioRequest;

public interface PersonagemService {

	List<PersonagemResponse> buscaListaTodosPersonagem();

	void excluirPersonagem(PersonagemRequest request) throws Exception;

	void salvarPersonagem(PersonagemRequest request) throws Exception;

	List<PersonagemResponse> buscaListaPersonagemByFiltro(PersonagemFiltro request) throws Exception;

	List<PersonagemResponse> buscarListaPersonagensByEmail(UsuarioRequest request) throws Exception;

}
