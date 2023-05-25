package com.baccarin.tormenta.resource;

import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baccarin.tormenta.exception.RegistroComRelacionamentoException;
import com.baccarin.tormenta.exception.RegistroDuplicadoException;
import com.baccarin.tormenta.exception.RegistroIncompletoException;
import com.baccarin.tormenta.exception.RegistroNaoEncontradoException;
import com.baccarin.tormenta.service.RacaService;
import com.baccarin.tormenta.vo.ResponseGenerico;
import com.baccarin.tormenta.vo.raca.RacaFiltro;
import com.baccarin.tormenta.vo.raca.RacaRequest;
import com.baccarin.tormenta.vo.raca.RacaResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("tormenta/raca")
@RequiredArgsConstructor
public class RacaResource {

	private final RacaService racaService;

	@PostMapping(path = "salvar")
	public ResponseEntity<ResponseGenerico> salvarRaca(@RequestBody RacaRequest request) {
		try {
			racaService.salvarRaca(request);
			return new ResponseEntity<ResponseGenerico>(new ResponseGenerico("Raça salva com sucesso."), HttpStatus.OK);
		} catch (RegistroIncompletoException | RegistroDuplicadoException e) {
			return new ResponseEntity<ResponseGenerico>(new ResponseGenerico(e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<ResponseGenerico>(new ResponseGenerico("Erro ao salvar raça."),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(path = "excluir")
	public ResponseEntity<ResponseGenerico> excluirRaca(@RequestBody RacaRequest request) {
		try {
			racaService.excluirRaca(request);
			return new ResponseEntity<ResponseGenerico>(new ResponseGenerico("Raça excluida com sucesso."),
					HttpStatus.OK);
		} catch (RegistroNaoEncontradoException | RegistroComRelacionamentoException e) {
			return new ResponseEntity<ResponseGenerico>(new ResponseGenerico(e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<ResponseGenerico>(new ResponseGenerico("Erro ao excluir raça."),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(path = "buscaLista/byFiltro")
	public ResponseEntity<List<RacaResponse>> buscaListaRacaByFiltro(@RequestBody RacaFiltro request) throws Exception {
		List<RacaResponse> racas = racaService.buscaListaRacaByFiltro(request);
		if (Objects.nonNull(racas) && !racas.isEmpty()) {
			return new ResponseEntity<List<RacaResponse>>(racas, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
