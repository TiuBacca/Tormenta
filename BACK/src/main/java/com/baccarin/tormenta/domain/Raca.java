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
@Table(name = "raca", schema = "sis", uniqueConstraints = {})
public class Raca {

	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

	@Column(name = "nome", unique = true, nullable = false, updatable = true)
	private String nome;
	
    @OneToMany(mappedBy = "raca")
    private List<HablidadeRaca> habilidades = new ArrayList<>();
	
}
