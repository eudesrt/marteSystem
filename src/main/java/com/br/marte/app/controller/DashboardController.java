package com.br.marte.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.br.marte.app.model.StaticModel;
import com.br.marte.app.repository.OrdemServicoRepository;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
	
	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	/***
	 * CARREGA À PAGINA INICIAL DA APLICAÇÃO APÓS EFETUARMOS O LOGIN
	 * 
	 * @return
	 */
	@RequestMapping(value = "/painel", method = RequestMethod.GET)
	public String home(@RequestParam("ano") Integer ano, Model model) {

		List<Object[]> analyticsDate = this.ordemServicoRepository.findAnalyticsDateYear(ano);
		List<Object[]> dateStatic = this.ordemServicoRepository.findDateStaticYear(ano);

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

		for (Object[] s : analyticsDate) {
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
			slaDentro = s[1] != null ? Integer.valueOf(s[1].toString()) : 0;
		}

		model.addAttribute("staticModel", new StaticModel(jan, fev, mar, abr, mai, jun, jul, ago, set, out, nov, dez, slaDentro, slaFora, ano));

		return "painel";
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
