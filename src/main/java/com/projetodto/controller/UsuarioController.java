package com.projetodto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetodto.dto.UsuarioDTO;
import com.projetodto.entities.Usuario;
import com.projetodto.services.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/Usuario")
public class UsuarioController {

	private final UsuarioService UsuarioService;

	@Autowired
	public UsuarioController(UsuarioService UsuarioService) {
		this.UsuarioService = UsuarioService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> buscaUsuarioControlId(@PathVariable Long id) {
		Usuario Usuario = UsuarioService.getUsuarioDTOById(id);
		if (Usuario != null) {
			return ResponseEntity.ok(Usuario);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping
	public ResponseEntity<List<Usuario>> buscaTodasLigacoesControl() {
		List<Usuario> Usuario = UsuarioService.getAllUsuarioDTOs();
		return ResponseEntity.ok(Usuario);
	}

	@PostMapping
	public ResponseEntity<UsuarioDTO> saveUsuarioControl(@RequestBody @Valid UsuarioDTO UsuarioDTO) {
		UsuarioDTO saveUsuario = UsuarioService.saveUsuarioDTO(UsuarioDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(saveUsuario);
	}

	@PutMapping("/{id}")
	public ResponseEntity<UsuarioDTO> alteraUsuarioControl(@PathVariable Long id, @RequestBody @Valid UsuarioDTO UsuarioDTO) {
		UsuarioDTO alteraUsuario = UsuarioService.changeUsuarioDTO(id, UsuarioDTO);

		if (alteraUsuario != null) {
			return ResponseEntity.ok(UsuarioDTO);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUsuarioControl(@PathVariable Long id) {
		boolean delete = UsuarioService.deleteUsuarioDTO(id);
		if (delete) {
			return ResponseEntity.ok().body("O produto foi excluido com o sucesso");
		} else {
			return ResponseEntity.notFound().build();
		}

	}

}