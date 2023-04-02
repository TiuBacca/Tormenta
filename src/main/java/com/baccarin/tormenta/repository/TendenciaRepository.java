package com.baccarin.tormenta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.baccarin.tormenta.domain.Tendencia;

@Repository
public interface TendenciaRepository extends JpaRepository<Tendencia, Long> {

}
