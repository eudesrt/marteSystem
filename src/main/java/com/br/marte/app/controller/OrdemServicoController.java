package com.br.marte.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.br.marte.app.model.OrdemServicoModel;

@Controller
@RequestMapping("/ordemServico")
public class OrdemServicoController {
	
	
	@RequestMapping(value = "/novoCadastro", method = RequestMethod.GET)
	public ModelAndView novoCadastro(Model model) {

		model.addAttribute("ordemServicoModel" , new OrdemServicoModel());

		return new ModelAndView("novaOrdemServico");
	}

}
