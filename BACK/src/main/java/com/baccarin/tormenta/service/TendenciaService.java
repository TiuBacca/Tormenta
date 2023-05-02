package com.baccarin.tormenta.service;

import java.util.List;

import com.baccarin.tormenta.vo.tendencia.TendenciaRequest;
import com.baccarin.tormenta.vo.tendencia.TendenciaResponse;

public interface TendenciaService {

	void salvarTendencia(TendenciaRequest request) throws Exception;

	void excluirTendencia(TendenciaRequest request) throws Exception;

	List<TendenciaResponse> buscarListaTendencias();

}
