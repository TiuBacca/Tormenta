package com.baccarin.tormenta.vo.personagem;

import com.baccarin.tormenta.enums.TipoHabilidade;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonagemPericiaResponse {

	private Long idPersonagem;
	private Long idPericia;
	private String nome;
	private String descricao;
	private String modificador;
	private Integer outros;
	private Integer graduacao;

	public PersonagemPericiaResponse(Long idPericia, String nome, String descricao, TipoHabilidade modificador,
			Integer outros, Integer graduacao) {
		this.idPericia = idPericia;
		this.nome = nome;
		this.descricao = descricao;
		this.modificador = modificador.getDescricao();
		this.outros = outros;
		this.graduacao = graduacao;
	}

}
