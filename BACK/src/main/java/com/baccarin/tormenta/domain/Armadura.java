package com.baccarin.tormenta.domain;

import com.baccarin.tormenta.enums.TipoArmadura;

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
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "armadura", schema = "item", uniqueConstraints = {})
public class Armadura {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

	@Column(name = "nome", nullable = false, updatable = true)
	private String nome;

	@Column(name = "descricao", nullable = false, updatable = true)
	private String descricao;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo")
	private TipoArmadura tipoArmadura;
	
	@Column(name = "caracteristica", nullable = false, updatable = true)
	private String caracteristica;
	
	@Column(name = "beneficios", nullable = false, updatable = true)
	private String beneficios;
	
}
