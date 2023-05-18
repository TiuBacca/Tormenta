package com.baccarin.tormenta.service;

import java.util.List;

import com.baccarin.tormenta.vo.item.ArmaduraResponse;
import com.baccarin.tormenta.vo.item.ItemFiltro;

public interface ArmaduraService {

	List<ArmaduraResponse> buscaListaTodasArmaduras();

	List<ArmaduraResponse> buscaListaArmadurasByFiltro(ItemFiltro filtro) throws Exception;

}
