package com.projetodto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetodto.dto.UsuarioDTO;
import com.projetodto.entities.Usuario;


public interface  UsuarioRepository extends JpaRepository<Usuario,Long>{

	

}
