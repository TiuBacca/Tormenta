package com.baccarin.tormenta.domain;

import com.baccarin.tormenta.enums.TipoHabilidade;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name = "pericia", schema = "tormenta", uniqueConstraints = {})
public class Pericia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

	@Column(name = "nome", nullable = false, updatable = true)
	private String nome;
	
	@Column(name = "descricao", nullable = false, updatable = true)
	private String descricao;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipoModificador")
	private TipoHabilidade tipoModificador;

}
 