package com.baccarin.tormenta.service.impl;

import javax.security.auth.login.LoginException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.baccarin.tormenta.domain.Usuario;
import com.baccarin.tormenta.exception.RegistroIncompletoException;
import com.baccarin.tormenta.exception.RegistroNaoEncontradoException;
import com.baccarin.tormenta.repository.UsuarioRepository;
import com.baccarin.tormenta.service.LoginService;
import com.baccarin.tormenta.util.Util;
import com.baccarin.tormenta.vo.login.LoginRequest;
import com.baccarin.tormenta.vo.login.LoginResponse;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

	private final UsuarioRepository usuarioRepository;
	private final Util util;

	@Override
	public LoginResponse acessarSistema(LoginRequest request) throws Exception {
		validarAcessarSistema(request);
		return new LoginResponse(util.gerarToken(request));
	}

	private void validarAcessarSistema(LoginRequest request) throws Exception {
		if (StringUtils.isBlank(request.getEmail())) {
			throw new RegistroIncompletoException("Necessário informar o login.");
		} else {
			if (StringUtils.isBlank(request.getSenha())) {
				throw new RegistroIncompletoException("Necessário informar a senha.");
			}
			Usuario user = usuarioRepository.findByEmail(request.getEmail())
					.orElseThrow(() -> new RegistroNaoEncontradoException("Usuário não encontrado."));
			if (user.getSenha().equals(Util.criptografar(request.getSenha()))) {
				return;
			}
			throw new LoginException("E-mail/Senha inválidos.");
		}

	}

}
