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

import com.teamdev.market.entities.Categoria;
import com.teamdev.market.services.interfaces.IcategoriaService;

@RestController()
@CrossOrigin()
@RequestMapping("/api/categorias")
public class CategoriaController {
	
	@Autowired
	private IcategoriaService categoriaService;
	
	@GetMapping()
	public ResponseEntity<List<Categoria>> getAll() {
		
		return new ResponseEntity<List<Categoria>>(categoriaService.getAll(), HttpStatus.OK);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> getById(@PathVariable("id") Integer id) {
		return new ResponseEntity<Categoria>(categoriaService.getById(id), HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<Categoria> create(@RequestBody Categoria categoria) {
		return new ResponseEntity<Categoria>(categoriaService.save(categoria), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Categoria> update(@RequestBody Categoria categoria, @PathVariable Integer id) {
		return new ResponseEntity<Categoria>(categoriaService.update(categoria, id), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
		categoriaService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
