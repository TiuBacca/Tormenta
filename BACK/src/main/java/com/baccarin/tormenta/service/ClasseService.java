package com.baccarin.tormenta.service;

import java.util.List;

import com.baccarin.tormenta.vo.classe.ClasseRequest;
import com.baccarin.tormenta.vo.classe.ClasseResponse;

public interface ClasseService {

	void salvarClasse(ClasseRequest request) throws Exception;

	void excluirClasse(ClasseRequest request)  throws Exception;

	List<ClasseResponse> buscarListaClasses();

}
