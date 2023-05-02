package com.baccarin.tormenta.domain;

import java.util.Objects;

import com.baccarin.tormenta.vo.habilidade.HabilidadeRequest;

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
@Table(name = "habilidade", schema = "tormenta", uniqueConstraints = {})
public class Habilidade {

	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

    @OneToOne(mappedBy = "habilidade")
    private Personagem personagem;
    
	@Column(name = "forca")
	private Integer forca;

	@Column(name = "destreza")
	private Integer destreza;

	@Column(name = "constituicao")
	private Integer constituicao;

	@Column(name = "inteligencia")
	private Integer inteligencia;

	@Column(name = "sabedoria")
	private Integer sabedoria;

	@Column(name = "carisma")
	private Integer carisma;

	public static Integer getModificador(Integer valor) {
		if (valor >= 10) {
			return (valor - 10) / 2;
		} else {
			return 0;
		}
	}

	public Habilidade() {
		this.forca = 10;
		this.destreza = 10;
		this.constituicao = 10;
		this.inteligencia = 10;
		this.sabedoria = 10;
		this.carisma = 10;
	}

	public Habilidade(HabilidadeRequest request) {
		this();

		if (Objects.nonNull(request.getForca())) {
			this.forca = request.getForca();
		}

		if (Objects.nonNull(request.getDestreza())) {
			this.destreza = request.getDestreza();
		}

		if (Objects.nonNull(request.getConstituicao())) {
			this.constituicao = request.getConstituicao();
		}

		if (Objects.nonNull(request.getInteligencia())) {
			this.inteligencia = request.getInteligencia();
		}

		if (Objects.nonNull(request.getSabedoria())) {
			this.sabedoria = request.getSabedoria();
		}

		if (Objects.nonNull(request.getCarisma())) {
			this.carisma = request.getCarisma();
		}

	}

}
