package com.br.marte.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.br.marte.app.model.GrupoModel;
import com.br.marte.app.model.UsuarioModel;
import com.br.marte.app.service.GrupoService;
import com.br.marte.app.service.UsuarioService;

/**
 * 
 * @author Rafael Eudes
 * 
 *         OBJETO RESPONS�?VEL POR REALIZAR AS ROTINAS DE USU�?RIO.
 * 
 */
@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	/** INJETANDO O OBJETO GrupoService */
	@Autowired
	private GrupoService grupoService;

	/** INJETANDO O OBJETO UsuarioService */
	@Autowired
	private UsuarioService usuarioService;

	/***
	 * CHAMA A FUNCIONALIDADE PARA CADASTRAR UM NOVO USU�?RIO NO SISTEMA
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/novoCadastro", method = RequestMethod.GET)
	public ModelAndView novoCadastro(Model model) {

		/* LISTA DE GRUPOS QUE VAMOS MOSTRAR NA P�?GINA */
		model.addAttribute("grupos", grupoService.consultarGrupos());

		/* OBJETO QUE VAMOS ATRIBUIR OS VALORES DOS CAMPOS */
		model.addAttribute("usuarioModel", new UsuarioModel());

		return new ModelAndView("novoCadastro");
	}

	/***
	 * SALVA UM NOVO USU�?RIO NO SISTEMA
	 * 
	 * @param usuarioModel
	 * @param result
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/salvarUsuario", method = RequestMethod.POST)
	public ModelAndView salvarUsuario(@ModelAttribute @Valid UsuarioModel usuarioModel, final BindingResult result,
			Model model, RedirectAttributes redirectAttributes) {

		/*
		 * VERIFICA SE TEM ALGUM ERRO (@NotEmpty), SE TIVER ALGUM ERRO DEVEMOS RETORNAR
		 * PARA A MESMA P�?GINA PARA O USU�?RIO CORRIGIR
		 */
		if (result.hasErrors()) {

			List<GrupoModel> gruposModel = grupoService.consultarGrupos();

			/*
			 * POSICIONANDO OS CKECKBOX SELECIONADOS
			 * 
			 * SE O SISTEMA ENCONTROU ALGUM ERRO DE VALIDAÇÃO DEVEMOS BUSCAR OS GRUPOS E
			 * MARCAR COMO SELECIONADO NOVAMENTE PARA MOSTRAR N�? P�?GINAS DA FORMA QUE O
			 * USU�?RIO ENVIO A REQUEST
			 */
			gruposModel.forEach(grupo -> {

				if (usuarioModel.getGrupos() != null && usuarioModel.getGrupos().size() > 0) {

					usuarioModel.getGrupos().forEach(grupoSelecionado -> {

						/* DEVEMOS MOSTRAR NA P�?GINA OS GRUPOS COM O CHECKBOX SELECIONADO */
						if (grupoSelecionado != null) {
							if (grupo.getCodigo().equals(grupoSelecionado))
								grupo.setChecked(true);
						}
					});
				}

			});

			/* ADICIONA O GRUPOS QUE VÃO SER MOSTRADOS NA P�?GINA */
			model.addAttribute("grupos", gruposModel);

			/* ADICIONA OS DADOS DO USU�?RIO PARA COLOCAR NO FORMUL�?RIO */
			model.addAttribute("usuarioModel", usuarioModel);

			/* RETORNA A VIEW */
			return new ModelAndView("novoCadastro");
		} else {

			/* SALVANDO UM NOVO REGISTRO */
			usuarioService.salvarUsuario(usuarioModel);

		}

		ModelAndView modelAndView = new ModelAndView("redirect:/usuario/novoCadastro");

		/*
		 * PASSANDO O ATRIBUTO PARA O ModelAndView QUE VAI REALIZAR O REDIRECIONAMENTO
		 * COM A MENSAGEM DE SUCESSO
		 */
		redirectAttributes.addFlashAttribute("msg_resultado", "Registro salvo com sucesso!");

		/* REDIRECIONANDO PARA UM NOVO CADASTRO */
		return modelAndView;
	}

	/***
	 * CONSULTA TODOS USU�?RIOS CADASTRADOS NO SISTEMA
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/consultar", method = RequestMethod.GET)
	public ModelAndView consultar(Model model) {

		/* CONSULTA USU�?RIOS CADASTRADOS */
		model.addAttribute("usuariosModel", this.usuarioService.consultarUsuarios());

		/* RETORNA A VIEW */
		return new ModelAndView("consultarCadastros");
	}

	/***
	 * EXCLUI UM REGISTRO DO SISTEMA PELO CÓDIGO
	 * 
	 * @param codigoUsuario
	 * @return
	 */
	@RequestMapping(value = "/excluir", method = RequestMethod.POST)
	public ModelAndView excluir(@RequestParam("codigoUsuario") Long codigoUsuario) {

		ModelAndView modelAndView = new ModelAndView("redirect:/usuario/consultar");

		/* EXCLUINDO O REGISTRO */
		this.usuarioService.excluir(codigoUsuario);

		/* RETORNANDO A VIEW */
		return modelAndView;
	}

	/***
	 * CONSULTA UM USU�?RIO PELO CÓDIGO PARA REALIZAR ALTERAÇÕES NAS INFORAMÇÕES
	 * CADASTRADAS.
	 * 
	 * @param codigoUsuario
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/editarCadastro", method = RequestMethod.GET)
	public ModelAndView editarCadastro(@RequestParam("codigoUsuario") Long codigoUsuario, Model model) {

		/* CONSULTA OS GRUPOS CADASTRADOS */
		List<GrupoModel> gruposModel = grupoService.consultarGrupos();

		/* CONSULTA O USU�?RIO PELO CÓDIGO */
		UsuarioModel usuarioModel = this.usuarioService.consultarUsuario(codigoUsuario);

		/* DEIXA SELECIONADO OS GRUPOS CADASTRADOS PARA O USU�?RIO */
		gruposModel.forEach(grupo -> {

			usuarioModel.getGrupos().forEach(grupoCadastrado -> {

				if (grupoCadastrado != null) {
					if (grupo.getCodigo().equals(grupoCadastrado))
						grupo.setChecked(true);
				}
			});

		});

		/* ADICIONANDO GRUPOS PARA MOSTRAR NA P�?GINA(VIEW) */
		model.addAttribute("grupos", gruposModel);

		/* ADICIONANDO INFORMAÇÕES DO USU�?RIO PARA MOSTRAR NA P�?GINA(VIEW) */
		model.addAttribute("usuarioModel", usuarioModel);

		/* CHAMA A VIEW /src/main/resources/templates/editarCadastro.html */
		return new ModelAndView("editarCadastro");
	}

	/***
	 * SALVA AS ALTERAÇÕES REALIZADAS NO CADASTRO DO USU�?RIO
	 * 
	 * @param usuarioModel
	 * @param result
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/salvarAlteracao", method = RequestMethod.POST)
	public ModelAndView salvarAlteracao(@ModelAttribute @Valid UsuarioModel usuarioModel, final BindingResult result,
			Model model, RedirectAttributes redirectAttributes) {

		boolean isErroNullCampos = false;

		/*
		 * AQUI ESTAMOS VERIFICANDO SE TEM ALGUM CAMPO QUE NÃO EST�? PREENCHIDO, MENOS O
		 * CAMPO DA SENHA, POIS SE O USU�?RIO NÃO INFORMAR VAMOS MANTER A SENHA J�?
		 * CADASTRADA
		 */
		for (FieldError fieldError : result.getFieldErrors()) {
			if (!fieldError.getField().equals("senha")) {
				isErroNullCampos = true;
			}
		}

		/*
		 * SE ENCONTROU ERRO DEVEMOS RETORNAR PARA A VIEW PARA QUE O USU�?RIO TERMINE DE
		 * INFORMAR OS DADOS
		 */
		if (isErroNullCampos) {

			List<GrupoModel> gruposModel = grupoService.consultarGrupos();

			gruposModel.forEach(grupo -> {

				if (usuarioModel.getGrupos() != null && usuarioModel.getGrupos().size() > 0) {

					/* DEIXA CHECADO OS GRUPOS QUE O USU�?RIO SELECIONOU */
					usuarioModel.getGrupos().forEach(grupoSelecionado -> {

						if (grupoSelecionado != null) {
							if (grupo.getCodigo().equals(grupoSelecionado))
								grupo.setChecked(true);
						}
					});
				}

			});

			/* ADICIONANDO GRUPOS PARA MOSTRAR NA P�?GINA(VIEW) */
			model.addAttribute("grupos", gruposModel);

			/*
			 * ADICIONANDO O OBJETO usuarioModel PARA MOSTRAR NA P�?GINA(VIEW) AS INFORMAÇÕES
			 * DO USU�?RIO
			 */
			model.addAttribute("usuarioModel", usuarioModel);

			/* RETORNANDO A VIEW */
			return new ModelAndView("editarCadastro");
		} else {

			/* SALVANDO AS INFORMAÇÕES ALTERADAS DO USU�?RIO */
			usuarioService.alterarUsuario(usuarioModel);

		}

		/* APÓS SALVAR VAMOS REDIRICIONAR O USU�?RIO PARA A PAGINA DE CONSULTA */
		ModelAndView modelAndView = new ModelAndView("redirect:/usuario/consultar");

		/* RETORNANDO A VIEW */
		return modelAndView;
	}

}
