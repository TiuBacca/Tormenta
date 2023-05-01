package com.baccarin.tormenta.service;

import java.util.List;

import com.baccarin.tormenta.domain.Raca;
import com.baccarin.tormenta.vo.raca.RacaRequest;
import com.baccarin.tormenta.vo.raca.RacaResponse;

public interface RacaService {

	List<RacaResponse> buscarListaTodasRacas();
	
	void excluirRaca(RacaRequest request) throws Exception;

	void salvarRaca(RacaRequest request) throws Exception;

}