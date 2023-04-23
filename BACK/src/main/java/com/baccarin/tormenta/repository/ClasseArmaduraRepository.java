package com.baccarin.tormenta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.baccarin.tormenta.domain.ClasseArmadura;

@Repository
public interface ClasseArmaduraRepository extends JpaRepository<ClasseArmadura, Long> {

}
