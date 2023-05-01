package com.baccarin.tormenta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.baccarin.tormenta.domain.Raca;
import com.baccarin.tormenta.vo.raca.RacaResponse;

@Repository
public interface RacaRepository extends JpaRepository<Raca, Long> {

	@Query("select new com.baccarin.tormenta.vo.raca.RacaResponse(r.id, r.nome) from Raca r where r.id > 0 ")
	List<RacaResponse> buscarListaTodasRacas();

}