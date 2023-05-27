package com.baccarin.tormenta.vo.classe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClasseRequest {

	private Long id;
	private String nome;
	private String descricao;
	private Integer pontosBaseVida;
	private Integer pontosBaseAtaque;
}
