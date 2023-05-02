package com.baccarin.tormenta.domain;

import java.util.Objects;

import com.baccarin.tormenta.vo.classeArmadura.ClasseArmaduraRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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

	@OneToOne(mappedBy = "classeArmadura")
	private Personagem personagem;

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

	public ClasseArmadura(ClasseArmaduraRequest request) {
		this();

		if (Objects.nonNull(request.getBonusArmadura())) {
			this.bonusArmadura = request.getBonusArmadura();
		}

		if (Objects.nonNull(request.getBonusEscudo())) {
			this.bonusEscudo = request.getBonusEscudo();
		}

		if (Objects.nonNull(request.getOutros())) {
			this.outros = request.getOutros();
		}

		this.total = this.total + this.bonusArmadura + this.bonusEscudo + this.outros;

	}
}
