package br.com.barrostech.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.barrostech.domain.entity.Cozinha;
import br.com.barrostech.domain.entity.Restaurante;
import br.com.barrostech.domain.exception.EntidadeNaoEncontrada;
import br.com.barrostech.domain.repository.CozinhaRepository;
import br.com.barrostech.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	public Restaurante salvar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		Cozinha cozinha = cozinhaRepository.buscarPorId(cozinhaId);
		
		if (cozinha == null) {
			throw new EntidadeNaoEncontrada(String.format("Esta cozinha de código %d , não esta cadastrada .", cozinhaId));
		}
		restaurante.setCozinha(cozinha);
		
		return restauranteRepository.adicionar(restaurante);
	}

}
