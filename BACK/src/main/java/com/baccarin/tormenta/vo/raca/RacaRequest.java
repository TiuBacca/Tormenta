package com.baccarin.tormenta.vo.raca;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RacaRequest {

	private Long id;
	private String nome;
	private List<HablidadeRacaResponse> habilidades = new ArrayList<>();
}
