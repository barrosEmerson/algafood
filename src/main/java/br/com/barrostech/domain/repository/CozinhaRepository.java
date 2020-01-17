package br.com.barrostech.domain.repository;

import java.util.List;

import br.com.barrostech.domain.entity.Cozinha;

public interface CozinhaRepository {

	
	List<Cozinha> listar();
	Cozinha buscarPorId(Long id);
	Cozinha adicionar(Cozinha cozinha);
	void delete(Long id);
}
