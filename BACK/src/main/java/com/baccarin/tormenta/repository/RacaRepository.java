package com.baccarin.tormenta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.baccarin.tormenta.domain.Raca;

@Repository
public interface RacaRepository extends JpaRepository<Raca, Long> {
	
 
}