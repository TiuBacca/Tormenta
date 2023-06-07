package com.baccarin.tormenta.vo.personagem;

import com.baccarin.tormenta.vo.classe.ClasseRequest;
import com.baccarin.tormenta.vo.classeArmadura.ClasseArmaduraRequest;
import com.baccarin.tormenta.vo.habilidade.HabilidadeRequest;
import com.baccarin.tormenta.vo.raca.RacaRequest;
import com.baccarin.tormenta.vo.tendencia.TendenciaRequest;

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
	private RacaRequest raca;
	private ClasseRequest classe;
	private TendenciaRequest tendencia;

	private String nome;
	private String sexo;

	private HabilidadeRequest habilidade;

	private Integer fortitude;
	private Integer reflexo;
	private Integer vontade;
	private Integer nivel;
	private Double tamanho;

	private Long idUsuario;

}
