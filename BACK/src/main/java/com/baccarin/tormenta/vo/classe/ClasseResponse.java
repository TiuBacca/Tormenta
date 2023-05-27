package com.baccarin.tormenta.vo.classe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClasseResponse {

	private Long id;
	private String nome;
	private String descricao;
	private Integer pontosBaseVida;
	private Integer pontosBaseAtaque;

	public ClasseResponse(Long idClasse, String nome) {
		this.id = idClasse;
		this.nome = nome;
	}

	public ClasseResponse(Long idClasse, String descricaoClasse, Integer pontosBaseVida2, Integer pontosBaseAtaque2) {
		this(idClasse, descricaoClasse);
		this.pontosBaseVida = pontosBaseVida2;
		this.pontosBaseAtaque = pontosBaseAtaque2;
	}

}
