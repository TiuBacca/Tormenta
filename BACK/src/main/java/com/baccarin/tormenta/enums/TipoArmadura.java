package com.baccarin.tormenta.enums;

public enum TipoArmadura {
	MEDIA("Média"), PESADA("Pesada"), LEVE("Leve");

	private final String descricao;

	TipoArmadura(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}