package com.baccarin.tormenta.vo.raca;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RacaResponse {

	private Long id;
	private String nome;
	private String descricao;

	public RacaResponse(String nome) {
		this.nome = nome;
	}

	public RacaResponse(Long idRaca, String nome) {
		this.id = idRaca;
		this.nome = nome;
	}

}
