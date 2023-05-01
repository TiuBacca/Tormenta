package com.baccarin.tormenta.vo.tendencia;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TendenciaResponse {

	private Long id;
	private String descricao;
	
}
