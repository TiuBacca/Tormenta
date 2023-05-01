package com.baccarin.tormenta.domain;

import com.baccarin.tormenta.enums.Sexo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@Table(name = "personagem", schema = "tormenta", uniqueConstraints = {})
public class Personagem {

	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;


	@Column(name = "nome", nullable = false, updatable = true)
	private String nome;

	@Enumerated(EnumType.STRING)
	@Column(name = "sexo")
	private Sexo sexo;

	@OneToOne
	@JoinColumn(name = "id_raca", nullable = false)
	private Raca raca;

	@OneToOne
	@JoinColumn(name = "id_classe", nullable = false)
	private Classe classe;

	@OneToOne
	@JoinColumn(name = "id_tendencia", nullable = false)
	private Tendencia tendencia;

	@OneToOne
	@JoinColumn(name = "id_vida", nullable = false)
	private Vida vida;

	@OneToOne
	@JoinColumn(name = "id_habilidade", nullable = false)
	private Habilidade habilidade;

	@OneToOne
	@JoinColumn(name = "id_classe_armadura", nullable = false)
	private ClasseArmadura classeArmadura;

	@Column(name = "fortitude")
	private Integer fortitude;

	@Column(name = "reflexo")
	private Integer reflexo;

	@Column(name = "vontade")
	private Integer vontade;

	@Column(name = "nivel")
	private Integer nivel;

	@Column(name = "tamanho")
	private Double tamanho;

	public Personagem() {
		this.habilidade = new Habilidade();
		this.tendencia = new Tendencia();
		this.raca = new Raca();
		this.classe = new Classe();

		this.nivel = 1;
		this.vontade = Habilidade.getModificador(habilidade.getSabedoria());
		this.reflexo = Habilidade.getModificador(habilidade.getDestreza());
		this.fortitude = Habilidade.getModificador(habilidade.getConstituicao());

		ClasseArmadura classeArmadura = new ClasseArmadura();
		classeArmadura.setTotal(
				classeArmadura.getTotal() + this.nivel % 2 + Habilidade.getModificador(getHabilidade().getDestreza()));

		this.classeArmadura = classeArmadura;
		this.vida = new Vida();
	}

}
