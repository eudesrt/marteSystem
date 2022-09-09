package com.br.marte.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.br.marte.app.model.StaticModel;
import com.br.marte.app.repository.OrdemServicoRepository;
import com.br.marte.app.repository.StatusRepository;
import com.br.marte.app.service.RecebeOrdemServico;
import com.br.marte.app.service.RecebeOrdemServicoImp;
import com.br.marte.app.service.UsuarioService;

@Controller
public class MainController {

	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	@Autowired
	private StatusRepository statusRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	RecebeOrdemServicoImp recebeOrdemServico;

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
	public ModelAndView home(Model model, RedirectAttributes redirectAttributes) {
		recebeOrdemServico = new RecebeOrdemServico(ordemServicoRepository, statusRepository, usuarioService);
		recebeOrdemServico.aplicar();
		
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
			novo = Integer.valueOf(s[0].toString());
			desenvolvendo = Integer.valueOf(s[1].toString());
			homologando = Integer.valueOf(s[2].toString());
			pendente = Integer.valueOf(s[3].toString());
			gerencia = Integer.valueOf(s[4].toString());
			fechado = Integer.valueOf(s[5].toString());
		}

		for (Object[] s : analyticsDate) {
			jan = Integer.valueOf(s[0] != null ? s[0].toString() : "0");
			fev = Integer.valueOf(s[1] != null ? s[1].toString() : "0");
			mar = Integer.valueOf(s[2] != null ? s[2].toString() : "0");
			abr = Integer.valueOf(s[3] != null ? s[3].toString() : "0");
			mai = Integer.valueOf(s[4] != null ? s[4].toString() : "0");
			jun = Integer.valueOf(s[5] != null ? s[5].toString() : "0");
			jul = Integer.valueOf(s[6] != null ? s[6].toString() : "0");
			ago = Integer.valueOf(s[7] != null ? s[7].toString() : "0");
			set = Integer.valueOf(s[8] != null ? s[8].toString() : "0");
			out = Integer.valueOf(s[9] != null ? s[9].toString() : "0");
			nov = Integer.valueOf(s[10] != null ? s[10].toString() : "0");
			dez = Integer.valueOf(s[11] != null ? s[11].toString() : "0");
		}

		for (Object[] s : dateStatic) {
			slaFora = s[0] != null ? Integer.valueOf(s[0].toString()) : 0;
			slaDentro = s[1] != null ? Integer.valueOf(s[1].toString()) : 0;
		}

		model.addAttribute("staticModel", new StaticModel(novo, desenvolvendo, pendente, homologando, gerencia, fechado,
				jan, fev, mar, abr, mai, jun, jul, ago, set, out, nov, dez, slaDentro, slaFora));
		
		System.out.println("slaDentro " + slaDentro + " slaFora " + slaFora);
		
		
		redirectAttributes.addFlashAttribute("msg_valida", true);
		redirectAttributes.addFlashAttribute("msg_resultado", " TESTE 2 " );


		return new ModelAndView("home");
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
