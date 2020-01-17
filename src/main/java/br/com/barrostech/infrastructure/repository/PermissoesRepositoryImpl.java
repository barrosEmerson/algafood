package br.com.barrostech.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.barrostech.domain.entity.Permissoes;
import br.com.barrostech.domain.repository.PermissoesRepository;

@Repository
public class PermissoesRepositoryImpl implements PermissoesRepository{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Permissoes> listar() {
		return manager.createQuery("from Permissoes", Permissoes.class).getResultList();
	}

	@Override
	public Permissoes buscarPorId(Long id) {
		return manager.find(Permissoes.class, id);
	}

	@Override
	public Permissoes adicionar(Permissoes permissoes) {
		return manager.merge(permissoes);
	}

	@Override
	public void delete(Permissoes permissoes) {
		manager.remove(permissoes);
	}

}
