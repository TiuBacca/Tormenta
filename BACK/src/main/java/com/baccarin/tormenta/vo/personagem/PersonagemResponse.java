package com.baccarin.tormenta.vo.personagem;

import java.util.Objects;

import com.baccarin.tormenta.enums.Sexo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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

	private String sexo;

	public PersonagemResponse(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public PersonagemResponse(Long id, String nome, String descricaoClasse, String descricaoRaca) {
		this(id, nome);
		this.descricaoClasse = descricaoClasse;
		this.descricaoRaca = descricaoRaca;
	}

	public PersonagemResponse(Long id, String nome, String descricaoClasse, String descricaoRaca, Integer fortitude,
			Integer reflexo, Integer vontade, Integer nivel) {
		this(id, nome, descricaoClasse, descricaoRaca);
		this.fortitude = fortitude;
		this.reflexo = reflexo;
		this.vontade = vontade;
		this.nivel = nivel;
	}

	public PersonagemResponse(Long id, String nome, String descricaoClasse, String descricaoRaca, Integer fortitude,
			Integer reflexo, Integer vontade, Integer nivel, Integer vidaAtual, Integer vidaTotal) {
		this.id = id;
		this.nome = nome;
		this.descricaoClasse = descricaoClasse;
		this.descricaoRaca = descricaoRaca;
		this.fortitude = fortitude;
		this.reflexo = reflexo;
		this.vontade = vontade;
		this.nivel = nivel;
		this.vidaAtual = vidaAtual;
		this.vidaTotal = vidaTotal;
	}

	public PersonagemResponse(Long id, String nome, String descricaoClasse, String descricaoRaca, Integer fortitude,
			Integer reflexo, Integer vontade, Integer nivel, Integer vidaAtual, Integer vidaTotal, Sexo sexo) {
		this.id = id;
		this.nome = nome;
		this.descricaoClasse = descricaoClasse;
		this.descricaoRaca = descricaoRaca;
		this.fortitude = fortitude;
		this.reflexo = reflexo;
		this.vontade = vontade;
		this.nivel = nivel;
		this.vidaAtual = vidaAtual;
		this.vidaTotal = vidaTotal;
		this.sexo = Objects.isNull(sexo) ? "Indefinido" : sexo.getDescricao();
	}

}
