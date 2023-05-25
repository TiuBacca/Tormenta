package com.baccarin.tormenta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.baccarin.tormenta.domain.Classe;
import com.baccarin.tormenta.vo.classe.ClasseResponse;

@Repository
public interface ClasseRepository extends JpaRepository<Classe, Long> {

	@Query(" select new com.baccarin.tormenta.vo.classe.ClasseResponse (c.id, c.nome) from Classe c where c.id > 0 ")
	public List<ClasseResponse> buscarListaClasses();
	
	List<Classe> findByNome(String nome);

}
