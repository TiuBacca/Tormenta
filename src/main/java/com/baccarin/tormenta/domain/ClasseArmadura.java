package com.baccarin.tormenta.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name = "classe_armadura", schema = "tormenta", uniqueConstraints = {})
public class ClasseArmadura {

	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

	@Column(name = "bonusArmadura")
	private Integer bonusArmadura;

	@Column(name = "bonusEscudo")
	private Integer bonusEscudo;

	@Column(name = "outros")
	private Integer outros;

	@Column(name = "total")
	private Integer total;

	public ClasseArmadura() {
		this.bonusArmadura = 0;
		this.bonusEscudo = 0;
		this.outros = 0;
		this.total = 10;
	}
}
