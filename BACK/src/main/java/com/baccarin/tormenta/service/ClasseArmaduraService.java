package com.baccarin.tormenta.service;

import com.baccarin.tormenta.vo.classeArmadura.ClasseArmaduraRequest;
import com.baccarin.tormenta.vo.classeArmadura.ClasseArmaduraResponse;

public interface ClasseArmaduraService {

	void salvarClasseArmadura(ClasseArmaduraRequest request) throws Exception;

	void excluirClasseArmadura(ClasseArmaduraRequest request) throws Exception;

	ClasseArmaduraResponse buscarClasseArmaduraByPersonagemId(ClasseArmaduraRequest request) throws Exception;

}
