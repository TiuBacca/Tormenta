package com.baccarin.tormenta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.baccarin.tormenta.domain.Classe;
import com.baccarin.tormenta.domain.HabilidadeClasse;

public interface HabilidadeClasseRepository extends JpaRepository<HabilidadeClasse, Long>{

	List<HabilidadeClasse> findByClasse(Classe classe);	
}
