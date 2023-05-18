package com.baccarin.tormenta.enums;

public enum TipoArma {
	CORTE("Corte"), PERFURACAO("Perfuração"), ESMAGAMENTO("Esmagamento"), CONTUSAO("Contusão"), FOGO("Fogo"),
	GELO("Gelo"), SAGRADO("Sagrado"), PROFANO("Profano");

	private final String descricao;

	TipoArma(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
