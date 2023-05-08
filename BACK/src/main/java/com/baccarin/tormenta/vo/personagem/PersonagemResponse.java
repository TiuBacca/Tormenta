package com.baccarin.tormenta.vo.personagem;

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
	
	
	public PersonagemResponse(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	
}
