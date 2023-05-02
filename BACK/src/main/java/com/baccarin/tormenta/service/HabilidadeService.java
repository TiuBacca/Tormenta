package com.baccarin.tormenta.service;

import java.util.List;

import com.baccarin.tormenta.vo.habilidade.HabilidadeRequest;
import com.baccarin.tormenta.vo.habilidade.HabilidadeResponse;

public interface HabilidadeService {

	void salvarHabilidade(HabilidadeRequest request) throws Exception;

	void excluirHabilidade(HabilidadeRequest request) throws Exception;

	List<HabilidadeResponse> buscarListaHabilidades();

}
