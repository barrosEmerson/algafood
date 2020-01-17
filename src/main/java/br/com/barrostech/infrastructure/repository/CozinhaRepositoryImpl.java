package br.com.barrostech.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.barrostech.domain.entity.Cozinha;
import br.com.barrostech.domain.repository.CozinhaRepository;

@Repository
public class CozinhaRepositoryImpl implements CozinhaRepository {

	@PersistenceContext
	private EntityManager maneger;
	
	public List<Cozinha> listar(){
		return maneger.createQuery("from Cozinha", Cozinha.class).getResultList();
	}
	
	@Transactional
	public Cozinha adicionar(Cozinha cozinha) {
		return maneger.merge(cozinha);
	}
	
	public Cozinha buscarPorId(Long id) {
		return maneger.find(Cozinha.class, id);
	}
	
	@Transactional
	public void delete(Long id) {
		Cozinha cozinha = buscarPorId(id);
		
		if (cozinha == null) {
			throw new EmptyResultDataAccessException(1);
		}
				
		maneger.remove(cozinha);
	}

}
