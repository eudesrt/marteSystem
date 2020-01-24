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
	@Query("SELECT u FROM OrdemServico u where u.os = :codigo")
	public OrdemServico findOrdemServicoIdBy(@Param("codigo") Integer codigo);	
	
	
	//QUERY COM JPQL
	@Query(value = "SELECT SUM(CASE WHEN evento_id = 1000 THEN 1 ELSE 0 END) as NOVO," + 
			"SUM(CASE WHEN evento_id = 1100 THEN 1 ELSE 0 END) as DESENVOLVENDO," + 
			"SUM(CASE WHEN evento_id = 1200 THEN 1 ELSE 0 END) as HOMOLOGANDO," + 
			"SUM(CASE WHEN evento_id = 1300 THEN 1 ELSE 0 END) as PENDENTE," + 
			"SUM(CASE WHEN evento_id = 1400 THEN 1 ELSE 0 END) as GERENCIA," + 
			"SUM(CASE WHEN evento_id = 9999 THEN 1 ELSE 0 END) as FECHADO" 
			+ " FROM tb_servico u" , nativeQuery = true)
	public List<Object[]> findStatusStatic();		
	
	//QUERY COM JPQL
	@Query(value = "SELECT " + 
			"SUM(CASE WHEN EXTRACT(MONTH FROM dt_commit) =  1 THEN 1 ELSE 0 END) as JAN," + 
			"SUM(CASE WHEN EXTRACT(MONTH FROM dt_commit) =  2 THEN 1 ELSE 0 END) as FEV," + 
			"SUM(CASE WHEN EXTRACT(MONTH FROM dt_commit) =  3 THEN 1 ELSE 0 END) as MAR," + 
			"SUM(CASE WHEN EXTRACT(MONTH FROM dt_commit) =  4 THEN 1 ELSE 0 END) as ABR," + 
			"SUM(CASE WHEN EXTRACT(MONTH FROM dt_commit) =  5 THEN 1 ELSE 0 END) as MAI," + 
			"SUM(CASE WHEN EXTRACT(MONTH FROM dt_commit) =  6 THEN 1 ELSE 0 END) as JUN," + 
			"SUM(CASE WHEN EXTRACT(MONTH FROM dt_commit) =  7 THEN 1 ELSE 0 END) as JUL," + 
			"SUM(CASE WHEN EXTRACT(MONTH FROM dt_commit) =  8 THEN 1 ELSE 0 END) as AGO," + 
			"SUM(CASE WHEN EXTRACT(MONTH FROM dt_commit) =  9 THEN 1 ELSE 0 END) as SET," + 
			"SUM(CASE WHEN EXTRACT(MONTH FROM dt_commit) = 10 THEN 1 ELSE 0 END) as OUT," + 
			"SUM(CASE WHEN EXTRACT(MONTH FROM dt_commit) = 11 THEN 1 ELSE 0 END) as NOV," + 
			"SUM(CASE WHEN EXTRACT(MONTH FROM dt_commit) = 12 THEN 1 ELSE 0 END) as DEZ"
			+ " FROM tb_servico u"
			+ " WHERE EXTRACT(YEAR  FROM dt_commit) = EXTRACT(YEAR  FROM now()) AND evento_id = 9999 " , nativeQuery = true)
	public List<Object[]> findAnalyticsDate();		
	
	//QUERY COM JPQL
	@Query(value = "SELECT " + 
			"SUM(CASE WHEN DT_VENC < dt_commit THEN 1 ELSE 0 END) as FORA_DO_PRAZO ," + 
			"SUM(CASE WHEN DT_VENC >= dt_commit THEN 1 ELSE 0 END) as DENTRO_PRAZO " + 
			"FROM TB_SERVICO\r\n" + 
			"WHERE evento_id = 9999" + 
			" AND dt_commit is not null" + 
			" AND EXTRACT(MONTH FROM dt_commit) = EXTRACT(MONTH FROM now()) ", nativeQuery = true)
	public List<Object[]> findDateStatic();	
	
	
	
	
	
	
}
