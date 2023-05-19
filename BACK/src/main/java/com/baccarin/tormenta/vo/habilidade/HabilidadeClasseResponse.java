package com.baccarin.tormenta.vo.habilidade;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HabilidadeClasseResponse {

	private Long id;
	private String nome;
	private String descricao;	
	
}
