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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.br.marte.app.entity.OrdemServico;
import com.br.marte.app.model.OrdemServicoModel;
import com.br.marte.app.model.StatusModel;
import com.br.marte.app.repository.OrdemServicoRepository;
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

	@Autowired
	private OrdemServicoRepository ordemServicoRepository;

	@RequestMapping(value = "/novoCadastro", method = RequestMethod.GET)
	public ModelAndView novoCadastro(Model model) {

		model.addAttribute("ordemServicoModel", new OrdemServicoModel());

		return new ModelAndView("novaOrdemServico");
	}
	
	public Integer retornoPagina = 1;

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
	public ModelAndView salvarOrdemServico(@ModelAttribute @Valid OrdemServicoModel ordemServicoModel,
			final BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		String message = "";
		boolean valida = true;
		/*
		 * VERIFICA SE TEM ALGUM ERRO (@NotEmpty), SE TIVER ALGUM ERRO DEVEMOS RETORNAR
		 * PARA A MESMA P�?GINA PARA O USUARIO CORRIGIR
		 */
		if (result.hasErrors()) {

			List<StatusModel> statusModel = statusService.consultarStatus();

			/* ADICIONA O GRUPOS QUE VÃO SER MOSTRADOS NA P�?GINA */
			model.addAttribute("status", statusModel);

			/* ADICIONA OS DADOS DO USUARIO PARA COLOCAR NO FORMUL�?RIO */
			model.addAttribute("ordemServicoModel", ordemServicoModel);

			/* RETORNA A VIEW */
			return new ModelAndView("ordemServico/novoCadastro");
		} else {

			/* SALVANDO UM NOVO REGISTRO */

			OrdemServico ordemServicosEntity = ordemServicoRepository.findOrdemServicoIdBy(ordemServicoModel.getOs());

			if (ordemServicosEntity == null) {
				ordemServicoService.salvarOrdemServico(ordemServicoModel);
				message = "OS salvo com sucesso!";

			} else {
				message = "A OS " + ordemServicoModel.getOs() + " Já existe no sistema !!!!";
				valida = false;
			}

		}

		ModelAndView modelAndView = new ModelAndView("redirect:/ordemServico/novoCadastro");

		/*
		 * PASSANDO O ATRIBUTO PARA O ModelAndView QUE VAI REALIZAR O REDIRECIONAMENTO
		 * COM A MENSAGEM DE SUCESSO
		 */
		redirectAttributes.addFlashAttribute("msg_valida", valida);
		redirectAttributes.addFlashAttribute("msg_resultado", message);

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
	public ModelAndView consultarOrdemServico(@RequestParam("codigo") Integer codigo, Model model) {

		/* CONSULTA USUARIOS CADASTRADOS */
		model.addAttribute("ordemServicoModel", this.ordemServicoService.consultarOrdemServico(codigo));

		if (codigo.equals(1000)) {
			model.addAttribute("status", "Nova");
		} else if (codigo.equals(1100)) {
			model.addAttribute("status", "Em Desenvolvimento");
		} else if (codigo.equals(1200)) {
			model.addAttribute("status", "Em Homologação");
		} else if (codigo.equals(1300)) {
			model.addAttribute("status", "Pedente Informação");
		} else if (codigo.equals(1400)) {
			model.addAttribute("status", "Na Gerência");
		} else if (codigo.equals(9998)) {
			model.addAttribute("status", "Cancelada");
		} else if (codigo.equals(9999)) {
			model.addAttribute("status", "Finalizadas");
		} else if (codigo.equals(2019)) {
			model.addAttribute("status", "Finalizadas 2019");
		} else if (codigo.equals(2020)) {
			model.addAttribute("status", "Finalizadas 2020");
		} else if (codigo.equals(1)) {
			model.addAttribute("status", "Fora do Prazo Mês Vigente");
		} else if (codigo.equals(2)) {
			model.addAttribute("status", "Fora do Prazo Todas");
		} else if (codigo.equals(3)) {
			model.addAttribute("status", "Finalizadas no Mês Vigente");
		} else if (codigo.equals(4)) {
			model.addAttribute("status", "Diario");
		} else {
			model.addAttribute("status", "Todas");
		}

		/* RETORNA A VIEW */
		return new ModelAndView("consultarOrdemServico");
	}

	@RequestMapping(value = "/editarOrdemServico", method = RequestMethod.GET)
	public ModelAndView editarOrdemServico(@RequestParam("codigo") Long codigo, Model model) {

		/* CONSULTA OS STATUS(EVENTOS) CADASTRADOS */
		List<StatusModel> statusModel = statusService.consultarStatus();

		/* CONSULTA O ORDEM DE SERVICO PELO CÓDIGO */
		OrdemServicoModel ordemServicoModel = this.ordemServicoService.consultarOrdemServico(codigo);

		/* ADICIONANDO STATUS PARA MOSTRAR NA PAGINA(VIEW) */
		model.addAttribute("statusModel", statusModel);

		/* ADICIONANDO INFORMAÇÕES DA ORDEM DE SERVICO PARA MOSTRAR NA PAGINA(VIEW) */
		model.addAttribute("ordemServicoModel", ordemServicoModel);

		/* CHAMA A VIEW /src/main/resources/templates/editarOrdemServico.html */
		return new ModelAndView("editarOrdemServico");
	}

	@RequestMapping(value = "/salvarAlteracao", method = RequestMethod.POST)
	public ModelAndView salvarAlteracao(@ModelAttribute @Valid OrdemServicoModel ordemServicoModel,	final BindingResult result, Model model, RedirectAttributes redirectAttributes) {

		ModelAndView modelAndView = null;
		if (ordemServicoModel.getStatus().equals(9999)) {
			if (ordemServicoModel.getDtHomologacao() == null) {
				redirectAttributes.addFlashAttribute("msg_valida", false);
				redirectAttributes.addFlashAttribute("msg_resultado", "A Data de Homologação e necessária pra finalizar a ordem de serviço.");

				modelAndView = new ModelAndView("redirect:/ordemServico/editarOrdemServico?codigo=" + ordemServicoModel.getCodigo());

				return modelAndView;
			}
		}

		/* SALVANDO AS INFORMAÇÕES ALTERADAS DE ORDEM DE SERVICO */
		ordemServicoService.alterarOrdemServico(ordemServicoModel);

		
		redirectAttributes.addFlashAttribute("msg_valida", true);
		redirectAttributes.addFlashAttribute("msg_resultado", "Alterado com sucesso");
		redirectAttributes.addFlashAttribute("pagina", "2");

		/*
		 * APÓS SALVAR VAMOS REDIRICIONAR O ORDEM DE SERVICO PARA A PAGINA DE CONSULTA
		 */
		modelAndView = new ModelAndView("redirect:/ordemServico/editarOrdemServico?codigo=" + ordemServicoModel.getCodigo());
		// modelAndView = new
		// ModelAndView("redirect:/ordemServico/consultarOrdemServico?codigo=" +
		// ordemServicoModel.getStatus());

		/* RETORNANDO A VIEW */
		return modelAndView;
	}

	public Integer getRetornoPagina() {
		return retornoPagina;
	}

	public void setRetornoPagina(Integer retornoPagina) {
		this.retornoPagina = retornoPagina;
	}

}
