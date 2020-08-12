package br.com.barrostech.domain.repository;

import java.util.List;

import br.com.barrostech.domain.entity.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {
	//List<Cozinha> listar();

}
