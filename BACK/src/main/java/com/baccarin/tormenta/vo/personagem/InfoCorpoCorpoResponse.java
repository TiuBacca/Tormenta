package com.baccarin.tormenta.vo.personagem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InfoCorpoCorpoResponse {

	private Long idPersonagem;
	private Integer bonusBaseAtaque;
	private Integer modificadorHabilidade;
	private Integer modificadorTamanho;
	private Integer outros;
}
