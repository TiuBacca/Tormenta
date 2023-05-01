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

	public RacaResponse(String nome) {
		this.nome = nome;
	}

	
}
