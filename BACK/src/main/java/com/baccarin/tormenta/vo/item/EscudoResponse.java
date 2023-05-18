package com.baccarin.tormenta.vo.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EscudoResponse {

	private Long id;
	private String nome;
	private String descricao;
	private String caracteristica;
	private String beneficios;
	private String tipo;

}
