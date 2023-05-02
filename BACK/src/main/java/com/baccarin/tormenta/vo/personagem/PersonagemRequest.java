package com.baccarin.tormenta.vo.personagem;

import com.baccarin.tormenta.vo.habilidade.HabilidadeRequest;

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

	private HabilidadeRequest habilidade;
	private ClasseArmaduraRequest classeArmadura;

	private Integer fortitude;
	private Integer reflexo;
	private Integer vontade;
	private Integer nivel;
	private Double tamanho;

	private Long idUsuario;

}
