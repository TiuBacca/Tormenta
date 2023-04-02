package com.baccarin.tormenta.util;

import java.sql.Connection;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Util {

	private final EntityManager em;

	public Connection getConnection() {
		return em.unwrap(Connection.class);
	}
	
	public EntityManager getEntityManager() {
		return this.em;
	}

}
