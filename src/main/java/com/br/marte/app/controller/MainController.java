package com.br.marte.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.br.marte.app.model.StaticModel;
import com.br.marte.app.repository.OrdemServicoRepository;

@Controller
public class MainController {
	
	
	@Autowired
	private OrdemServicoRepository ordemServicoRepository;


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
	//	
		
		List<Object[]> statuStatic = this.ordemServicoRepository.findStatusStatic();		
		
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
		
		for(Object[] s:statuStatic) {
			
		
			novo = Integer.valueOf(s[0].toString());
			desenvolvendo = Integer.valueOf(s[1].toString());
			homologando = Integer.valueOf(s[2].toString());
			pendente = Integer.valueOf(s[3].toString());
			gerencia = Integer.valueOf(s[4].toString());
			fechado = Integer.valueOf(s[5].toString());
			jan = Integer.valueOf(s[6].toString());
			fev = Integer.valueOf(s[7].toString());
			mar = Integer.valueOf(s[8].toString()); 
			abr = Integer.valueOf(s[9].toString()); 
			mai = Integer.valueOf(s[10].toString()); 
			jun = Integer.valueOf(s[11].toString()); 
			jul = Integer.valueOf(s[12].toString()); 
			ago = Integer.valueOf(s[13].toString()); 
			set = Integer.valueOf(s[14].toString()); 
			out = Integer.valueOf(s[15].toString()); 
			nov = Integer.valueOf(s[16].toString()); 
			dez = Integer.valueOf(s[17].toString());	
		}
 		
		model.addAttribute("staticModel",  new StaticModel(novo, desenvolvendo , pendente , homologando, gerencia , fechado , jan, fev, mar, abr, mai, jun, jul, ago, set, out, nov, dez));

		return "home";
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
