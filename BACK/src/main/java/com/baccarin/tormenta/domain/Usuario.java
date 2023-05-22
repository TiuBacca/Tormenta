package com.baccarin.tormenta.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.baccarin.tormenta.util.Util;
import com.baccarin.tormenta.vo.usuario.UsuarioRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "usuario", schema = "administracao", uniqueConstraints = {})
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

	@Column(name = "nome", nullable = false, updatable = true)
	private String nome;

	@Column(name = "email", nullable = false, updatable = false, unique = true)
	private String email;

	@Column(name = "senha", nullable = false, updatable = true)
	private String senha;

	@OneToMany(mappedBy = "usuario")
	private List<Personagem> personagens = new ArrayList<>();

	public Usuario(UsuarioRequest request) {

		if (Objects.nonNull(request.getId())) {
			this.id = request.getId();
		}

		if (Objects.nonNull(request.getNome())) {
			this.nome = request.getNome();
		}

		if (Objects.nonNull(request.getEmail())) {
			this.email = Util.criptografar(request.getEmail());
		}

		if (Objects.nonNull(request.getSenha())) {
			this.senha = Util.criptografar(request.getSenha());
		}

	}
}
