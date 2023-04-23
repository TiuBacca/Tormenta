package com.baccarin.tormenta.vo.personagem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonagemResponse {

	private Long idPersonagem;
	private String nome;
	private String descricaoClasse;
	private String descricaoRaca;
	
}
