package com.br.marte.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.marte.app.entity.Grupo;
import com.br.marte.app.entity.Usuario;

public interface GrupoRepository extends JpaRepository<Grupo, Long> {
	
	List<Grupo> findByUsuariosIn(Usuario usuarioEntity);

}
