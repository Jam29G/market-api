package com.teamdev.market.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teamdev.market.entities.Usuario;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario, Integer> {
	
	public long countByCorreo(String correo);
	
	public Usuario findByCorreoAndPassword(String correo, String password);
	
	public Usuario findByCorreo(String correo);
	
	public List<Usuario> findByEstado(Boolean estado);

}
