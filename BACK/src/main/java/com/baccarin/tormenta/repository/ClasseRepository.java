package com.baccarin.tormenta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.baccarin.tormenta.domain.Classe;

@Repository
public interface ClasseRepository extends JpaRepository<Classe, Long> {

}
