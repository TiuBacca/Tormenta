package com.baccarin.tormenta.resource;

import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baccarin.tormenta.service.EscudoService;
import com.baccarin.tormenta.vo.item.EscudoResponse;
import com.baccarin.tormenta.vo.item.ItemFiltro;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("tormenta/escudo")
@RequiredArgsConstructor
public class EscudoResource {

	private final EscudoService escudoService;

	@GetMapping(path = "buscaLista")
	public ResponseEntity<List<EscudoResponse>> buscaListaTodasEscudos() throws Exception {
		List<EscudoResponse> escudos = escudoService.buscaListaTodasEscudos();
		if (Objects.nonNull(escudos)) {
			return new ResponseEntity<List<EscudoResponse>>(escudos, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping(path = "buscaLista/byFiltro")
	public ResponseEntity<List<EscudoResponse>> buscaListaEscudosByFiltro(@RequestBody ItemFiltro filtro)
			throws Exception {
		List<EscudoResponse> escudos = escudoService.buscaListaEscudosByFiltro(filtro);
		if (Objects.nonNull(escudos)) {
			return new ResponseEntity<List<EscudoResponse>>(escudos, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
