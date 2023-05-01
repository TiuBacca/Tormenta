package com.baccarin.tormenta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.baccarin.tormenta.domain.Tendencia;
import com.baccarin.tormenta.vo.tendencia.TendenciaResponse;

@Repository
public interface TendenciaRepository extends JpaRepository<Tendencia, Long> {

	@Query (" select new com.baccarin.tormenta.vo.tendencia.TendenciaResponse (t.id, t.descricao) from Tendencia t where t.descricao like :descricao")
	List<TendenciaResponse> buscarTendenciaByDescricao(@Param("descricao") String descricao);

	
	@Query (" select new com.baccarin.tormenta.vo.tendencia.TendenciaResponse (t.id, t.descricao) from Tendencia t where t.id > 0 ")
	List<TendenciaResponse> buscarListaTendencias();

}
