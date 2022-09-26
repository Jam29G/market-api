package com.teamdev.market.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teamdev.market.entities.Rol;
import com.teamdev.market.entities.Usuario;
import com.teamdev.market.services.interfaces.IusuarioService;

@RestController
@CrossOrigin()
@RequestMapping("/api/usuarios")
public class UsuarioController {
	
	@Autowired
	private IusuarioService usuarioService;
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping
	public ResponseEntity<List<Usuario>> getAll(@RequestParam(name = "estado") Boolean estado ) {
		return new ResponseEntity<List<Usuario>>(usuarioService.getAll(estado), HttpStatus.OK);
	}
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getById(@PathVariable("id") Integer id ) {
		return new ResponseEntity<Usuario>(usuarioService.getById(id), HttpStatus.OK);
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@PostMapping
	public ResponseEntity<Usuario> create(@RequestBody Usuario usuario) {
		return new ResponseEntity<Usuario>(usuarioService.save(usuario), HttpStatus.CREATED);
	}
	
	@PostMapping("/user")
	public ResponseEntity<Usuario> createUser(@RequestBody Usuario usuario) {
		List<Rol> roles = new ArrayList<>();
		roles.add(new Rol(2));
		usuario.setRoles(roles);
		return new ResponseEntity<Usuario>(usuarioService.save(usuario), HttpStatus.CREATED);
	}
	
	@Secured({"ROLE_ADMIN"})
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> update(@RequestBody Usuario usuario, @PathVariable Integer id) {
		return new ResponseEntity<Usuario>(usuarioService.update(usuario, id), HttpStatus.OK);
	}
	
	@Secured({"ROLE_ADMIN"})
	@PutMapping("/restore/{id}")
	public ResponseEntity<Usuario> update(@PathVariable Integer id) {
		return new ResponseEntity<Usuario>(usuarioService.restore(id), HttpStatus.OK);
	}
	
	@Secured({"ROLE_ADMIN"})
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
		usuarioService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}
