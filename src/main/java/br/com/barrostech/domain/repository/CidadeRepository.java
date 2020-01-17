package br.com.barrostech.domain.repository;

import java.util.List;

import br.com.barrostech.domain.entity.Cidade;

public interface CidadeRepository{

	List<Cidade> listar();
	Cidade buscarPorId(Long id);
	Cidade adicionar(Cidade cidade);
	void delete(Long id);
}
