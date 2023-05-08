package com.baccarin.tormenta.resource;

import javax.security.auth.login.LoginException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baccarin.tormenta.exception.RegistroIncompletoException;
import com.baccarin.tormenta.exception.RegistroNaoEncontradoException;
import com.baccarin.tormenta.service.LoginService;
import com.baccarin.tormenta.vo.login.LoginRequest;
import com.baccarin.tormenta.vo.login.LoginResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("tormenta/acesso")
@RequiredArgsConstructor
public class LoginResource {

	private final LoginService loginService;

	@PostMapping(path = "login")
	public ResponseEntity<LoginResponse> acessarSistema(@RequestBody LoginRequest request) throws Exception {
		try {
			LoginResponse response = loginService.acessarSistema(request);
			return new ResponseEntity<LoginResponse>(response, HttpStatus.OK);
		} catch (RegistroIncompletoException | RegistroNaoEncontradoException | LoginException e) {
			throw new Exception(e.getMessage());
		} catch (Exception e) {
			throw new Exception("Erro ao realizar login.");
		}
	}
}
