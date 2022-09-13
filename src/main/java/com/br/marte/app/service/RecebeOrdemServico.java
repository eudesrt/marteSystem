package com.br.marte.app.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.br.marte.app.entity.OrdemServico;
import com.br.marte.app.model.JsonOrdemServicoRecebida;
import com.br.marte.app.model.JsonOrdemServicoRecebida.JsonOrdemServicoConsulta;
import com.br.marte.app.model.TokenFeedback;
import com.br.marte.app.model.TokenFeedbackCache;
import com.br.marte.app.repository.OrdemServicoRepository;
import com.br.marte.app.repository.StatusRepository;

public class RecebeOrdemServico implements RecebeOrdemServicoImp {

	private OrdemServicoRepository ordemServicoRepository;
	private StatusRepository statusRepository;
	private UsuarioService usuarioService;
	
	private static final Map<String, String> TIPO_ORDEM_SERVICO;
	
	static {
		TIPO_ORDEM_SERVICO = new HashMap<String, String>();
		TIPO_ORDEM_SERVICO.put("FLASH PHOENIX",			"PHOENIX");
		TIPO_ORDEM_SERVICO.put("WEB-SERVICE",			"WS PEGASUS");
		TIPO_ORDEM_SERVICO.put("BANCO DE DADOS",		"OUTROS");
		TIPO_ORDEM_SERVICO.put("FLASHCTE",				"OUTROS");
		
		
		TIPO_ORDEM_SERVICO.put("IMPORTACAO/EXPORTACAO",	"PROCESSADOR JALL");
		TIPO_ORDEM_SERVICO.put("WEB JALL",				"PROCESSADOR JALL");
		
	}

	public RecebeOrdemServico(OrdemServicoRepository ordemServicoRepository, StatusRepository statusRepository,
			UsuarioService usuarioService) {
		this.ordemServicoRepository = ordemServicoRepository;
		this.statusRepository = statusRepository;
		this.usuarioService = usuarioService;
	}

	@Override
	public void aplicar() {
		LocalDate localDate = LocalDate.now();

		OrdermServicoByTi ordermServicoByTi = new OrdermServicoByTi();
		OrdemServico ordemServico = null;

		String token = "";
		TokenFeedback tokenFeedbackCache = TokenFeedbackCache.getTokenFeedback("eudes");
		List<JsonOrdemServicoRecebida> jsonOrdemServicoRecebida = new ArrayList<JsonOrdemServicoRecebida>();
		
		if(tokenFeedbackCache != null && tokenFeedbackCache.getToken() != null) {
			
			token = tokenFeedbackCache.getToken();
			
			System.out.println("Executando postOrdemServico TOKEN CACHE");
			
		}else {
			token = ordermServicoByTi.postToken();
			 
			TokenFeedbackCache.addTokenFeedback(new TokenFeedback (token, "eudes"));
			
			System.out.println("Executando postOrdemServico TOKEN NEW");

		}
		
		jsonOrdemServicoRecebida = ordermServicoByTi.postOrdemServico(token);
		
		if(jsonOrdemServicoRecebida != null && (jsonOrdemServicoRecebida.size() >= 1 )) {
			
			for (JsonOrdemServicoRecebida i : jsonOrdemServicoRecebida) {
				OrdemServico ordemServicoEntity = new OrdemServico();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
				Integer codigo = 0;

				codigo = Integer.valueOf(i.getId());
				LocalDate dateVenc = LocalDate.parse(i.getDataVencimento(), formatter);

				ordemServico = ordemServicoRepository.findOrdemServicoIdBy(codigo);

				if (ordemServico == null) {
					System.out.println("CADASTRAR NOVA ORDEM DE SERVICOS " + codigo);
					
					String tipoSistema = TIPO_ORDEM_SERVICO.getOrDefault(i.getTipoSistema().toUpperCase(), null);

					ordemServicoEntity.setOs(codigo);
					ordemServicoEntity.setTipoSistema(tipoSistema);
					ordemServicoEntity.setTitulo(i.getTitulo());
					ordemServicoEntity.setDt_entrada(localDate);
					ordemServicoEntity.setDt_venc(dateVenc);
					ordemServicoEntity.setSolicitante(i.getSolicitante());
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
					}else {
						
						if(ordemServico != null) {
							//"id": 2200, "nome": "Com Desenvolvedor",
							if(i.getEvento() != null  && (i.getEvento().getId().equals(2200) )) {
								ordemServico.setStatus(statusRepository.getOne(1100));
								
								this.ordemServicoRepository.saveAndFlush(ordemServico);

							}
						}						
					}
				}
			}
		}
			
		List<OrdemServico> ordemServicos = ordemServicoRepository.findListaStatus(Arrays.asList(1000,1100));
		
		if (!ordemServicos.isEmpty()) {
			for (OrdemServico os : ordemServicos) {
				
				if(tokenFeedbackCache != null ) {
					token = tokenFeedbackCache.getToken();
				}else {
					token = ordermServicoByTi.postToken();
					 
					TokenFeedbackCache.addTokenFeedback(new TokenFeedback (token, "eudes"));
				}

				JsonOrdemServicoConsulta ordemServicoConsulta = ordermServicoByTi.postOrdemServicoConsulta(token, os.getOs());

				if (ordemServicoConsulta != null) {
					Integer eventos = ordemServicoConsulta.getContent() != null
							? ordemServicoConsulta.getContent().get(0) != null
									? ordemServicoConsulta.getContent().get(0).getEvento() != null
											? ordemServicoConsulta.getContent().get(0).getEvento()
													.getId() != null
															? ordemServicoConsulta.getContent().get(0).getEvento().getId()
															: null: null: null: null;

					if (eventos != null && (eventos.equals(1700) || eventos.equals(1300) )) {
						
						os.setStatus(statusRepository.getOne(1300));
						this.ordemServicoRepository.saveAndFlush(os);

						// "id": 1800,1900,2000,2100,2300,2400,2500,2600 , "nome": "* - Liberado
						// Homolog",
						
					} else if (eventos != null && (eventos.equals(1800) || eventos.equals(1900)
							|| eventos.equals(2000) || eventos.equals(2100) || eventos.equals(2300)
							|| eventos.equals(2400) || eventos.equals(2500) || eventos.equals(2600))) {

						os.setStatus(statusRepository.getOne(1200));
						os.setDt_homologacao(localDate);
						this.ordemServicoRepository.saveAndFlush(os);

					}
				}
			}
		}		
	}
}
