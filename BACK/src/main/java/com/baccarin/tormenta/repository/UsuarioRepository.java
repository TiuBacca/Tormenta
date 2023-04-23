package com.baccarin.tormenta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.baccarin.tormenta.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
