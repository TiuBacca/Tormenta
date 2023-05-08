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
	private int bonusArmadura;
	private int bonusEscudo;
	private int outros;
	private int total;

	public ClasseArmaduraRequest() {
		this.total = 10;
	}

}
