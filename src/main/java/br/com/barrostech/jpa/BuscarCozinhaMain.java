package br.com.barrostech.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import br.com.barrostech.AlgafoodApplication;
import br.com.barrostech.domain.entity.Cozinha;
import br.com.barrostech.domain.repository.CozinhaRepository;

public class BuscarCozinhaMain {
	
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		CozinhaRepository cadastroCozinha = applicationContext.getBean(CozinhaRepository.class);
		
		Cozinha cozinha = new Cozinha();
		cozinha.setId(1L);
		
		cadastroCozinha.delete(cozinha.getId());
		
		System.out.println(cozinha.getNome());
		
	}

}
