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

import com.baccarin.tormenta.exception.RegistroComRelacionamentoException;
import com.baccarin.tormenta.exception.RegistroDuplicadoException;
import com.baccarin.tormenta.exception.RegistroIncompletoException;
import com.baccarin.tormenta.exception.RegistroNaoEncontradoException;
import com.baccarin.tormenta.service.TendenciaService;
import com.baccarin.tormenta.vo.ResponseGenerico;
import com.baccarin.tormenta.vo.tendencia.TendenciaRequest;
import com.baccarin.tormenta.vo.tendencia.TendenciaResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("tormenta/tendencia")
@RequiredArgsConstructor
public class TendenciaResource {
	
	private final TendenciaService tendenciaService;

	@PostMapping(path = "salvar")
	public ResponseEntity<ResponseGenerico> salvarTendencia(@RequestBody TendenciaRequest request) {
		try {
			tendenciaService.salvarClasse(request);
			return new ResponseEntity<ResponseGenerico>(new ResponseGenerico("Tendência salva com sucesso."),
					HttpStatus.OK);
		} catch (RegistroIncompletoException | RegistroDuplicadoException e) {
			return new ResponseEntity<ResponseGenerico>(new ResponseGenerico(e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<ResponseGenerico>(new ResponseGenerico("Erro ao salvar tendência."),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(path = "excluir")
	public ResponseEntity<ResponseGenerico> exxcluirTendencia(@RequestBody TendenciaRequest request) {
		try {
			tendenciaService.excluirClasse(request);
			return new ResponseEntity<ResponseGenerico>(new ResponseGenerico("Tendência excluida com sucesso."),
					HttpStatus.OK);
		} catch (RegistroNaoEncontradoException | RegistroComRelacionamentoException e) {
			return new ResponseEntity<ResponseGenerico>(new ResponseGenerico(e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<ResponseGenerico>(new ResponseGenerico("Erro ao excluir tendência."),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(path = "buscaLista")
	public ResponseEntity<List<TendenciaResponse>> buscarListaTendencias() throws Exception {
		List<TendenciaResponse> tendencias = tendenciaService.buscarListaTendencias();
		if (Objects.nonNull(tendencias) && !tendencias.isEmpty()) {
			return new ResponseEntity<List<TendenciaResponse>>(tendencias, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
