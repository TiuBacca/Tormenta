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
import com.baccarin.tormenta.service.HabilidadeService;
import com.baccarin.tormenta.vo.ResponseGenerico;
import com.baccarin.tormenta.vo.habilidade.HabilidadeRequest;
import com.baccarin.tormenta.vo.habilidade.HabilidadeResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("tormenta/habilidade")
@RequiredArgsConstructor
public class HabilidadeResource {
	
	private final HabilidadeService habilidadeService;

	@PostMapping(path = "salvar")
	public ResponseEntity<ResponseGenerico> salvarHabilidade(@RequestBody HabilidadeRequest request) {
		try {
			habilidadeService.salvarHabilidade(request);
			return new ResponseEntity<ResponseGenerico>(new ResponseGenerico("Habilidade salva com sucesso."),
					HttpStatus.OK);
		} catch (RegistroIncompletoException | RegistroDuplicadoException e) {
			return new ResponseEntity<ResponseGenerico>(new ResponseGenerico(e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<ResponseGenerico>(new ResponseGenerico("Erro ao salvar habilidade."),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(path = "excluir")
	public ResponseEntity<ResponseGenerico> excluirHabilidade(@RequestBody HabilidadeRequest request) {
		try {
			habilidadeService.excluirHabilidade(request);
			return new ResponseEntity<ResponseGenerico>(new ResponseGenerico("Habilidade excluida com sucesso."),
					HttpStatus.OK);
		} catch (RegistroNaoEncontradoException | RegistroComRelacionamentoException e) {
			return new ResponseEntity<ResponseGenerico>(new ResponseGenerico(e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<ResponseGenerico>(new ResponseGenerico("Erro ao excluir habilidade."),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(path = "buscaLista")
	public ResponseEntity<List<HabilidadeResponse>> buscarListaHabilidades() throws Exception {
		List<HabilidadeResponse> tendencias = habilidadeService.buscarListaHabilidades();
		if (Objects.nonNull(tendencias) && !tendencias.isEmpty()) {
			return new ResponseEntity<List<HabilidadeResponse>>(tendencias, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
