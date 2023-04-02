package com.baccarin.tormenta.util;

import java.sql.Connection;

import org.springframework.context.annotation.Configuration;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class Util {

	private final EntityManager em;

	public Connection getConnection() {
		return em.unwrap(Connection.class);
	}
	
	public EntityManager getEntityManager() {
		return this.em;
	}

}
