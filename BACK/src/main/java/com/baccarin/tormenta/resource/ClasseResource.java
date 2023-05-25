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
import com.baccarin.tormenta.service.ClasseService;
import com.baccarin.tormenta.vo.ResponseGenerico;
import com.baccarin.tormenta.vo.classe.ClasseRequest;
import com.baccarin.tormenta.vo.classe.ClasseResponse;
import com.baccarin.tormenta.vo.habilidade.HabilidadeClasseResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("tormenta/classe")
@RequiredArgsConstructor
public class ClasseResource {

	private final ClasseService classeService;

	@PostMapping(path = "salvar")
	public ResponseEntity<ResponseGenerico> salvarClasse(@RequestBody ClasseRequest request) {
		try {
			classeService.salvarClasse(request);
			return new ResponseEntity<ResponseGenerico>(new ResponseGenerico("Classe salva com sucesso."),
					HttpStatus.OK);
		} catch (RegistroIncompletoException | RegistroDuplicadoException e) {
			return new ResponseEntity<ResponseGenerico>(new ResponseGenerico(e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<ResponseGenerico>(new ResponseGenerico("Erro ao salvar classe."),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(path = "excluir")
	public ResponseEntity<ResponseGenerico> excluirClasse(@RequestBody ClasseRequest request) {
		try {
			classeService.excluirClasse(request);
			return new ResponseEntity<ResponseGenerico>(new ResponseGenerico("Classe excluida com sucesso."),
					HttpStatus.OK);
		} catch (RegistroNaoEncontradoException | RegistroComRelacionamentoException e) {
			return new ResponseEntity<ResponseGenerico>(new ResponseGenerico(e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<ResponseGenerico>(new ResponseGenerico("Erro ao excluir classe."),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(path = "buscaLista/byFiltro")
	public ResponseEntity<List<ClasseResponse>> buscarListaClasses(@RequestBody ClasseRequest request)
			throws Exception {
		List<ClasseResponse> classes = classeService.buscarListaClassesByFiltro(request);
		if (Objects.nonNull(classes) && !classes.isEmpty()) {
			return new ResponseEntity<List<ClasseResponse>>(classes, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping(path = "buscaLista/habilidades")
	public ResponseEntity<List<HabilidadeClasseResponse>> buscaListaHabilidadesByClasse(
			@RequestBody ClasseRequest request) throws Exception {
		List<HabilidadeClasseResponse> classes = classeService.buscaListaHabilidadesByClasse(request);
		if (Objects.nonNull(classes) && !classes.isEmpty()) {
			return new ResponseEntity<List<HabilidadeClasseResponse>>(classes, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
