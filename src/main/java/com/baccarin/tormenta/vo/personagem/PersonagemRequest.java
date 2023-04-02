package com.baccarin.tormenta.vo.personagem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonagemRequest {

	private Long id;
	private Long idRaca;
	private Long idClasse;
	private Long idTendencia;

	private String nome;
	private String sexo;

	private VidaRequest vida;
	private HabilidadeRequest habilidade;
	private ClasseArmaduraRequest classeArmadura;

	private Integer fortitude;
	private Integer reflexo;
	private Integer vontade;
	private Integer nivel;
	private Double tamanho;

}
