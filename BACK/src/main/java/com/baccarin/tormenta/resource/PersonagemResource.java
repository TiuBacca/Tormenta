package com.baccarin.tormenta.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("tormenta/personagem")
@RequiredArgsConstructor
public class PersonagemResource {

//	private final PersonagemService service;
//
//	@PostMapping(path = "salvar")
//	public ResponseEntity<?> salvarPersonagem(@RequestBody PersonagemRequest request) throws Exception {
//		try {
//			service.salvarPersonagem(request);
//			return new ResponseEntity<>(HttpStatus.OK);
//		} catch (Exception e) {
//			throw new PersistenceException("Erro ao tentar salvar novo personagem.");
//		}
//		
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
//	
//
//	@PostMapping(path = "buscaLista/byFiltro")
//	public ResponseEntity<List<PersonagemResponse>> buscarListaPersonagensByFiltro(
//			@RequestBody PersonagemRequest request) throws Exception {
//		List<PersonagemResponse> personagens = service.buscaListaPersonagemByFiltro(request);
//		if (Objects.nonNull(personagens) && personagens.isEmpty()) {
//			return new ResponseEntity<List<PersonagemResponse>>(personagens, HttpStatus.NO_CONTENT);
//		}
//		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//	}


}
