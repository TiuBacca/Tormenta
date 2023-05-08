package com.baccarin.tormenta.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;

import org.springframework.context.annotation.Configuration;

import com.baccarin.tormenta.vo.login.LoginRequest;
import com.baccarin.tormenta.vo.login.LoginResponse;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class Util {

	private final EntityManager em;

	public String gerarToken(LoginRequest request) {
		return "ajustar serviço para gerar token";
	}

	public Connection getConnection() {
		return em.unwrap(Connection.class);
	}
	
	public EntityManager getEntityManager() {
		return this.em;
	}
	
    public static String criptografar(String texto) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(texto.getBytes(StandardCharsets.UTF_8));
            return bytesParaHex(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String bytesParaHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }


}
