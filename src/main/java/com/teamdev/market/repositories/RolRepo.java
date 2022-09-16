package com.teamdev.market.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teamdev.market.entities.Rol;

@Repository
public interface RolRepo extends JpaRepository<Rol, Integer> {
	
	public List<Rol> findByNombre(String nombre);

}
