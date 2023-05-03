package com.baccarin.tormenta.resource;

import java.util.ArrayList;
import java.util.List;

import com.baccarin.tormenta.vo.classe.ClasseRequest;
import com.baccarin.tormenta.vo.raca.RacaRequest;
import com.baccarin.tormenta.vo.tendencia.TendenciaRequest;
import com.baccarin.tormenta.vo.usuario.UsuarioRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor

@Builder
public class PersonagemFiltro {

	private Long id;
	private List<UsuarioRequest> usuarios;
	private List<String> sexos;
	private List<ClasseRequest> classes;
	private List<RacaRequest> racas;
	private List<TendenciaRequest> tendencias;

	public PersonagemFiltro() {
		this.usuarios = new ArrayList<>();
		this.sexos = new ArrayList<>();
		this.classes = new ArrayList<>();
		this.racas = new ArrayList<>();
		this.tendencias = new ArrayList<>();
	}

}
