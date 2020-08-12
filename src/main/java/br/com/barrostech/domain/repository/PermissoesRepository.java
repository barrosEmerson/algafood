package br.com.barrostech.domain.repository;

import java.util.List;

import br.com.barrostech.domain.entity.Permissoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissoesRepository extends JpaRepository<Permissoes, Long> {

	//List<Permissoes> listar();

}
