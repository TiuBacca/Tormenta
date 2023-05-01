package com.baccarin.tormenta.resource;

import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baccarin.tormenta.exception.RegistroDuplicadoException;
import com.baccarin.tormenta.exception.RegistroIncompletoException;
import com.baccarin.tormenta.exception.RegistroNaoEncontradoException;
import com.baccarin.tormenta.exception.RegistrosAssociadosException;
import com.baccarin.tormenta.service.UsuarioService;
import com.baccarin.tormenta.vo.ResponseGenerico;
import com.baccarin.tormenta.vo.usuario.UsuarioRequest;
import com.baccarin.tormenta.vo.usuario.UsuarioResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("tormenta/usuario")
@RequiredArgsConstructor
public class UsuarioResource {

	private final UsuarioService usuarioService;

	@PostMapping(path = "salvar")
	public ResponseEntity<ResponseGenerico> salvarUsuario(@RequestBody UsuarioRequest request) throws Exception {
		try {
			usuarioService.salvarUsuario(request);
			return new ResponseEntity<ResponseGenerico>(new ResponseGenerico("Usuário salvo com sucesso."),
					HttpStatus.OK);
		} catch (RegistroIncompletoException | RegistroDuplicadoException | RegistroNaoEncontradoException e) {
			return new ResponseEntity<ResponseGenerico>(new ResponseGenerico(e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<ResponseGenerico>(new ResponseGenerico("Erro ao tentar salvar novo usuário."),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(path = "excluir")
	public ResponseEntity<ResponseGenerico> removerUsuario(@RequestBody UsuarioRequest request) {
		try {
			usuarioService.removerUsuario(request);
			return new ResponseEntity<ResponseGenerico>(new ResponseGenerico("Usuário excluido com sucesso."),
					HttpStatus.OK);
		} catch (RegistroNaoEncontradoException | RegistrosAssociadosException e) {
			return new ResponseEntity<ResponseGenerico>(new ResponseGenerico(e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<ResponseGenerico>(new ResponseGenerico("Erro ao excluir usuário."),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(path = "buscaLista/byFiltro")
	public ResponseEntity<List<UsuarioResponse>> buscarListaUsuariosByFiltro(@RequestBody UsuarioRequest request)
			throws Exception {
		List<UsuarioResponse> usuarios = usuarioService.buscarListaUsuariosByFiltro(request);
		if (Objects.nonNull(usuarios) && !usuarios.isEmpty()) {
			return new ResponseEntity<List<UsuarioResponse>>(usuarios, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
