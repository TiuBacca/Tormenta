package com.baccarin.tormenta.enums;

public enum TipoHabilidade {

	FORCA("Força"), DESTREZA("Destreza"), CONSTITUICAO("Constituição"), INTELIGENCIA("Inteligência"),
	SABEDORIA("Sabedoria"), CARISMA("Carisma");

	private String descricao;

	TipoHabilidade(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
