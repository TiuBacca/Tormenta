package com.baccarin.tormenta.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.baccarin.tormenta.vo.classe.ClasseRequest;

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
@Table(name = "classe", schema = "tormenta", uniqueConstraints = {})
public class Classe {

	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

	@Column(name = "nome", nullable = false, updatable = true)
	private String nome;

	@OneToMany(mappedBy = "classe")
	private List<HablidadeClasse> habilidades = new ArrayList<>();

	public Classe(ClasseRequest request) {
		if (Objects.nonNull(request.getNome())) {
			this.nome = request.getNome();
		}

		if (Objects.nonNull(request.getId())) {
			this.id = request.getId();
		}
	}
}
