package com.baccarin.tormenta.service;

import java.util.List;

import com.baccarin.tormenta.vo.item.EscudoResponse;
import com.baccarin.tormenta.vo.item.ItemFiltro;

public interface EscudoService {

	List<EscudoResponse> buscaListaTodasEscudos();

	List<EscudoResponse> buscaListaEscudosByFiltro(ItemFiltro filtro) throws Exception;

}
