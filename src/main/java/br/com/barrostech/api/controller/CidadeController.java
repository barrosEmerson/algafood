package br.com.barrostech.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.barrostech.domain.entity.Cidade;
import br.com.barrostech.domain.exception.EntidadeEmUsoException;
import br.com.barrostech.domain.exception.EntidadeNaoEncontrada;
import br.com.barrostech.domain.repository.CidadeRepository;
import br.com.barrostech.domain.service.CadastroCidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadeController {
	
	@Autowired
	private CadastroCidadeService cidadeService;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@GetMapping
	public List<Cidade> listar(){
		return cidadeRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cidade> buscar(@PathVariable Long id){
		Optional<Cidade> cidade = cidadeRepository.findById(id);
		
		if (cidade.isPresent()) {
			return ResponseEntity.ok(cidade.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody Cidade cidade){
		try {
			cidade = cidadeService.salvar(cidade);
			return ResponseEntity.status(HttpStatus.CREATED).body(cidade);
			
		} catch (EntidadeNaoEncontrada e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@PathVariable Long id,@RequestBody Cidade cidade) {
		Optional<Cidade> cidadeAtual = cidadeRepository.findById(id);
		
		if(cidadeAtual.isPresent()) {
			try {
				BeanUtils.copyProperties(cidade, cidadeAtual.get(), "id");
				Cidade cidadeSalva = cidadeService.salvar(cidadeAtual.get());
				return ResponseEntity.ok(cidadeSalva);
				
			} catch (EntidadeNaoEncontrada e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			}
		}
		return (ResponseEntity<?>) ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{cidadeId}")
	public ResponseEntity<Cidade> remover(@PathVariable Long cidadeId) {
		try {
			cidadeService.excluir(cidadeId);	
			return ResponseEntity.noContent().build();
			
		} catch (EntidadeNaoEncontrada e) {
			return ResponseEntity.notFound().build();
			
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

}
