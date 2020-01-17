package br.com.barrostech.domain.repository;

import java.util.List;

import br.com.barrostech.domain.entity.Estado;

public interface EstadoRepository {
	
	List<Estado> listar();
	Estado buscarPorId(Long id);
	Estado adicionar(Estado estado);
	void delete(Long id);

}
