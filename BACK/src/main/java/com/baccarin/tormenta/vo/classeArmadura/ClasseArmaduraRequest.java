package com.baccarin.tormenta.vo.classeArmadura;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
public class ClasseArmaduraRequest {

	private Long id;
	private Long idPersonagem;
	private Integer bonusArmadura;
	private Integer bonusEscudo;
	private Integer outros;
	private Integer total;

	public ClasseArmaduraRequest() {
		this.bonusArmadura = 0;
		this.bonusEscudo = 0;
		this.outros = 0;
		this.total = 10;
	}

}
