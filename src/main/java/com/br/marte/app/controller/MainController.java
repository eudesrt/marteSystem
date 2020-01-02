package com.br.marte.app.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.br.marte.app.entity.OrdemServico;
import com.br.marte.app.model.StaticModel;
import com.br.marte.app.repository.OrdemServicoRepository;
import com.br.marte.app.repository.StatusRepository;
import com.br.marte.app.service.OrdermServicoByTi;
import com.br.marte.app.service.UsuarioService;

@Controller
public class MainController {

	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	@Autowired
	private StatusRepository statusRepository;
	
	@Autowired
	private UsuarioService usuarioService;

	/***
	 * ESSE MÉTODO CARREGA A PAGINA(index.html) DE LOGIN DA NOSSA APLICAÇÃO
	 * 
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {

		return "index";
	}

	/***
	 * CARREGA À PAGINA INICIAL DA APLICAÇÃO APÓS EFETUARMOS O LOGIN
	 * 
	 * @return
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Model model) {
		recebeNovaOS();
		
		List<Object[]> statuStatic = this.ordemServicoRepository.findStatusStatic();
		List<Object[]> analyticsDate = this.ordemServicoRepository.findAnalyticsDate();		
		List<Object[]> dateStatic = this.ordemServicoRepository.findDateStatic();

		Integer novo = 0;
		Integer desenvolvendo = 0;
		Integer homologando = 0;
		Integer pendente = 0;
		Integer gerencia = 0;
		Integer fechado = 0;
		Integer jan = 0;
		Integer fev = 0;
		Integer mar = 0;
		Integer abr = 0;
		Integer mai = 0;
		Integer jun = 0;
		Integer jul = 0;
		Integer ago = 0;
		Integer set = 0;
		Integer out = 0;
		Integer nov = 0;
		Integer dez = 0;
		Integer slaDentro = 0;
		Integer slaFora = 0;

		for (Object[] s : statuStatic) {
			novo = Integer.valueOf(s[0].toString()) ;
			desenvolvendo = Integer.valueOf(s[1].toString());
			homologando = Integer.valueOf(s[2].toString());
			pendente = Integer.valueOf(s[3].toString());
			gerencia = Integer.valueOf(s[4].toString());
			fechado = Integer.valueOf(s[5].toString());
		}
		
		
		for(Object[] s : analyticsDate) {
			jan = Integer.valueOf(s[0].toString());
			fev = Integer.valueOf(s[1].toString());
			mar = Integer.valueOf(s[2].toString());
			abr = Integer.valueOf(s[3].toString());
			mai = Integer.valueOf(s[4].toString());
			jun = Integer.valueOf(s[5].toString());
			jul = Integer.valueOf(s[6].toString());
			ago = Integer.valueOf(s[7].toString());
			set = Integer.valueOf(s[8].toString());
			out = Integer.valueOf(s[9].toString());
			nov = Integer.valueOf(s[10].toString());
			dez = Integer.valueOf(s[11].toString());
		}
		

		for (Object[] s : dateStatic) {
			 slaFora = s[0] != null ? Integer.valueOf(s[0].toString()) : 0;
			 slaDentro= s[1] != null ? Integer.valueOf(s[1].toString()) : 0;
		}		

		model.addAttribute("staticModel", new StaticModel(novo, desenvolvendo, pendente, homologando, gerencia, fechado,
				jan, fev, mar, abr, mai, jun, jul, ago, set, out, nov, dez, slaDentro, slaFora));
		System.out.println("slaDentro " + slaDentro + " slaFora " + slaFora);

		return "home";
	}

	public void recebeNovaOS() {

		OrdermServicoByTi ordermServicoByTi = new OrdermServicoByTi();
		OrdemServico ordemServico = null;
		String[] info = ordermServicoByTi.JavaHttpUrlConnectionReader().toString().split(",");


		for (String i : info) {
			OrdemServico ordemServicoEntity = new OrdemServico();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

			Integer codigo = 0;
			String[] os = i.toString().split(";");

			codigo = Integer.valueOf(os[0]);
			LocalDate dateVenc = LocalDate.parse(os[6].trim(),formatter);

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
				
				if(ordemServico.getStatus().equals(statusRepository.getOne(1200)) || ordemServico.getStatus().equals(statusRepository.getOne(1300))) {
					System.out.println("ATUALIZANDO PEDIDNO " + codigo);
					
					ordemServico.setStatus(statusRepository.getOne(1000));
					ordemServico.setDt_venc(dateVenc);
					ordemServico.setDt_homologacao(null);
					
					this.ordemServicoRepository.saveAndFlush(ordemServico);
				}
				
			}
		}
	}

	/***
	 * MOSTRA UM PAGINA COM A MENSAGEM DE ACESSO NEGADO QUANDO O USUARIO NÃO TIVER
	 * PERMISSÃO DE ACESSAR UMA DETERMINADA FUNÇÃO DO SISTEMA
	 * 
	 * @return
	 */
	@RequestMapping(value = "/acessoNegado", method = RequestMethod.GET)
	public String acessoNegado() {

		return "acessoNegado";
	}
}
