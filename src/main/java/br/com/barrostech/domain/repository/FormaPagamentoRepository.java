package br.com.barrostech.domain.repository;

import java.util.List;

import br.com.barrostech.domain.entity.FormaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long> {
	
	//List<FormaPagamento> listar();

}
