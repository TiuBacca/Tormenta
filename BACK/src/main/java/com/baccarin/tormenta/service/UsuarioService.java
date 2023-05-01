package com.baccarin.tormenta.service;

import java.util.List;

import com.baccarin.tormenta.vo.usuario.UsuarioRequest;
import com.baccarin.tormenta.vo.usuario.UsuarioResponse;

public interface UsuarioService {

	List<UsuarioResponse> buscarListaUsuariosByFiltro(UsuarioRequest request);

	void removerUsuario(UsuarioRequest request) throws Exception;

	void salvarUsuario(UsuarioRequest request) throws Exception;

}
