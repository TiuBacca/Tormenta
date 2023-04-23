package com.baccarin.tormenta.resource;

import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baccarin.tormenta.service.PersonagemService;
import com.baccarin.tormenta.service.UsuarioService;
import com.baccarin.tormenta.vo.personagem.PersonagemRequest;
import com.baccarin.tormenta.vo.personagem.PersonagemResponse;
import com.baccarin.tormenta.vo.usuario.UsuarioRequest;
import com.baccarin.tormenta.vo.usuario.UsuarioResponse;

import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("tormenta/usuario")
@RequiredArgsConstructor
public class UsuarioResource {

	private final UsuarioService usuarioService;

	@PostMapping(path = "buscaLista/byFiltro")
	public ResponseEntity<List<UsuarioResponse>> buscarListaUsuariosByFiltro(@RequestBody UsuarioRequest request)
			throws Exception {
		List<UsuarioResponse> usuarios = usuarioService.buscarListaUsuariosByFiltro(request);
		if (Objects.nonNull(usuarios) && usuarios.isEmpty()) {
			return new ResponseEntity<List<UsuarioResponse>>(usuarios, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping(path = "excluir")
	public ResponseEntity<?> removerUsuario(@RequestBody UsuarioRequest request) {
		try {
			usuarioService.removerUsuario(request);
			return new ResponseEntity<String>("Usuário excluido com sucesso.", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(path = "salvar")
	public ResponseEntity<?> salvarUsuario(@RequestBody UsuarioRequest request) throws Exception {
		try {
			usuarioService.salvarUsuario(request);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			throw new PersistenceException("Erro ao tentar salvar novo usuário.");
		}

	}

}
