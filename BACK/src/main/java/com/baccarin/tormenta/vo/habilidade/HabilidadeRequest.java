package com.baccarin.tormenta.vo.habilidade;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HabilidadeRequest {

	private Long id;
	private Long idPersonagem;
	private Integer forca;
	private Integer destreza;
	private Integer constituicao;
	private Integer inteligencia;
	private Integer sabedoria;
	private Integer carisma;

}
