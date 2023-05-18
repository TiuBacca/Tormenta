package com.baccarin.tormenta.enums;

public enum TipoEscudo {
	PEQUENO("Pequeno"), MEDIO("Médio"), GRANDE("Grande");

	private final String descricao;

	TipoEscudo(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
