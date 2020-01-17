package br.com.barrostech.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import br.com.barrostech.AlgafoodApplication;
import br.com.barrostech.domain.entity.Restaurante;
import br.com.barrostech.domain.repository.RestauranteRepository;

public class ConsultaCozinhaMain {
	
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);
		
		List<Restaurante> restaurantes = restauranteRepository.listar();
		
		for(Restaurante restaurante : restaurantes) {
			System.out.printf("%s - %f - %s\n",restaurante.getNome(), restaurante.getTaxaFrete(),restaurante.getCozinha().getNome());
		}
	}

}
