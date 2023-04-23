package com.baccarin.tormenta.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario", schema = "administracao", uniqueConstraints = {})
public class Usuario {

	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

	@Column(name = "nome", nullable = false, updatable = true)
	private String nome;

	@Column(name = "email", nullable = false, updatable = true)
	private String email;

	@Column(name = "senha", nullable = false, updatable = true)
	private String senha;
	
    @OneToMany(mappedBy = "personagens")
    private List<Personagem> personagens = new ArrayList<>();

}
