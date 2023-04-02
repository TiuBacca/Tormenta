package com.baccarin.tormenta.vo.raca;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HablidadeRacaResponse {

	private Long idHabildadeRaca;
	private String nome;
	private String descricao;
}
