package com.baccarin.tormenta.vo.raca;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RacaRequest {

	private Long idRaca;
	private String nome;
	private List<HablidadeRacaResponse> habilidades = new ArrayList<>();
}
