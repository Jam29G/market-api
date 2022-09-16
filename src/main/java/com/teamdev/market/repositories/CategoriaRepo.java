package com.teamdev.market.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teamdev.market.entities.Categoria;

@Repository
public interface CategoriaRepo extends JpaRepository<Categoria, Integer> {
	
	public List<Categoria> findByNombre(String nombre);
	
	

}
