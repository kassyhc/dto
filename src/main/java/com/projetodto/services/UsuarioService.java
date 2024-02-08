package com.projetodto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetodto.dto.UsuarioDTO;
import com.projetodto.entities.Usuario;
import com.projetodto.repository.UsuarioRepository;


@Service
public class UsuarioService {
	private final UsuarioRepository UsuarioRepository;
	

	@Autowired
	public UsuarioService(UsuarioRepository UsuarioRepository) {
		this.UsuarioRepository = UsuarioRepository;
	}

	public List<Usuario> getAllUsuarioDTOs() {
		return UsuarioRepository.findAll();
	}

	public Usuario getUsuarioDTOById(Long id) {
		Optional<Usuario> Usuario = UsuarioRepository.findById(id);
		return Usuario.orElse(null);
	}

	public UsuarioDTO saveUsuarioDTO(UsuarioDTO UsuarioDTO) {
		Usuario usuario = new Usuario(UsuarioDTO.nome(), UsuarioDTO.senha());
		Usuario salvarUsuario = UsuarioRepository.save(usuario);
		return new UsuarioDTO(salvarUsuario.getId(), salvarUsuario.getNome(), salvarUsuario.getSenha());
	}

	public UsuarioDTO changeUsuarioDTO(Long id, UsuarioDTO UsuarioDTO) {
		Usuario existeUsuario = UsuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
		
		existeUsuario.setNome(UsuarioDTO.nome());
		existeUsuario.setSenha(UsuarioDTO.senha());
		
		Usuario updateUsuario = UsuarioRepository.save(existeUsuario);
		return new UsuarioDTO(updateUsuario.getId(), updateUsuario.getNome(), updateUsuario.getSenha());
	}

	public boolean deleteUsuarioDTO(Long id) {
		Optional<Usuario> existeUsuarioDTO= UsuarioRepository.findById(id);
		if (existeUsuarioDTO.isPresent()) {
			UsuarioRepository.deleteById(id);
			return true;
		}
		return false;
	}
}

