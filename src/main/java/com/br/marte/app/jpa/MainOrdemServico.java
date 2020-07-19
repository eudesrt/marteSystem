package com.br.marte.app.jpa;

import java.util.Optional;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.br.marte.app.Application;
import com.br.marte.app.entity.OrdemServico;
import com.br.marte.app.repository.OrdemServicoRepository;

public class MainOrdemServico {
	

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(Application.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		OrdemServicoRepository ordemServico = applicationContext.getBean(OrdemServicoRepository.class);
		
		
		Optional<OrdemServico> o = ordemServico.findByos(16840);
		
		System.out.println(o.get());

	}

}
