package com.br.marte.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.marte.app.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Usuario findByLogin(String login);
	
}
