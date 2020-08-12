package br.com.barrostech.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.barrostech.domain.entity.Cozinha;
import br.com.barrostech.domain.exception.EntidadeEmUsoException;
import br.com.barrostech.domain.exception.EntidadeNaoEncontrada;
import br.com.barrostech.domain.repository.CozinhaRepository;

@Service
public class CadastroCozinhaService {

	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	public Cozinha salvar(Cozinha cozinha) {
		return cozinhaRepository.save(cozinha);
	}
	
	public void remover(Long id) {
		try {
			cozinhaRepository.deleteById(id);
		
		}catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontrada(String.format("Cozinha de código %d não foi encontrada", id));
			
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format("Esta cozinha de código %d, não pode ser excluída por estar em uso  ", id));
		}
	}
}

