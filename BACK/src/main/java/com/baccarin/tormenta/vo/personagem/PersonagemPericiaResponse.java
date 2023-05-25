package com.baccarin.tormenta.vo.personagem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonagemPericiaResponse {

	private Long idPersonagem;
	private Long idPericia;
	private String nome;
	private String descricao;
	private String modificador;
	private Integer outros;
	private Integer graduacao;
}
