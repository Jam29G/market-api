package com.teamdev.market.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teamdev.market.entities.Producto;

@Repository
public interface ProductoRepo extends JpaRepository<Producto, Integer> {
	
	public Long countByNombreAndDescripcion(String nombre, String descripcion);
	
	public List<Producto> findByEstado(String estado);

}
