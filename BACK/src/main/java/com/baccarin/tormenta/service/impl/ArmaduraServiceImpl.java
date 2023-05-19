package com.baccarin.tormenta.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baccarin.tormenta.repository.ArmaduraRepository;
import com.baccarin.tormenta.service.ArmaduraService;
import com.baccarin.tormenta.vo.item.ArmaduraResponse;
import com.baccarin.tormenta.vo.item.ItemFiltro;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ArmaduraServiceImpl implements ArmaduraService {

	private final ArmaduraRepository armaduraRepository;
	//private final EntityManager em;

	@Override
	public List<ArmaduraResponse> buscaListaTodasArmaduras() {
		List<ArmaduraResponse> armadurasResponse = armaduraRepository.findAll().stream().map(armadura -> {
			ArmaduraResponse armaduraResponse = new ArmaduraResponse();
			armaduraResponse.setId(armadura.getId());
			armaduraResponse.setNome(armadura.getNome());
			armaduraResponse.setDescricao(armadura.getDescricao());
			armaduraResponse.setTipo(armadura.getTipoArmadura().getDescricao());
			return armaduraResponse;
		}).collect(Collectors.toList());

		return armadurasResponse;
	}

	@Override
	public List<ArmaduraResponse> buscaListaArmadurasByFiltro(ItemFiltro filtro) throws Exception {


		return null;
	}

}
