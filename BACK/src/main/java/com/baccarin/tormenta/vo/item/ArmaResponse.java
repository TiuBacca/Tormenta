package com.baccarin.tormenta.vo.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArmaResponse {

	private Long id;
	private String nome;
	private String descricao;
	private String dano;
	private String critico;
	private String tipo;

}
