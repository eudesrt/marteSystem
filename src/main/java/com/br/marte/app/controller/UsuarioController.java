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
 *         OBJETO RESPONSÃ?VEL POR REALIZAR AS ROTINAS DE USUÃ?RIO.
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
	 * CHAMA A FUNCIONALIDADE PARA CADASTRAR UM NOVO USUÃ?RIO NO SISTEMA
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/novoCadastro", method = RequestMethod.GET)
	public ModelAndView novoCadastro(Model model) {

		/* LISTA DE GRUPOS QUE VAMOS MOSTRAR NA PÃ?GINA */
		model.addAttribute("grupos", grupoService.consultarGrupos());

		/* OBJETO QUE VAMOS ATRIBUIR OS VALORES DOS CAMPOS */
		model.addAttribute("usuarioModel", new UsuarioModel());

		return new ModelAndView("novoCadastro");
	}

	/***
	 * SALVA UM NOVO USUÃ?RIO NO SISTEMA
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
		 * PARA A MESMA PÃ?GINA PARA O USUÃ?RIO CORRIGIR
		 */
		if (result.hasErrors()) {

			List<GrupoModel> gruposModel = grupoService.consultarGrupos();

			/*
			 * POSICIONANDO OS CKECKBOX SELECIONADOS
			 * 
			 * SE O SISTEMA ENCONTROU ALGUM ERRO DE VALIDAÃ‡ÃƒO DEVEMOS BUSCAR OS GRUPOS E
			 * MARCAR COMO SELECIONADO NOVAMENTE PARA MOSTRAR NÃ? PÃ?GINAS DA FORMA QUE O
			 * USUÃ?RIO ENVIO A REQUEST
			 */
			gruposModel.forEach(grupo -> {

				if (usuarioModel.getGrupos() != null && usuarioModel.getGrupos().size() > 0) {

					usuarioModel.getGrupos().forEach(grupoSelecionado -> {

						/* DEVEMOS MOSTRAR NA PÃ?GINA OS GRUPOS COM O CHECKBOX SELECIONADO */
						if (grupoSelecionado != null) {
							if (grupo.getCodigo().equals(grupoSelecionado))
								grupo.setChecked(true);
						}
					});
				}

			});

			/* ADICIONA O GRUPOS QUE VÃƒO SER MOSTRADOS NA PÃ?GINA */
			model.addAttribute("grupos", gruposModel);

			/* ADICIONA OS DADOS DO USUÃ?RIO PARA COLOCAR NO FORMULÃ?RIO */
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
	 * CONSULTA TODOS USUÃ?RIOS CADASTRADOS NO SISTEMA
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/consultar", method = RequestMethod.GET)
	public ModelAndView consultar(Model model) {

		/* CONSULTA USUÃ?RIOS CADASTRADOS */
		model.addAttribute("usuariosModel", this.usuarioService.consultarUsuarios());

		/* RETORNA A VIEW */
		return new ModelAndView("consultarCadastros");
	}

	/***
	 * EXCLUI UM REGISTRO DO SISTEMA PELO CÃ“DIGO
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
	 * CONSULTA UM USUÃ?RIO PELO CÃ“DIGO PARA REALIZAR ALTERAÃ‡Ã•ES NAS INFORAMÃ‡Ã•ES
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

		/* CONSULTA O USUÃ?RIO PELO CÃ“DIGO */
		UsuarioModel usuarioModel = this.usuarioService.consultarUsuario(codigoUsuario);

		/* DEIXA SELECIONADO OS GRUPOS CADASTRADOS PARA O USUÃ?RIO */
		gruposModel.forEach(grupo -> {

			usuarioModel.getGrupos().forEach(grupoCadastrado -> {

				if (grupoCadastrado != null) {
					if (grupo.getCodigo().equals(grupoCadastrado))
						grupo.setChecked(true);
				}
			});

		});

		/* ADICIONANDO GRUPOS PARA MOSTRAR NA PÃ?GINA(VIEW) */
		model.addAttribute("grupos", gruposModel);

		/* ADICIONANDO INFORMAÃ‡Ã•ES DO USUÃ?RIO PARA MOSTRAR NA PÃ?GINA(VIEW) */
		model.addAttribute("usuarioModel", usuarioModel);

		/* CHAMA A VIEW /src/main/resources/templates/editarCadastro.html */
		return new ModelAndView("editarCadastro");
	}

	/***
	 * SALVA AS ALTERAÃ‡Ã•ES REALIZADAS NO CADASTRO DO USUÃ?RIO
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
		 * AQUI ESTAMOS VERIFICANDO SE TEM ALGUM CAMPO QUE NÃƒO ESTÃ? PREENCHIDO, MENOS O
		 * CAMPO DA SENHA, POIS SE O USUÃ?RIO NÃƒO INFORMAR VAMOS MANTER A SENHA JÃ?
		 * CADASTRADA
		 */
		for (FieldError fieldError : result.getFieldErrors()) {
			if (!fieldError.getField().equals("senha")) {
				isErroNullCampos = true;
			}
		}

		/*
		 * SE ENCONTROU ERRO DEVEMOS RETORNAR PARA A VIEW PARA QUE O USUÃ?RIO TERMINE DE
		 * INFORMAR OS DADOS
		 */
		if (isErroNullCampos) {

			List<GrupoModel> gruposModel = grupoService.consultarGrupos();

			gruposModel.forEach(grupo -> {

				if (usuarioModel.getGrupos() != null && usuarioModel.getGrupos().size() > 0) {

					/* DEIXA CHECADO OS GRUPOS QUE O USUÃ?RIO SELECIONOU */
					usuarioModel.getGrupos().forEach(grupoSelecionado -> {

						if (grupoSelecionado != null) {
							if (grupo.getCodigo().equals(grupoSelecionado))
								grupo.setChecked(true);
						}
					});
				}

			});

			/* ADICIONANDO GRUPOS PARA MOSTRAR NA PÃ?GINA(VIEW) */
			model.addAttribute("grupos", gruposModel);

			/*
			 * ADICIONANDO O OBJETO usuarioModel PARA MOSTRAR NA PÃ?GINA(VIEW) AS INFORMAÃ‡Ã•ES
			 * DO USUÃ?RIO
			 */
			model.addAttribute("usuarioModel", usuarioModel);

			/* RETORNANDO A VIEW */
			return new ModelAndView("editarCadastro");
		} else {

			/* SALVANDO AS INFORMAÃ‡Ã•ES ALTERADAS DO USUÃ?RIO */
			usuarioService.alterarUsuario(usuarioModel);

		}

		/* APÃ“S SALVAR VAMOS REDIRICIONAR O USUÃ?RIO PARA A PÃ?GINA DE CONSULTA */
		ModelAndView modelAndView = new ModelAndView("redirect:/usuario/consultar");

		/* RETORNANDO A VIEW */
		return modelAndView;
	}

}
