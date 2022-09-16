package com.teamdev.market.services.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.teamdev.market.entities.Categoria;
import com.teamdev.market.repositories.CategoriaRepo;
import com.teamdev.market.services.interfaces.IcategoriaService;

@Service
public class CategoriaService implements IcategoriaService {
	
	@Autowired
	private CategoriaRepo repository;
	
	public List<Categoria> getAll() {
		return repository.findAll();
	}
	
	public Categoria getById(Integer id) {
		return repository.findById(id).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "La categoria no existe") );
	}
	
	public Categoria save(Categoria categoria) {
		List<Categoria> isExists = repository.findByNombre(categoria.getNombre());
		
		if(isExists.size() > 0) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "La categoria con nombre " + categoria.getNombre() + " ya existe");
		}
		
		return repository.save(categoria);
	}
	
	public Categoria update(Categoria categoria, Integer id) {
		Categoria categoriaUpdate = getById(id);
		
		categoriaUpdate.setNombre(categoria.getNombre());
		
		return repository.save(categoriaUpdate);
	}
	
	public void delete(Integer id) {
		Categoria categoria = getById(id);
		
		repository.delete(categoria);
	}
	
	

}
