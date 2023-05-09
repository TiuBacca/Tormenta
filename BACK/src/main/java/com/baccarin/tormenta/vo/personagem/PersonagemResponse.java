package com.baccarin.tormenta.vo.personagem;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonagemResponse {

	private Long id;
	private String nome;
	private String descricaoClasse;
	private String descricaoRaca;
	
	private Integer fortitude;
	private Integer reflexo;
	private Integer vontade;
	private Integer nivel;

	private Integer vidaAtual;
	private Integer vidaTotal;
	
	public PersonagemResponse(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public PersonagemResponse(Long id, String nome, String descricaoClasse, String descricaoRaca) {
		this(id,nome);
		this.descricaoClasse = descricaoClasse;
		this.descricaoRaca = descricaoRaca;
	}

	public PersonagemResponse(Long id, String nome, String descricaoClasse, String descricaoRaca, Integer fortitude,
			Integer reflexo, Integer vontade, Integer nivel) {
		this(id,nome,descricaoClasse,descricaoRaca);
		this.fortitude = fortitude;
		this.reflexo = reflexo;
		this.vontade = vontade;
		this.nivel = nivel;
	}

	
}
