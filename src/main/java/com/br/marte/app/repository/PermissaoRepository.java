package com.br.marte.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.marte.app.entity.Grupo;
import com.br.marte.app.entity.Permissao;

public interface PermissaoRepository  extends JpaRepository<Permissao, Long>{
	
	List<Permissao> findByGruposIn(Grupo grupoEntity);

}
