package com.baccarin.tormenta.domain;

import java.util.Objects;

import com.baccarin.tormenta.vo.tendencia.TendenciaRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tendencia", schema = "sis", uniqueConstraints = {})
public class Tendencia {

	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

	@Column(name = "descricao", nullable = false, updatable = true)
	private String descricao;

	public Tendencia(TendenciaRequest request) {

		if (Objects.nonNull(request.getId())) {
			this.id = request.getId();
		}

		if (Objects.nonNull(request.getDescricao())) {
			this.descricao = request.getDescricao();
		}
	}
}
