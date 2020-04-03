package com.br.marte.app.repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.br.marte.app.entity.OrdemServico;
import com.br.marte.app.service.OrdermServicoByTi;
import com.br.marte.app.service.UsuarioService;

public class RecebeOrdemServico implements RecebeOrdemServicoImp {
	
	private OrdemServicoRepository ordemServicoRepository;	
	private StatusRepository statusRepository;	
	private UsuarioService usuarioService;	
	

	public RecebeOrdemServico(OrdemServicoRepository ordemServicoRepository,StatusRepository statusRepository,UsuarioService usuarioService ) {
		this.ordemServicoRepository = ordemServicoRepository;
		this.statusRepository = statusRepository;
		this.usuarioService = usuarioService;
	}

	@Override
	public void aplicar() {

		OrdermServicoByTi ordermServicoByTi = new OrdermServicoByTi();
		OrdemServico ordemServico = null;
		String[] info = ordermServicoByTi.JavaHttpUrlConnectionReader().toString().split(",");

		for (String i : info) {
			OrdemServico ordemServicoEntity = new OrdemServico();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

			Integer codigo = 0;
			String[] os = i.toString().split(";");

			codigo = Integer.valueOf(os[0]);
			LocalDate dateVenc = LocalDate.parse(os[6].trim(), formatter);

			ordemServico = ordemServicoRepository.findOrdemServicoIdBy(codigo);

			if (ordemServico == null) {
				System.out.println("CADASTRAR NOVA ORDEM DE SERVICOS " + codigo);
				LocalDate localDate = LocalDate.now();

				ordemServicoEntity.setOs(codigo);
				ordemServicoEntity.setTitulo(os[3].trim());
				ordemServicoEntity.setDt_entrada(localDate);
				ordemServicoEntity.setDt_venc(dateVenc);
				ordemServicoEntity.setStatus(statusRepository.getOne(1000));
				ordemServicoEntity.setId_usuario(usuarioService.usuarioEntity);

				this.ordemServicoRepository.save(ordemServicoEntity);
			} else {
				if (ordemServico.getStatus().equals(statusRepository.getOne(1200)) || ordemServico.getStatus().equals(statusRepository.getOne(1300))) {
					System.out.println("ATUALIZANDO PEDIDNO " + codigo);

					ordemServico.setStatus(statusRepository.getOne(1000));
					ordemServico.setDt_venc(dateVenc);
					ordemServico.setDt_homologacao(null);

					this.ordemServicoRepository.saveAndFlush(ordemServico);
				}
			}
		}
	}
}
