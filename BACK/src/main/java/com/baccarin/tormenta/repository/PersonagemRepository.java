package com.baccarin.tormenta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.baccarin.tormenta.domain.Personagem;
import com.baccarin.tormenta.vo.personagem.PersonagemPericiaResponse;
import com.baccarin.tormenta.vo.personagem.PersonagemResponse;

@Repository
public interface PersonagemRepository extends JpaRepository<Personagem, Long> {

	@Query(" select new  com.baccarin.tormenta.vo.personagem.PersonagemResponse ( p.id, p.nome, classe.nome, raca.nome) from Personagem p join p.classe classe join p.raca raca ")
	List<PersonagemResponse> findListTodosPersonagens();

	@Query(" select new  com.baccarin.tormenta.vo.personagem.PersonagemResponse ( p.id, p.nome, classe.id, classe.nome, classe.pontosBaseVida, classe.pontosBaseAtaque, raca.id, raca.nome, p.nivel, p.vidaAtual, p.vidaTotal) "
			+ "	 from Personagem p join p.classe classe join p.raca raca " + " where p.usuario.email like :email ")
	List<PersonagemResponse> findByUsuarioEmail(@Param("email") String email);

	@Query(" SELECT new com.baccarin.tormenta.vo.personagem.PersonagemPericiaResponse"
			+ "(p.id, p.nome , p.descricao , p.tipoModificador , coalesce (pp.outros,0), coalesce (pp.graduacao,0)) \n"
			+ "FROM Pericia p "
			+ "LEFT JOIN PericiaPersonagem pp ON p.id = pp.pericia.id AND pp.personagem.id = :idPersonagem; ")
	List<PersonagemPericiaResponse> buscaListaPersonagemPericia(@Param("idPersonagem") Long idPersonagem);
	
	
	
}
