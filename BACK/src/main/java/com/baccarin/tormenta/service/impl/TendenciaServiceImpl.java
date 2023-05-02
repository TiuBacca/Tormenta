package com.baccarin.tormenta.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.baccarin.tormenta.domain.Tendencia;
import com.baccarin.tormenta.exception.RegistroDuplicadoException;
import com.baccarin.tormenta.exception.RegistroIncompletoException;
import com.baccarin.tormenta.exception.RegistroNaoEncontradoException;
import com.baccarin.tormenta.repository.TendenciaRepository;
import com.baccarin.tormenta.service.TendenciaService;
import com.baccarin.tormenta.vo.tendencia.TendenciaRequest;
import com.baccarin.tormenta.vo.tendencia.TendenciaResponse;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class TendenciaServiceImpl implements TendenciaService {

	private final TendenciaRepository tendenciaRepository;

	@Override
	public void salvarTendencia(TendenciaRequest request) throws Exception {
		validaSalvar(request);
		tendenciaRepository.save(new Tendencia(request));
	}

	@Override
	public void excluirTendencia(TendenciaRequest request) throws Exception {
		validaExcluir(request);
		tendenciaRepository.deleteById(request.getId());
	}

	@Override
	public List<TendenciaResponse> buscarListaTendencias() {
		return tendenciaRepository.buscarListaTendencias();
	}

	private void validaSalvar(TendenciaRequest request) throws Exception {
		if (Objects.isNull(request.getDescricao()) || request.getDescricao().isBlank()) {
			throw new RegistroIncompletoException("Atribudo descrição faltando para salvar tendência.");
		} else {
			List<TendenciaResponse> lista = tendenciaRepository.buscarTendenciaByDescricao(request.getDescricao());
			if (Objects.nonNull(lista) && !lista.isEmpty()) {
				throw new RegistroDuplicadoException("Tendência ja cadastrada.");
			}
		}
	}

	private void validaExcluir(TendenciaRequest request) throws Exception {
		if (Objects.isNull(request.getId())) {
			throw new RegistroIncompletoException("Atributo id faltando para excluir tendência.");
		} else {
			tendenciaRepository.findById(request.getId())
					.orElseThrow(() -> new RegistroNaoEncontradoException("Tendência não encontrada."));
		}
	}
}
