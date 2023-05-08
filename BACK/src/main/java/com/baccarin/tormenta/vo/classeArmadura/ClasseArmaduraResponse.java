package com.baccarin.tormenta.vo.classeArmadura;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClasseArmaduraResponse {

	private Long id;
	
	private Long idPersonagem;
	private String nomePersonagem;
	
	private Integer bonusArmadura;
	private Integer bonusEscudo;
	private Integer outros;
	private Integer total;
}
