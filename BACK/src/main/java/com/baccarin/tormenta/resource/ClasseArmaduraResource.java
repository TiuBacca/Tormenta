package com.baccarin.tormenta.resource;

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
import com.baccarin.tormenta.exception.RegistrosAssociadosException;
import com.baccarin.tormenta.service.ClasseArmaduraService;
import com.baccarin.tormenta.vo.ResponseGenerico;
import com.baccarin.tormenta.vo.classeArmadura.ClasseArmaduraRequest;
import com.baccarin.tormenta.vo.classeArmadura.ClasseArmaduraResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("tormenta/classeArmadura")
@RequiredArgsConstructor
public class ClasseArmaduraResource {

	private final ClasseArmaduraService classeArmaduraService;

	@PostMapping(path = "salvar")
	public ResponseEntity<ResponseGenerico> salvarClasseArmadura(@RequestBody ClasseArmaduraRequest request) {
		try {
			classeArmaduraService.salvarClasseArmadura(request);
			return new ResponseEntity<ResponseGenerico>(new ResponseGenerico("Classe Armadura salva com sucesso."),
					HttpStatus.OK);
		} catch (RegistroIncompletoException | RegistroDuplicadoException e) {
			return new ResponseEntity<ResponseGenerico>(new ResponseGenerico(e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<ResponseGenerico>(new ResponseGenerico("Erro ao salvar classe armadura."),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(path = "excluir")
	public ResponseEntity<ResponseGenerico> excluirClasseArmadura(@RequestBody ClasseArmaduraRequest request) {
		try {
			classeArmaduraService.excluirClasseArmadura(request);
			return new ResponseEntity<ResponseGenerico>(new ResponseGenerico("Classe Armadura excluida com sucesso."),
					HttpStatus.OK);
		} catch (RegistrosAssociadosException | RegistroNaoEncontradoException | RegistroComRelacionamentoException e) {
			return new ResponseEntity<ResponseGenerico>(new ResponseGenerico(e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<ResponseGenerico>(new ResponseGenerico("Erro ao excluir classe armadura."),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(path = "busca/byPersonagem")
	public ResponseEntity<ClasseArmaduraResponse> buscarClasseArmaduraByPersonagemId(@RequestBody ClasseArmaduraRequest request) throws Exception {
		ClasseArmaduraResponse classe = classeArmaduraService.buscarClasseArmaduraByPersonagemId(request);
		if (Objects.nonNull(classe)) {
			return new ResponseEntity<ClasseArmaduraResponse>(classe, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
