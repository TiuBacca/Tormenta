package com.baccarin.tormenta.enums;

import java.util.ArrayList;
import java.util.List;

public enum Sexo {

	MASCULINO("Masculino"), FEMININO("Feminino");

	private String descricao;

	Sexo(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public static List<String> obterDescricoesSexo() {
		List<String> descricoes = new ArrayList<>();
		for (Sexo sexo : Sexo.values()) {
			descricoes.add(sexo.getDescricao());
		}
		return descricoes;
	}
}
