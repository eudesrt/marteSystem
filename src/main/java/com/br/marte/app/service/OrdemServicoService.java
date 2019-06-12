package com.br.marte.app.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.br.marte.app.entity.Grupo;
import com.br.marte.app.entity.OrdemServico;
import com.br.marte.app.entity.Usuario;
import com.br.marte.app.model.OrdemServicoModel;
import com.br.marte.app.model.UsuarioModel;
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
		ordemServicoEntity.setId_usuario(usuarioService.usuarioEntity);

		/* SALVANDO O REGISTRO */
		this.ordemServicoRepository.save(ordemServicoEntity);
	}

	/***
	 * CONSULTA NOVA ORDEM DE SERVICO CADASTRADOS
	 * 
	 * @return
	 */
	public List<OrdemServicoModel> consultarOrdemServico() {

		List<OrdemServicoModel> ordemServicoModel = new ArrayList<OrdemServicoModel>();

		List<OrdemServico> ordemServicosEntity = this.ordemServicoRepository.findAll();

		ordemServicosEntity.forEach(ordemServicoEntity -> {
			

			ordemServicoModel.add(new OrdemServicoModel(ordemServicoEntity.getCodigo(), ordemServicoEntity.getOs(),
					ordemServicoEntity.getTitulo(), ordemServicoEntity.getDt_entrada(),
					ordemServicoEntity.getDt_homologacao(), ordemServicoEntity.getDt_commit(),
					ordemServicoEntity.getDt_venc(), ordemServicoEntity.getStatus().getEvento_id(),
					ordemServicoEntity.getId_usuario().getCodigo().intValue()));
		});

		return ordemServicoModel;
	}

	public OrdemServicoModel consultarOrdemServico(Long codigo) {

		OrdemServico ordemServicoEntity = this.ordemServicoRepository.getOne(codigo);

		return new OrdemServicoModel(ordemServicoEntity.getCodigo(), ordemServicoEntity.getOs(),
				ordemServicoEntity.getTitulo(), ordemServicoEntity.getDt_entrada(),
				ordemServicoEntity.getDt_homologacao(), ordemServicoEntity.getDt_commit(),
				ordemServicoEntity.getDt_venc(), ordemServicoEntity.getStatus().getEvento_id(),
				ordemServicoEntity.getId_usuario().getCodigo().intValue());

	}
	
	
	
	/**
	 * ALTERA AS INFORMAÇÕES DO USU�?RIO
	 */
	public void alterarOrdemServico(OrdemServicoModel ordemServicoModel) {

		OrdemServico ordemServicoEntity = this.ordemServicoRepository.getOne(ordemServicoModel.getCodigo());
		
		ordemServicoEntity.setOs(ordemServicoModel.getOs());
		ordemServicoEntity.setDt_entrada(ordemServicoModel.getDtEntrada());
		ordemServicoEntity.setDt_venc(ordemServicoModel.getDtVencimento());
		ordemServicoEntity.setTitulo(ordemServicoModel.getTitulo());
		ordemServicoEntity.setStatus(statusRepository.getOne(ordemServicoModel.getStatus().intValue()));
		ordemServicoEntity.setDt_commit(ordemServicoModel.getDtCommit());
		ordemServicoEntity.setDt_homologacao(ordemServicoModel.getDtHomologacao());


		/* SALVANDO ALTERAÇÃO DO REGISTRO */
		this.ordemServicoRepository.saveAndFlush(ordemServicoEntity);
	}

}
