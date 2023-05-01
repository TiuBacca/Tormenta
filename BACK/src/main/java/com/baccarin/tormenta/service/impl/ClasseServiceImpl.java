package com.baccarin.tormenta.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.baccarin.tormenta.domain.Classe;
import com.baccarin.tormenta.exception.RegistroDuplicadoException;
import com.baccarin.tormenta.exception.RegistroIncompletoException;
import com.baccarin.tormenta.exception.RegistroNaoEncontradoException;
import com.baccarin.tormenta.repository.ClasseRepository;
import com.baccarin.tormenta.service.ClasseService;
import com.baccarin.tormenta.vo.classe.ClasseRequest;
import com.baccarin.tormenta.vo.classe.ClasseResponse;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ClasseServiceImpl implements ClasseService {

	private final ClasseRepository classeRepository;

	@Override
	public void salvarClasse(ClasseRequest request) throws Exception {
		validaSalvarClasse(request);
		classeRepository.save(new Classe(request));
	}

	@Override
	public void excluirClasse(ClasseRequest request) throws Exception {
		validaExcluirClasse(request);
		classeRepository.deleteById(request.getId());
	}

	@Override
	public List<ClasseResponse> buscarListaClasses() {
		return classeRepository.buscarListaClasses();
	}

	private void validaSalvarClasse(ClasseRequest request) throws Exception {
		if (Objects.isNull(request.getNome()) || request.getNome().isBlank()) {
			throw new RegistroIncompletoException("Atributo nome faltando para salvar classe.");
		} else {
			List<ClasseResponse> lista = classeRepository.buscarListaClassesByNome(request.getNome());
			if (Objects.nonNull(lista) && !lista.isEmpty()) {
				throw new RegistroDuplicadoException("Classe ja cadastrada.");
			}
		}

	}

	private void validaExcluirClasse(ClasseRequest request) throws Exception {
		if (Objects.isNull(request.getId())) {
			throw new RegistroIncompletoException("Atributo id está faltando.");
		} else {
			classeRepository.findById(request.getId())
					.orElseThrow(() -> new RegistroNaoEncontradoException("Classe não encontrada."));
		}
	}
}
