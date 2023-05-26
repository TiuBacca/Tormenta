package com.baccarin.tormenta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.baccarin.tormenta.domain.HabilidadeRaca;
import com.baccarin.tormenta.domain.Raca;

public interface HabilidadeRacaRepository extends JpaRepository<HabilidadeRaca, Long>{

	List<HabilidadeRaca> findByRaca(Raca raca);
}
