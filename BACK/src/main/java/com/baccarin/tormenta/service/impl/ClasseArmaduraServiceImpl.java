package com.baccarin.tormenta.service.impl;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.baccarin.tormenta.domain.ClasseArmadura;
import com.baccarin.tormenta.domain.Personagem;
import com.baccarin.tormenta.exception.RegistroIncompletoException;
import com.baccarin.tormenta.exception.RegistrosAssociadosException;
import com.baccarin.tormenta.repository.ClasseArmaduraRepository;
import com.baccarin.tormenta.repository.PersonagemRepository;
import com.baccarin.tormenta.service.ClasseArmaduraService;
import com.baccarin.tormenta.vo.classeArmadura.ClasseArmaduraRequest;
import com.baccarin.tormenta.vo.classeArmadura.ClasseArmaduraResponse;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ClasseArmaduraServiceImpl implements ClasseArmaduraService {

	private final ClasseArmaduraRepository classeArmaduraRepository;
	private final PersonagemRepository personagemRepository;


	@Override
	public void salvarClasseArmadura(ClasseArmaduraRequest request) throws Exception {
		validaSalvar(request);
		Personagem personagem = personagemRepository.findById(request.getIdPersonagem()).get();
		ClasseArmadura classe = new ClasseArmadura(request);
		classe.setPersonagem(personagem);
		
		classeArmaduraRepository.save(classe);
		
	}

	@Override
	public void excluirClasseArmadura(ClasseArmaduraRequest request) throws Exception {
		validaExcluir(request);
		classeArmaduraRepository.deleteById(request.getId());
	}

	@Override
	public ClasseArmaduraResponse buscarClasseArmaduraByPersonagemId(ClasseArmaduraRequest request) throws Exception {

		if (Objects.isNull(request.getIdPersonagem())) {
			throw new RegistroIncompletoException("Atributo id do personagem faltando.");
		}

		return classeArmaduraRepository.buscarClasseArmaduraByPersonagemId(request.getIdPersonagem());
	}

	private void validaSalvar(ClasseArmaduraRequest request) throws Exception {
		if (Objects.isNull(request.getIdPersonagem())) {
			throw new RegistroIncompletoException("Atribudo id personagem faltando para salvar classe armadura.");
		}
	}

	private void validaExcluir(ClasseArmaduraRequest request) throws Exception {
		if (Objects.isNull(request.getId())) {
			throw new RegistroIncompletoException("Atributo id faltando para excluir classe armadura.");
		} else {
			ClasseArmaduraResponse resp = classeArmaduraRepository.buscarClasseArmaduraByPersonagemId(request.getIdPersonagem());
			if (Objects.nonNull(resp)) {
				throw new RegistrosAssociadosException(
						"Impossível excluir uma classe armadura com um personagem relacionado.");
			}
		}
	}
}
