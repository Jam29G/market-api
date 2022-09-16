package com.teamdev.market.services.interfaces;

import java.util.List;

import com.teamdev.market.entities.Categoria;

public interface IcategoriaService {

	public List<Categoria> getAll();
	
	public Categoria getById(Integer id);
	
	public Categoria save(Categoria categoria);
	
	public Categoria update(Categoria categoria, Integer id);
	
	public void delete(Integer id);
	
}
