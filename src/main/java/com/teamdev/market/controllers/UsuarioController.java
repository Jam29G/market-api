package com.teamdev.market.controllers;

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

import com.teamdev.market.entities.Usuario;
import com.teamdev.market.services.interfaces.IusuarioService;

@RestController
@CrossOrigin()
@RequestMapping("/api/usuarios")
public class UsuarioController {
	
	@Autowired
	private IusuarioService usuarioService;
	
	@GetMapping
	public ResponseEntity<List<Usuario>> getAll() {
		return new ResponseEntity<List<Usuario>>(usuarioService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getById(@PathVariable("id") Integer id ) {
		return new ResponseEntity<Usuario>(usuarioService.getById(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Usuario> create(@RequestBody Usuario usuario) {
		return new ResponseEntity<Usuario>(usuarioService.save(usuario), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> update(@RequestBody Usuario usuario, @PathVariable Integer id) {
		return new ResponseEntity<Usuario>(usuarioService.update(usuario, id), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
		usuarioService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}
