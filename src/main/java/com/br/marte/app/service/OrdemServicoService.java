package com.br.marte.app.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.br.marte.app.entity.OrdemServico;
import com.br.marte.app.model.OrdemServicoModel;
import com.br.marte.app.repository.OrdemServicoRepository;
import com.br.marte.app.repository.StatusRepository;

@Component
public class OrdemServicoService {

	@Autowired
	private OrdemServicoRepository ordemServicoRepository;

	@Autowired
	private StatusRepository statusRepository;

	@Autowired
	private UsuarioService usuarioService;
	
	/***
	 * SALVA UM NOVA ORDEM DE SERVICO
	 * 
	 * @param ordemServioModel
	 */
	public void salvarOrdemServico(OrdemServicoModel ordemServicoModel) {

		OrdemServico ordemServicoEntity = new OrdemServico();
		LocalDate localDate = LocalDate.now();

		ordemServicoEntity.setOs(ordemServicoModel.getOs());
		ordemServicoEntity.setTitulo(ordemServicoModel.getTitulo());
		ordemServicoEntity.setDt_entrada(localDate);
		ordemServicoEntity.setDt_venc(ordemServicoModel.getDtVencimento());
		ordemServicoEntity.setStatus(statusRepository.getOne(1000));
		ordemServicoEntity.setDescricao(ordemServicoModel.getDescricao() != null ? ordemServicoModel.getDescricao() : "");
		ordemServicoEntity.setId_usuario(usuarioService.usuarioEntity);
		ordemServicoEntity.setSolicitante(ordemServicoModel.getSolicitante());
		ordemServicoEntity.setDepartamento(ordemServicoModel.getDepartamento());
		ordemServicoEntity.setTipoSistema(ordemServicoModel.getTipoSistema());
		ordemServicoEntity.setTipoOs(ordemServicoModel.getTipoOs());

		/* SALVANDO O REGISTRO */
		this.ordemServicoRepository.save(ordemServicoEntity);
	}
	
	/***
	 * CONSULTA NOVA ORDEM DE SERVICO CADASTRADOS
	 * 
	 * @return
	 */
	public List<OrdemServicoModel> consultarOrdemServico(Integer codigo) {
		List<OrdemServicoModel> ordemServicoModel = new ArrayList<OrdemServicoModel>();

		List<OrdemServico> ordemServicosEntity = null;

		if (codigo == null || codigo == 0) {
			ordemServicosEntity = this.ordemServicoRepository.findAll(Sort.by(Sort.Direction.DESC, "codigo"));
		} else if (codigo == 1) {
			ordemServicosEntity = this.ordemServicoRepository.findForaPrazoMes();
		} else if (codigo == 2) {
			ordemServicosEntity = this.ordemServicoRepository.findForaPrazoTodos();
		} else if (codigo == 3) {
			ordemServicosEntity = this.ordemServicoRepository.findStatusMonth(9999);
		} else if (codigo == 2019) {
			ordemServicosEntity = this.ordemServicoRepository.findStatusYear(9999, codigo);
		} else if (codigo == 2020) {
			ordemServicosEntity = this.ordemServicoRepository.findStatusYear(9999, codigo);
		} else {
			ordemServicosEntity = this.ordemServicoRepository.findStatus(codigo);
		}

		ordemServicosEntity.forEach(ordemServicoEntity -> {

			ordemServicoModel.add(new OrdemServicoModel(ordemServicoEntity.getCodigo(), ordemServicoEntity.getOs(),
					ordemServicoEntity.getTitulo(), ordemServicoEntity.getDt_entrada(),
					ordemServicoEntity.getDt_homologacao(), ordemServicoEntity.getDt_commit(),
					ordemServicoEntity.getDt_venc(), ordemServicoEntity.getStatus().getEvento_id(),
					ordemServicoEntity.getId_usuario().getCodigo().intValue(), ordemServicoEntity.getDescricao(),
					ordemServicoEntity.getSolicitante(), ordemServicoEntity.getDepartamento(), ordemServicoEntity.getTipoSistema(), ordemServicoEntity.getTipoOs()));
		});

		return ordemServicoModel;
	}

	public List<OrdemServicoModel> findOrdemServico() {

		List<OrdemServico> ordemServicosEntity = null;

		ordemServicosEntity = this.ordemServicoRepository.findAll(Sort.by(Sort.Direction.DESC, "os"));

		List<OrdemServicoModel> ordemServicoModel = new ArrayList<OrdemServicoModel>();

		ordemServicosEntity.forEach(ordemServicoEntity -> {

			ordemServicoModel.add(new OrdemServicoModel(ordemServicoEntity.getCodigo(), ordemServicoEntity.getOs(),
					ordemServicoEntity.getTitulo(), ordemServicoEntity.getDt_entrada(),
					ordemServicoEntity.getDt_homologacao(), ordemServicoEntity.getDt_commit(),
					ordemServicoEntity.getDt_venc(), ordemServicoEntity.getStatus().getEvento_id(),
					ordemServicoEntity.getId_usuario().getCodigo().intValue(), ordemServicoEntity.getDescricao(),
					ordemServicoEntity.getSolicitante(), ordemServicoEntity.getDepartamento(), ordemServicoEntity.getTipoSistema(), ordemServicoEntity.getTipoOs()));
		});

		return ordemServicoModel;

	}

	public OrdemServicoModel consultarOrdemServico(Long codigo) {

		OrdemServico ordemServicoEntity = this.ordemServicoRepository.getOne(codigo);

		return new OrdemServicoModel(ordemServicoEntity.getCodigo(), ordemServicoEntity.getOs(),
				ordemServicoEntity.getTitulo(), ordemServicoEntity.getDt_entrada(),
				ordemServicoEntity.getDt_homologacao(), ordemServicoEntity.getDt_commit(),
				ordemServicoEntity.getDt_venc(), ordemServicoEntity.getStatus().getEvento_id(),
				ordemServicoEntity.getId_usuario().getCodigo().intValue(), ordemServicoEntity.getDescricao(),
				ordemServicoEntity.getSolicitante(), ordemServicoEntity.getDepartamento(), ordemServicoEntity.getTipoSistema(), ordemServicoEntity.getTipoOs());

	}

	/**
	 * ALTERA AS INFORMAÇÕES DO USUARIO
	 */
	public void alterarOrdemServico(OrdemServicoModel ordemServicoModel) {

		OrdemServico ordemServicoEntity = this.ordemServicoRepository.getOne(ordemServicoModel.getCodigo());
		LocalDate dtHomologacao = null;
		LocalDate dtCommits = null;
		
		
		LocalDate localDate = LocalDate.now();
		String descricao = ordemServicoModel.getDescricao() != null ? ordemServicoModel.getDescricao() : "" ;
		if(!ordemServicoModel.getTempDescricao().isEmpty()) {
			if(descricao != "") {
				descricao = "-------------------------------"+localDate +"------------------------------- \n" + ordemServicoModel.getTempDescricao() +"\n" + descricao;
			}else {
				descricao = "-------------------------------"+localDate +"------------------------------- \n" + ordemServicoModel.getTempDescricao();
			}
			
		}

		if (ordemServicoModel.getStatus().equals(1200)) {
			if (ordemServicoModel.getDtHomologacao() != null) {
				dtHomologacao = ordemServicoModel.getDtHomologacao();
			} else {
				dtHomologacao = LocalDate.now();
			}
			ordemServicoEntity.setDt_homologacao(dtHomologacao);
		} else if (ordemServicoModel.getStatus().equals(9999)) {
			if (ordemServicoModel.getDtCommit() != null) {
				dtCommits = ordemServicoModel.getDtCommit();
			} else {
				dtCommits = LocalDate.now();
			}
			if (ordemServicoModel.getDtHomologacao() != null) {
				dtHomologacao = ordemServicoModel.getDtHomologacao();
				ordemServicoEntity.setDt_homologacao(dtHomologacao);
			}
			ordemServicoEntity.setDt_commit(dtCommits);
		} else if (ordemServicoModel.getStatus().equals(1000) || ordemServicoModel.getStatus().equals(9998)
				|| ordemServicoModel.getStatus().equals(1200) || ordemServicoModel.getStatus().equals(1100)
				|| ordemServicoModel.getStatus().equals(1300)) {
			dtCommits = null;
			dtHomologacao = null;
			ordemServicoEntity.setDt_commit(dtCommits);
			ordemServicoEntity.setDt_homologacao(dtHomologacao);
		}

		ordemServicoEntity.setOs(ordemServicoModel.getOs());
		ordemServicoEntity.setDt_entrada(ordemServicoModel.getDtEntrada());
		ordemServicoEntity.setDt_venc(ordemServicoModel.getDtVencimento());
		ordemServicoEntity.setTitulo(ordemServicoModel.getTitulo());
		ordemServicoEntity.setDescricao(descricao);
		ordemServicoEntity.setStatus(statusRepository.getOne(ordemServicoModel.getStatus().intValue()));
		ordemServicoEntity.setSolicitante(ordemServicoModel.getSolicitante());
		ordemServicoEntity.setTipoOs(ordemServicoModel.getTipoOs());
		ordemServicoEntity.setTipoSistema(ordemServicoModel.getTipoSistema());
		ordemServicoEntity.setDepartamento(ordemServicoModel.getDepartamento());

		/* SALVANDO ALTERAÇÃO DO REGISTRO */
		this.ordemServicoRepository.saveAndFlush(ordemServicoEntity);
	}

}
