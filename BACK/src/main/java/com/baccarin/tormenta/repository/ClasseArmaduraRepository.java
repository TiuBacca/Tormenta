package com.baccarin.tormenta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.baccarin.tormenta.domain.ClasseArmadura;
import com.baccarin.tormenta.vo.classeArmadura.ClasseArmaduraResponse;

@Repository
public interface ClasseArmaduraRepository extends JpaRepository<ClasseArmadura, Long> {

	@Query(" select new com.baccarin.tormenta.vo.classeArmadura.ClasseArmaduraResponse (ca.id , ca.total) from ClasseArmadura  ca "
			+ "where ca.personagem.id =:idPersonagem")
	ClasseArmaduraResponse buscarClasseArmaduraByPersonagemId(@Param("idPersonagem") Long idPersonagem);

	
}
