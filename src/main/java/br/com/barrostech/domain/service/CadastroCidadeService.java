package br.com.barrostech.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.barrostech.domain.entity.Cidade;
import br.com.barrostech.domain.entity.Estado;
import br.com.barrostech.domain.exception.EntidadeEmUsoException;
import br.com.barrostech.domain.exception.EntidadeNaoEncontrada;
import br.com.barrostech.domain.repository.CidadeRepository;
import br.com.barrostech.domain.repository.EstadoRepository;

@Service
public class CadastroCidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	public Cidade salvar(Cidade cidade) {
		Long estadoId = cidade.getEstado().getId();
		Estado estado = estadoRepository.buscarPorId(estadoId);

		if (estado == null) {
			throw new EntidadeNaoEncontrada(
					String.format("Este estado de código %d , não esta cadastrado .", estadoId));
		}
		cidade.setEstado(estado);

		return cidadeRepository.adicionar(cidade);
	}

	public void excluir(Long cidadeId) {
		try {
			cidadeRepository.delete(cidadeId);
			
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontrada(
				String.format("Não existe um cadastro de cidade com código %d", cidadeId));
		
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
				String.format("Cidade de código %d não pode ser removida, pois está em uso", cidadeId));
		}
	}
}
