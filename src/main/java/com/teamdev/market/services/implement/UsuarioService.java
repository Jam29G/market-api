package com.teamdev.market.services.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.teamdev.market.entities.Usuario;
import com.teamdev.market.repositories.UsuarioRepo;
import com.teamdev.market.services.interfaces.IusuarioService;

@Service
public class UsuarioService implements IusuarioService {
	
	@Autowired
	private UsuarioRepo repository;

	@Override
	public List<Usuario> getAll() {
		return repository.findAll();
	}

	@Override
	public Usuario getById(Integer id) {
		return repository.findById(id).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El usuario no existe") ); 
	}

	@Override
	public Usuario save(Usuario usuario) {
		Long isExist = repository.countByCorreo(usuario.getCorreo());
		
		if(isExist > 0) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "El usuario con correo: " + usuario.getCorreo() + " ya existe");
		}
		
		return repository.save(usuario);
	}

	@Override
	public Usuario update(Usuario usuario, Integer integer) {
		
		Usuario usuarioActual = getById(integer);
		
		usuario.setId(usuarioActual.getId());
		
		return repository.save(usuario);
		
	
		
	}

	@Override
	public void delete(Integer id) {
		Usuario usuarioDelete = getById(id);
		usuarioDelete.setEstado("D");
		repository.save(usuarioDelete);
		
	}
	
	

}
