package com.baccarin.tormenta.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baccarin.tormenta.repository.EscudoRepository;
import com.baccarin.tormenta.service.EscudoService;
import com.baccarin.tormenta.vo.item.EscudoResponse;
import com.baccarin.tormenta.vo.item.ItemFiltro;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class EscudoServiceImpl implements EscudoService {

	private final EscudoRepository escudoRepository;

	@Override
	public List<EscudoResponse> buscaListaTodasEscudos() {
		List<EscudoResponse> lista = escudoRepository.findAll().stream().map(escudo -> {
			EscudoResponse escudoResponse = new EscudoResponse();
			escudoResponse.setId(escudo.getId());
			escudoResponse.setNome(escudo.getNome());
			escudoResponse.setDescricao(escudo.getDescricao());
			escudoResponse.setTipo(escudo.getTipoEscudo().getDescricao());
			return escudoResponse;

		}).collect(Collectors.toList());

		return lista;
	}

	@Override
	public List<EscudoResponse> buscaListaEscudosByFiltro(ItemFiltro filtro) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
