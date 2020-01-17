package br.com.barrostech.domain.repository;

import java.util.List;

import br.com.barrostech.domain.entity.Permissoes;

public interface PermissoesRepository {

	List<Permissoes> listar();
	Permissoes buscarPorId(Long id);
	Permissoes adicionar(Permissoes permissoes);
	void delete(Permissoes permissoes);
}
