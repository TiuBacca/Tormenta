package com.baccarin.tormenta.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.baccarin.tormenta.vo.classe.ClasseRequest;

import io.micrometer.common.util.StringUtils;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

	@Column(name = "nome", nullable = false)
	private String nome;

	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "pontos_base_vida")
	private Integer pontosBaseVida;

	@OneToMany(mappedBy = "classe")
	private List<HabilidadeClasse> habilidades = new ArrayList<>();

	public Classe(ClasseRequest request) {
		if (Objects.nonNull(request.getId()) && request.getId() != 0) {
			this.id = request.getId();
		}

		if (StringUtils.isNotBlank(request.getNome())) {
			this.nome = request.getNome();
		}

		if (StringUtils.isNotBlank(request.getDescricao())) {
			this.descricao = request.getDescricao();
		}

	}
}
