package com.baccarin.tormenta.vo.usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioRequest {

	private Long id;
	private String nome;
	private String email;
	private String senha;

}
