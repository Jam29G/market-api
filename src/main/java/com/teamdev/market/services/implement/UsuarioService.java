package com.teamdev.market.services.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.teamdev.market.entities.Usuario;
import com.teamdev.market.repositories.UsuarioRepo;
import com.teamdev.market.services.interfaces.IusuarioService;

@Service
public class UsuarioService implements IusuarioService, UserDetailsService {
	
	@Autowired
	private UsuarioRepo repository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	private static final Logger log = LoggerFactory.getLogger(UsuarioService.class);


	@Override
	public List<Usuario> getAll(Boolean estado) {
		return repository.findByEstado(estado);
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
		
		String passwordEncrypt = passwordEncoder.encode(usuario.getPassword());
		
		usuario.setPassword(passwordEncrypt);
		
		return repository.save(usuario);
	}

	@Override
	public Usuario update(Usuario usuario, Integer integer) {
		
		Usuario usuarioActual = getById(integer);
		
		usuario.setId(usuarioActual.getId());
		
		String passwordEncrypt = passwordEncoder.encode(usuario.getPassword());
		
		usuario.setPassword(passwordEncrypt);
		
		return repository.save(usuario);
		
	
		
	}

	@Override
	public void delete(Integer id) {
		Usuario usuarioDelete = getById(id);
		usuarioDelete.setEstado(false);
		repository.save(usuarioDelete);
		
	}
	
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = repository.findByCorreo(username);
		
		if(usuario == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El usuario " + username + " no existe");
		
		//Obtener los roles
		List<GrantedAuthority> authorities = usuario.getRoles()
				.stream()
				.map(rol -> new SimpleGrantedAuthority(rol.getNombre() ))
				.peek(authority -> log.info("Rol: " + authority.getAuthority() ))
				.collect(Collectors.toList());
		
		return new User(usuario.getCorreo(), usuario.getPassword(), usuario.getEstado(), true, true, true, authorities);
	}
	
	@Override
	public Usuario getByCorreo(String correo) {
		Usuario usuario = repository.findByCorreo(correo);
		if(usuario == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El usuario " + correo + " no existe");
		return usuario;
		
	}

	@Override
	public Usuario restore(Integer id) {
		Usuario usuario = getById(id);
		
		usuario.setEstado(true);
		
		repository.save(usuario);
		return null;
	}
	
	

}
