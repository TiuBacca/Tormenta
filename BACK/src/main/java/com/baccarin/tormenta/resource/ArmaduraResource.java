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

import com.baccarin.tormenta.service.ArmaduraService;
import com.baccarin.tormenta.vo.item.ArmaduraResponse;
import com.baccarin.tormenta.vo.item.ItemFiltro;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("armadura")
@RequiredArgsConstructor
public class ArmaduraResource {

	private final ArmaduraService armaduraService;

	@GetMapping(path = "buscaLista")
	public ResponseEntity<List<ArmaduraResponse>> buscaListaTodasArmaduras() throws Exception {
		List<ArmaduraResponse> armaduras = armaduraService.buscaListaTodasArmaduras();
		if (Objects.nonNull(armaduras)) {
			return new ResponseEntity<List<ArmaduraResponse>>(armaduras, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping(path = "buscaLista/byFiltro")
	public ResponseEntity<List<ArmaduraResponse>> buscaListaArmadurasByFiltro(@RequestBody ItemFiltro filtro) throws Exception {
		List<ArmaduraResponse> armaduras = armaduraService.buscaListaArmadurasByFiltro(filtro);
		if (Objects.nonNull(armaduras)) {
			return new ResponseEntity<List<ArmaduraResponse>>(armaduras, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
