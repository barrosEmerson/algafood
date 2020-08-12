package br.com.barrostech.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.barrostech.domain.entity.Restaurante;
import br.com.barrostech.domain.exception.EntidadeNaoEncontrada;
import br.com.barrostech.domain.repository.RestauranteRepository;
import br.com.barrostech.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping("/restaurante")
public class RestauranteController {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CadastroRestauranteService cadastroRestaurante;

	@GetMapping
	public List<Restaurante> listar() {
		return restauranteRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Restaurante> buscar(@PathVariable Long id) {
		Optional<Restaurante> restaurante = restauranteRepository.findById(id);

		if (restaurante.isPresent()) {
			return ResponseEntity.ok(restaurante.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody Restaurante restaurante) {
		try {

			restaurante = cadastroRestaurante.salvar(restaurante);
			return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);

		} catch (EntidadeNaoEncontrada e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Restaurante restaurante) {
		Optional<Restaurante> restauranteAtual = restauranteRepository.findById(id);

		if (restauranteAtual.isPresent()) {

			try {
				BeanUtils.copyProperties(restaurante, restauranteAtual.get(), "id");
				Restaurante restauranteSalvo = cadastroRestaurante.salvar(restauranteAtual.get());

				return ResponseEntity.ok(restauranteSalvo);
			} catch (EntidadeNaoEncontrada e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			}

		}
		return (ResponseEntity<?>) ResponseEntity.notFound().build();

	}

	@PatchMapping("/{id}")
	public ResponseEntity<?> atualizarParcial(@PathVariable Long id,@RequestBody Map<String, Object> campos) {
		Optional<Restaurante> restauranteAtual = restauranteRepository.findById(id);

		if (restauranteAtual.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		merge(campos, restauranteAtual.get());

		return atualizar(id, restauranteAtual.get());
	}

	private void merge(Map<String, Object> camposOriginais, Restaurante restauranteDestino) {
		ObjectMapper objectMapper = new ObjectMapper();
		Restaurante restauranteOrigem = objectMapper.convertValue(camposOriginais, Restaurante.class);
		
		camposOriginais.forEach((nomePropriedade, valorPropriedade)  	-> {
			Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
			field.setAccessible(true);
			
			Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

			ReflectionUtils.setField(field, restauranteDestino, novoValor);
		});
	}

}
