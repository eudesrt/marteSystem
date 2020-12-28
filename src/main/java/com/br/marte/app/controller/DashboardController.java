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
			jan = s[0] != null ? Integer.valueOf(s[0].toString()) : 0;
			fev = s[1] != null ? Integer.valueOf(s[1].toString()) : 0;
			mar = s[2] != null ? Integer.valueOf(s[2].toString()) : 0;
			abr = s[3] != null ? Integer.valueOf(s[3].toString()) : 0;
			mai = s[4] != null ? Integer.valueOf(s[4].toString()) : 0;
			jun = s[5] != null ? Integer.valueOf(s[5].toString()) : 0;
			jul = s[6] != null ? Integer.valueOf(s[6].toString()) : 0;
			ago = s[7] != null ? Integer.valueOf(s[7].toString()) : 0;
			set = s[8] != null ? Integer.valueOf(s[8].toString()) : 0;
			out = s[9] != null ? Integer.valueOf(s[9].toString()) : 0;
			nov = s[10] != null ? Integer.valueOf(s[10].toString()) : 0;
			dez = s[11] != null ? Integer.valueOf(s[11].toString()) : 0;
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
