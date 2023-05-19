package com.baccarin.tormenta.service;

import java.util.List;

import com.baccarin.tormenta.vo.habilidade.HabilidadeClasseResponse;
import com.baccarin.tormenta.vo.habilidade.HabilidadeRacaResponse;
import com.baccarin.tormenta.vo.habilidade.HabilidadeRequest;
import com.baccarin.tormenta.vo.habilidade.HabilidadeResponse;
import com.baccarin.tormenta.vo.personagem.PersonagemRequest;

public interface HabilidadeService {

	void salvarHabilidade(HabilidadeRequest request) throws Exception;

	void excluirHabilidade(HabilidadeRequest request) throws Exception;

	List<HabilidadeResponse> buscarListaHabilidades();

	List<HabilidadeClasseResponse> buscaListaHabilidadesClasse(PersonagemRequest request) throws Exception;

	List<HabilidadeRacaResponse> buscaListaHabilidadesRaca(PersonagemRequest request)  throws Exception;

}
