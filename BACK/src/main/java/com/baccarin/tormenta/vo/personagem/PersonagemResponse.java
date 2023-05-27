package com.baccarin.tormenta.vo.personagem;

import java.util.Objects;

import com.baccarin.tormenta.enums.Sexo;
import com.baccarin.tormenta.vo.classe.ClasseResponse;
import com.baccarin.tormenta.vo.raca.RacaResponse;
import com.baccarin.tormenta.vo.tendencia.TendenciaResponse;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonagemResponse {

	private Long id;
	private String nome;
	private ClasseResponse classe;
	private RacaResponse raca;
	private TendenciaResponse tendencia;

	private Integer fortitude;
	private Integer reflexo;
	private Integer vontade;
	private Integer nivel;

	private Integer vidaAtual;
	private Integer vidaTotal;

	private String sexo;

	public PersonagemResponse(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public PersonagemResponse(Long id, String nome, String descricaoClasse, String descricaoRaca, Integer fortitude,
			Integer reflexo, Integer vontade, Integer nivel, Integer vidaAtual, Integer vidaTotal) {
		this.id = id;
		this.nome = nome;

		this.fortitude = fortitude;
		this.reflexo = reflexo;
		this.vontade = vontade;
		this.nivel = nivel;
		this.vidaAtual = vidaAtual;
		this.vidaTotal = vidaTotal;
	}

	public PersonagemResponse(Long id, String nome, Long idClasse, String descricaoClasse, Long idRaca,
			String descricaoRaca, Long idTendencia, String descricaoTendencia, Integer fortitude, Integer reflexo,
			Integer vontade, Integer nivel, Integer vidaAtual, Integer vidaTotal, Sexo sexo) {
		this.id = id;
		this.nome = nome;

		this.classe = new ClasseResponse(idClasse, descricaoClasse);
		this.raca = new RacaResponse(idRaca, descricaoRaca);
		this.tendencia = new TendenciaResponse(idTendencia, descricaoTendencia);

		this.fortitude = fortitude;
		this.reflexo = reflexo;
		this.vontade = vontade;
		this.nivel = nivel;
		this.vidaAtual = vidaAtual;
		this.vidaTotal = vidaTotal;
		this.sexo = Objects.isNull(sexo) ? "Indefinido" : sexo.getDescricao();
	}

}
