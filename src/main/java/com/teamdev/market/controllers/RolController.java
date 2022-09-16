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

import com.teamdev.market.entities.Rol;
import com.teamdev.market.services.implement.RolService;


@RestController
@CrossOrigin()
@RequestMapping("/api/roles")
public class RolController {
	
	@Autowired
	private RolService rolService;
	
	@GetMapping()
	public ResponseEntity<List<Rol>> getAll() {
		
		return new ResponseEntity<List<Rol>>(rolService.getAll(), HttpStatus.OK);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Rol> getById( @PathVariable("id") Integer id) {
		return new ResponseEntity<Rol>(rolService.getById(id), HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<Rol> create( @RequestBody Rol rol) {
		return new ResponseEntity<Rol>(rolService.save(rol), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Rol> update(@RequestBody Rol rol, @PathVariable("id") Integer id ) {
		return new ResponseEntity<Rol>(rolService.update(rol, id), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
		rolService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	

}
