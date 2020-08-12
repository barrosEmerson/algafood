package br.com.barrostech.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.barrostech.domain.entity.Estado;
import br.com.barrostech.domain.exception.EntidadeEmUsoException;
import br.com.barrostech.domain.exception.EntidadeNaoEncontrada;
import br.com.barrostech.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {
	
	@Autowired
	private EstadoRepository estadoRepository;
		
	public Estado salvar(Estado estado) {
		return estadoRepository.save(estado);
	}
	
	public void remover(Long id) {
		try {
			estadoRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontrada(String.format("Estado de código %d não foi encontrado", id));
		
		}catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format("Este estado de código %d, não pode ser excluído por estar em uso", id));
		}
	}

}
