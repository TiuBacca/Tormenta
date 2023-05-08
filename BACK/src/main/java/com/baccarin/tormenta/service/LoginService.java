package com.baccarin.tormenta.service;

import com.baccarin.tormenta.vo.login.LoginRequest;
import com.baccarin.tormenta.vo.login.LoginResponse;

public interface LoginService {

	LoginResponse acessarSistema(LoginRequest request) throws Exception;

}
