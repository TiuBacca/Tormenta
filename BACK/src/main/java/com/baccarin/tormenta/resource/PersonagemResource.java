package com.baccarin.tormenta.resource;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baccarin.tormenta.enums.Sexo;
import com.baccarin.tormenta.exception.RegistroComRelacionamentoException;
import com.baccarin.tormenta.exception.RegistroIncompletoException;
import com.baccarin.tormenta.exception.RegistroNaoEncontradoException;
import com.baccarin.tormenta.service.PersonagemService;
import com.baccarin.tormenta.vo.ResponseGenerico;
import com.baccarin.tormenta.vo.personagem.InfoCorpoCorpoResponse;
import com.baccarin.tormenta.vo.personagem.PersonagemPericiaResponse;
import com.baccarin.tormenta.vo.personagem.PersonagemRequest;
import com.baccarin.tormenta.vo.personagem.PersonagemResponse;
import com.baccarin.tormenta.vo.usuario.UsuarioRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("tormenta/personagem")
@RequiredArgsConstructor
public class PersonagemResource {

	private final PersonagemService personagemService;

	@PostMapping(path = "salvar")
	public ResponseEntity<ResponseGenerico> salvarPersonagem(@RequestBody PersonagemRequest request) throws Exception {
		try {
			personagemService.salvarPersonagem(request);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (RegistroNaoEncontradoException | RegistroIncompletoException e) {
			return new ResponseEntity<ResponseGenerico>(new ResponseGenerico(e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<ResponseGenerico>(new ResponseGenerico("Erro ao salvar personagem."),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping(path = "excluir")
	public ResponseEntity<ResponseGenerico> excluirPersonagem(@RequestBody PersonagemRequest request) {
		try {
			personagemService.excluirPersonagem(request);
			return new ResponseEntity<ResponseGenerico>(new ResponseGenerico("Personagem excluido com sucesso."),
					HttpStatus.OK);
		} catch (RegistroComRelacionamentoException | RegistroNaoEncontradoException | RegistroIncompletoException e) {
			return new ResponseEntity<ResponseGenerico>(new ResponseGenerico(e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<ResponseGenerico>(new ResponseGenerico("Erro ao excluir personagem."),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(path = "buscaLista/byFiltro")
	public ResponseEntity<List<PersonagemResponse>> buscarListaPersonagensByFiltro(
			@RequestBody PersonagemFiltro request) throws Exception {
		List<PersonagemResponse> personagens = personagemService.buscaListaPersonagemByFiltro(request);
		if (Objects.nonNull(personagens) && !personagens.isEmpty()) {
			return new ResponseEntity<List<PersonagemResponse>>(personagens, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping(path = "buscaLista/byEmail")
	public ResponseEntity<List<PersonagemResponse>> buscarListaPersonagensByEmail(@RequestBody UsuarioRequest request)
			throws Exception {
		List<PersonagemResponse> personagens = personagemService.buscarListaPersonagensByEmail(request);
		if (Objects.nonNull(personagens) && !personagens.isEmpty()) {
			return new ResponseEntity<List<PersonagemResponse>>(personagens, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping(path = "buscaLista/sexo")
	public ResponseEntity<List<String>> buscaListaSexo() {
		List<String> lista = Sexo.obterDescricoesSexo();
		return new ResponseEntity<List<String>>(lista, HttpStatus.OK);
	}

	// TODO
	@PostMapping(path = "infoCorpoACorpo")
	public ResponseEntity<InfoCorpoCorpoResponse> informacoesCorpoACorpo(@RequestBody PersonagemRequest request)
			throws Exception {
		return new ResponseEntity<InfoCorpoCorpoResponse>(new InfoCorpoCorpoResponse(1l, 2, 1, 3, 0), HttpStatus.OK);
	}

	// TODO
	@PostMapping(path = "infoDistancia")
	public ResponseEntity<InfoCorpoCorpoResponse> informacoesDistancia(@RequestBody PersonagemRequest request)
			throws Exception {
		return new ResponseEntity<InfoCorpoCorpoResponse>(new InfoCorpoCorpoResponse(1l, 1, 4, 2, 1), HttpStatus.OK);
	}

	@PostMapping(path = "pericias")
	public ResponseEntity<List<PersonagemPericiaResponse>> buscarListaPericias(@RequestBody PersonagemRequest request)
			throws Exception {
		return new ResponseEntity<List<PersonagemPericiaResponse>>(Collections.emptyList(), HttpStatus.OK);
	}

}
