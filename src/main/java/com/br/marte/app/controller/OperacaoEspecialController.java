package com.br.marte.app.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.br.marte.app.commonService.FileHandelService;
import com.br.marte.app.model.OrdemServicoModel;
import com.br.marte.app.service.OperacaoEspecialService;
import com.br.marte.app.service.OrdemServicoService;

@Controller
@RequestMapping("/operacaoEspecial")
public class OperacaoEspecialController {
	
	/** INJETANDO O OBJETO OrdemServicoService */
	@Autowired
	private OrdemServicoService ordemServicoService;
	
	@Autowired
	private OperacaoEspecialService operacaoEspecialService;
	
	private final ServletContext context;
	private final FileHandelService fileHandelService;

	@Autowired
	public OperacaoEspecialController(ServletContext context, FileHandelService fileHandelService) {
		this.context = context;
		this.fileHandelService = fileHandelService;
	}
	
	@GetMapping(value = "/excel")
	public void allExcel(HttpServletRequest request, HttpServletResponse response,	RedirectAttributes redirectAttributes) throws IOException {
		List<OrdemServicoModel> ordemservico = this.ordemServicoService.findOrdemServico();

		String nomeArquivo = this.operacaoEspecialService.createExcell(ordemservico, context, request, response);

		String fullPath = request.getServletContext().getRealPath(nomeArquivo);

		fileHandelService.filedownload(fullPath, response, nomeArquivo);
	}

}
