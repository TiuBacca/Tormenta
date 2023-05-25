package com.baccarin.tormenta.service;

import java.util.List;

import com.baccarin.tormenta.vo.classe.ClasseRequest;
import com.baccarin.tormenta.vo.classe.ClasseResponse;
import com.baccarin.tormenta.vo.habilidade.HabilidadeClasseResponse;

public interface ClasseService {

	void salvarClasse(ClasseRequest request) throws Exception;

	void excluirClasse(ClasseRequest request) throws Exception;

	List<ClasseResponse> buscarListaClassesByFiltro(ClasseRequest request) throws Exception;

	List<HabilidadeClasseResponse> buscaListaHabilidadesByClasse(ClasseRequest request) throws Exception;

}
