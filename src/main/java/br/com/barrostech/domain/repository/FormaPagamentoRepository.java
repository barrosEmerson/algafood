package br.com.barrostech.domain.repository;

import java.util.List;

import br.com.barrostech.domain.entity.FormaPagamento;

public interface FormaPagamentoRepository {
	
	List<FormaPagamento> listar();
	FormaPagamento buscarPorId(Long id);
	FormaPagamento adicionar(FormaPagamento cozinha);
	void delete(FormaPagamento cozinha);

}
