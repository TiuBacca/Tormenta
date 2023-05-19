package com.baccarin.tormenta.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baccarin.tormenta.repository.ArmaRepository;
import com.baccarin.tormenta.service.ArmaService;
import com.baccarin.tormenta.vo.item.ArmaResponse;
import com.baccarin.tormenta.vo.item.ItemFiltro;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ArmaServiceImpl implements ArmaService {

	private final ArmaRepository armaRepository;
	
	@Override
	public List<ArmaResponse> buscaListaTodasArmas() {
		List<ArmaResponse> armasResponse = armaRepository.findAll().stream().map(arma -> {
			ArmaResponse armaResponse = new ArmaResponse();
			armaResponse.setId(arma.getId());
			armaResponse.setNome(arma.getNome());
			armaResponse.setDescricao(arma.getDescricao());
			armaResponse.setDano(arma.getDano());
			armaResponse.setCritico(arma.getCritico());
			armaResponse.setTipo(arma.getTipoArma().getDescricao());
			return armaResponse;
		}).collect(Collectors.toList());


		return armasResponse;
	}

	@Override
	public List<ArmaResponse> buscaListaArmasByFiltro(ItemFiltro filtro) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
