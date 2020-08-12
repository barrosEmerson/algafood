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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.barrostech.domain.entity.Estado;
import br.com.barrostech.domain.exception.EntidadeEmUsoException;
import br.com.barrostech.domain.exception.EntidadeNaoEncontrada;
import br.com.barrostech.domain.repository.EstadoRepository;
import br.com.barrostech.domain.service.CadastroEstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController {
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CadastroEstadoService estadoService;
	
	@GetMapping
	public List<Estado> Listar(){
		return estadoRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Estado salvar(@RequestBody Estado estado) {
		return estadoService.salvar(estado);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Estado> busca(@PathVariable Long id){
		Optional<Estado> estadoId  = estadoRepository.findById(id);
		
		if(estadoId.isPresent()) {
			return ResponseEntity.ok(estadoId.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Estado> atualizar(@PathVariable Long id, @RequestBody Estado estado){
		Optional<Estado> estadoAtual = estadoRepository.findById(id);
		
		if(estadoAtual.isPresent()) {
			BeanUtils.copyProperties(estado, estadoAtual.get(), "id");
			Estado estadoSalvo = estadoService.salvar(estadoAtual.get());
			return ResponseEntity.ok(estadoSalvo);
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Estado> deletar(@PathVariable Long id){
		
		try {
			estadoService.remover(id);
			return ResponseEntity.noContent().build();
			
		} catch (EntidadeNaoEncontrada e) {
			return ResponseEntity.notFound().build();
			
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
}
