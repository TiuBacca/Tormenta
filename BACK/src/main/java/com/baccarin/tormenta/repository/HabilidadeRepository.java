package com.baccarin.tormenta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.baccarin.tormenta.domain.Habilidade;
import com.baccarin.tormenta.vo.habilidade.HabilidadeResponse;
import com.baccarin.tormenta.vo.personagem.PersonagemResponse;

@Repository
public interface HabilidadeRepository extends JpaRepository<Habilidade, Long> {

	@Query("SELECT NEW com.baccarin.tormenta.vo.personagem.PersonagemResponse (p.id, p.nome) from Personagem p "
			+ " WHERE p.habilidade.id =:idHabilidade ")
	public PersonagemResponse buscaPersonagensByHabilidadeId(@Param("idHabilidade") Long idHabilidade);

	@Query("SELECT NEW com.baccarin.tormenta.vo.habilidade.HabilidadeResponse (h.id, p.id, p.nome,  h.forca, h.destreza,"
			+ "h.constituicao, h.inteligencia, h.sabedoria, h.carisma ) FROM Personagem p inner join p.habilidade h where h.id > 0")
	public List<HabilidadeResponse> buscarListaHabilidades();
}
