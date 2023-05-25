package com.baccarin.tormenta.vo.habilidade;

import com.baccarin.tormenta.domain.HabilidadeClasse;

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

	public HabilidadeClasseResponse(HabilidadeClasse habilidade) {
		this.id = habilidade.getId();
		this.nome = habilidade.getNome();
		this.descricao = habilidade.getDescricao();
	}

}
