package com.baccarin.tormenta.service;

import java.util.List;

import com.baccarin.tormenta.domain.Raca;
import com.baccarin.tormenta.vo.raca.RacaRequest;

public interface RacaService {

	public List<Raca> buscarListaTodasRacas();
	
	void excluirRaca(RacaRequest request) throws Exception;
}