package br.com.barrostech.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.barrostech.domain.entity.Restaurante;
import br.com.barrostech.domain.repository.RestauranteRepository;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepository {

	@PersistenceContext
	private EntityManager maneger;
	
	public List<Restaurante> listar(){
		return maneger.createQuery("from Restaurante", Restaurante.class).getResultList();
	}
	
	@Transactional
	public Restaurante adicionar(Restaurante restaurante) {
		return maneger.merge(restaurante);
	}
	
	public Restaurante buscarPorId(Long id) {
		return maneger.find(Restaurante.class, id);
	}
	
	@Transactional
	public void delete(Restaurante restaurante) {
		restaurante = buscarPorId(restaurante.getId());
		maneger.remove(restaurante);
	}

}
