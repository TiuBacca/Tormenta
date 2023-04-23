package com.baccarin.tormenta.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@Table(name = "vida", schema = "tormenta", uniqueConstraints = {})
public class Vida {

	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

	@Column(name = "pontos_totais")
	private Integer pontosTotais;

	@Column(name = "pontos_atuais")
	private Integer pontosAtuais;
	
	public Vida() {
		this.pontosAtuais = 10;
		this.pontosTotais = 10;
	}

	public Vida(Integer pontosTotais, Integer pontosAtuais) {
		this.pontosTotais = pontosTotais;
		this.pontosAtuais = pontosAtuais;
	}
}
