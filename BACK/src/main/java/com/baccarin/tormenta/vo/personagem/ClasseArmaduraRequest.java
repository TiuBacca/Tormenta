package com.baccarin.tormenta.vo.personagem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClasseArmaduraRequest {

	private Long id;
	private Integer bonusArmadura;
	private Integer bonusEscudo;
	private Integer outros;
	private Integer total;

}
