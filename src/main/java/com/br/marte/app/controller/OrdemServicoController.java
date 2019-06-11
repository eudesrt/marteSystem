package com.br.marte.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.br.marte.app.model.OrdemServicoModel;
import com.br.marte.app.model.StatusModel;
import com.br.marte.app.service.OrdemServicoService;
import com.br.marte.app.service.StatusService;

@Controller
@RequestMapping("/ordemServico")
public class OrdemServicoController {
	
	
	/** INJETANDO O OBJETO GrupoService */
	@Autowired
	private StatusService statusService;
	
	/** INJETANDO O OBJETO OrdemServicoService */
	@Autowired
	private OrdemServicoService ordemServicoService;
	
	
	@RequestMapping(value = "/novoCadastro", method = RequestMethod.GET)
	public ModelAndView novoCadastro(Model model) {

		model.addAttribute("ordemServicoModel" , new OrdemServicoModel());

		return new ModelAndView("novaOrdemServico");
	}
	
	
	
	/***
	 * SALVA UMAM NOVA ORDEM DE SERVICO
	 * 
	 * @param ordemServicoModel
	 * @param result
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/salvarOrdemServico", method = RequestMethod.POST)
	public ModelAndView salvarOrdemServico(@ModelAttribute @Valid OrdemServicoModel ordemServicoModel, final BindingResult result,
			Model model, RedirectAttributes redirectAttributes) {

		/*
		 * VERIFICA SE TEM ALGUM ERRO (@NotEmpty), SE TIVER ALGUM ERRO DEVEMOS RETORNAR
		 * PARA A MESMA P�?GINA PARA O USU�?RIO CORRIGIR
		 */
		if (result.hasErrors()) {

			List<StatusModel> statusModel = statusService.consultarStatus();



			/* ADICIONA O GRUPOS QUE VÃO SER MOSTRADOS NA P�?GINA */
			model.addAttribute("status", statusModel);

			/* ADICIONA OS DADOS DO USU�?RIO PARA COLOCAR NO FORMUL�?RIO */
			model.addAttribute("ordemServicoModel", ordemServicoModel);

			/* RETORNA A VIEW */
			return new ModelAndView("ordemServico/novoCadastro");
		} else {

			/* SALVANDO UM NOVO REGISTRO */
			ordemServicoService.salvarOrdemServico(ordemServicoModel);

		}

		ModelAndView modelAndView = new ModelAndView("redirect:/ordemServico/novoCadastro");

		/*
		 * PASSANDO O ATRIBUTO PARA O ModelAndView QUE VAI REALIZAR O REDIRECIONAMENTO
		 * COM A MENSAGEM DE SUCESSO
		 */
		redirectAttributes.addFlashAttribute("msg_resultado", "OS salvo com sucesso!");

		/* REDIRECIONANDO PARA UM NOVO CADASTRO */
		return modelAndView;
	}
	
	
	/***
	 * CONSULTA TODOS ORDEM SERVICO CADASTRADOS NO SISTEMA
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/consultarOrdemServico", method = RequestMethod.GET)
	public ModelAndView consultarOrdemServico(Model model) {

		/* CONSULTA USUARIOS CADASTRADOS */
		model.addAttribute("ordemServicoModel", this.ordemServicoService.consultarOrdemServico());

		/* RETORNA A VIEW */
		return new ModelAndView("consultarOrdemServico");
	}

}
