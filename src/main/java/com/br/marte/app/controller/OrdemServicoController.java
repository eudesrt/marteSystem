package com.br.marte.app.controller;

import java.time.LocalDate;
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
	
	
	public static void main(String[] args) {		
		
        LocalDate nineApr = LocalDate.parse("2019-06-11");        
         
        boolean isBefore = nineApr.isAfter(LocalDate.now());
        
        if(nineApr.isEqual(LocalDate.now())) {
        	System.out.println("Status :: Data dia");
        }else {
            isBefore = nineApr.isAfter(LocalDate.now());
            
            if(isBefore) {
            	System.out.println("Status :: Data Superior a data do dia");
            } else {
            	System.out.println("Status :: Data no prazo");
            }
        }  		
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
	public ModelAndView consultarOrdemServico(@RequestParam("codigo") Integer codigo , Model model) {

		/* CONSULTA USUARIOS CADASTRADOS */
		model.addAttribute("ordemServicoModel", this.ordemServicoService.consultarOrdemServico(codigo));

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
	public ModelAndView salvarAlteracao(@ModelAttribute @Valid OrdemServicoModel ordemServicoModel, final BindingResult result,
			Model model, RedirectAttributes redirectAttributes) {


			/* SALVANDO AS INFORMAÇÕES ALTERADAS DE ORDEM DE SERVICO */
		ordemServicoService.alterarOrdemServico(ordemServicoModel);
		

		/* APÓS SALVAR VAMOS REDIRICIONAR O ORDEM DE SERVICO PARA A PAGINA DE CONSULTA */
		ModelAndView modelAndView = new ModelAndView("redirect:/ordemServico/consultarOrdemServico?codigo=0");		
		

		/* RETORNANDO A VIEW */
		return modelAndView;
	}

}
