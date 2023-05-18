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

import com.baccarin.tormenta.service.ArmaService;
import com.baccarin.tormenta.vo.item.ArmaResponse;
import com.baccarin.tormenta.vo.item.ItemFiltro;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("arma")
@RequiredArgsConstructor
public class ArmaResource {

	private final ArmaService armaService;

	@GetMapping(path = "buscaLista")
	public ResponseEntity<List<ArmaResponse>> buscaListaTodasArmas() throws Exception {
		List<ArmaResponse> armas = armaService.buscaListaTodasArmas();
		if (Objects.nonNull(armas)) {
			return new ResponseEntity<List<ArmaResponse>>(armas, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping(path = "buscaLista/byFiltro")
	public ResponseEntity<List<ArmaResponse>> buscaListaArmasByFiltro(@RequestBody ItemFiltro filtro) throws Exception {
		List<ArmaResponse> armas = armaService.buscaListaArmasByFiltro(filtro);
		if (Objects.nonNull(armas)) {
			return new ResponseEntity<List<ArmaResponse>>(armas, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
