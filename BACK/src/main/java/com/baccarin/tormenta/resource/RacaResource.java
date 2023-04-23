package com.baccarin.tormenta.resource;

import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baccarin.tormenta.service.RacaService;
import com.baccarin.tormenta.vo.personagem.PersonagemRequest;
import com.baccarin.tormenta.vo.personagem.PersonagemResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("tormenta/raca")
@RequiredArgsConstructor
public class RacaResource {

	private final RacaService service;
	

//	@PostMapping(path = "buscaLista")
//	public ResponseEntity<List<PersonagemResponse>> buscarListaPersonagensByFiltro(
//			@RequestBody PersonagemRequest request) throws Exception {
//		List<PersonagemResponse> personagens = service.buscaListaPersonagemByFiltro(request);
//		if (Objects.nonNull(personagens) && personagens.isEmpty()) {
//			return new ResponseEntity<List<PersonagemResponse>>(personagens, HttpStatus.NO_CONTENT);
//		}
//		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//	}
//
//	@PostMapping(path = "excluir")
//	public ResponseEntity<?> excluirPersonagem(@RequestBody PersonagemRequest request) {
//		try {
//			service.excluirPersonagem(request);
//			return new ResponseEntity<String>("Personagem excluido com sucesso.", HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
	
}
