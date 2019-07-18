package com.br.marte.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.br.marte.app.entity.OrdemServico;

public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long>{
	
	
	//QUERY COM JPQL
	@Query("SELECT u FROM OrdemServico u JOIN FETCH u.status where u.status.evento_id = :codigo")
	public List<OrdemServico> findStatus(@Param("codigo") Integer codigo);	
	
	
	//QUERY COM JPQL
	@Query(value = "SELECT SUM(CASE WHEN evento_id = 1000 THEN 1 ELSE 0 END) as NOVO ,\r\n" + 
			"SUM(CASE WHEN evento_id = 1100 THEN 1 ELSE 0 END) as DESENVOLVENDO ,\r\n" + 
			"SUM(CASE WHEN evento_id = 1200 THEN 1 ELSE 0 END) as HOMOLOGANDO ,\r\n" + 
			"SUM(CASE WHEN evento_id = 1300 THEN 1 ELSE 0 END) as PENDENTE,\r\n" + 
			"SUM(CASE WHEN evento_id = 1400 THEN 1 ELSE 0 END) as GERENCIA,\r\n" + 
			"SUM(CASE WHEN evento_id = 9999 THEN 1 ELSE 0 END) as FECHADO, \r\n" + 
			"SUM(CASE WHEN EXTRACT(MONTH FROM dt_commit) = 1 THEN 1 ELSE 0 END) as JAN,\r\n" + 
			"SUM(CASE WHEN EXTRACT(MONTH FROM dt_commit) = 2 THEN 1 ELSE 0 END) as FEV,\r\n" + 
			"SUM(CASE WHEN EXTRACT(MONTH FROM dt_commit) = 3 THEN 1 ELSE 0 END) as MAR,\r\n" + 
			"SUM(CASE WHEN EXTRACT(MONTH FROM dt_commit) = 4 THEN 1 ELSE 0 END) as ABR,\r\n" + 
			"SUM(CASE WHEN EXTRACT(MONTH FROM dt_commit) = 5 THEN 1 ELSE 0 END) as MAI,\r\n" + 
			"SUM(CASE WHEN EXTRACT(MONTH FROM dt_commit) = 6 THEN 1 ELSE 0 END) as JUN,\r\n" + 
			"SUM(CASE WHEN EXTRACT(MONTH FROM dt_commit) = 7 THEN 1 ELSE 0 END) as JUL,\r\n" + 
			"SUM(CASE WHEN EXTRACT(MONTH FROM dt_commit) = 8 THEN 1 ELSE 0 END) as AGO,\r\n" + 
			"SUM(CASE WHEN EXTRACT(MONTH FROM dt_commit) = 9 THEN 1 ELSE 0 END) as SET,\r\n" + 
			"SUM(CASE WHEN EXTRACT(MONTH FROM dt_commit) = 10 THEN 1 ELSE 0 END) as OUT,\r\n" + 
			"SUM(CASE WHEN EXTRACT(MONTH FROM dt_commit) = 11 THEN 1 ELSE 0 END) as NOV,\r\n" + 
			"SUM(CASE WHEN EXTRACT(MONTH FROM dt_commit) = 12 THEN 1 ELSE 0 END) as DEZ"
			+ " FROM tb_servico u ", nativeQuery = true)
	public List<Object[]> findStatusStatic();	
	
}
