package com.baccarin.tormenta.service;

import java.util.List;

import com.baccarin.tormenta.vo.item.ArmaResponse;
import com.baccarin.tormenta.vo.item.ItemFiltro;

public interface ArmaService {

	List<ArmaResponse> buscaListaTodasArmas();

	List<ArmaResponse> buscaListaArmasByFiltro(ItemFiltro filtro) throws Exception;

}
