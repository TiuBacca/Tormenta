package com.baccarin.tormenta.domain;

import com.baccarin.tormenta.enums.TipoHabilidade;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class Pericia {

	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;
	
	@Column(name = "descricao", nullable = false, updatable = true)
	private String descricao;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipoModificador")
	private TipoHabilidade tipoModificador;

	@Column(name = "grad")
	private Integer grad;

	@Column(name = "outros")
	private Integer outros;

}
