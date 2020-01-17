package br.com.barrostech.domain.repository;

import java.util.List;

import br.com.barrostech.domain.entity.Restaurante;

public interface RestauranteRepository {

	
	List<Restaurante> listar();
	Restaurante buscarPorId(Long id);
	Restaurante adicionar(Restaurante cozinha);
	void delete(Restaurante cozinha);
}
