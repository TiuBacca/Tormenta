package com.baccarin.tormenta.vo.usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioResponse {

	private Long id;
	private String nome;
	private String email;

		
	public UsuarioResponse(String nome) {
		this.nome = nome;
	}
	
	public UsuarioResponse(String nome, String email) {
		this.nome = nome;
		this.email = email;
	}



}
