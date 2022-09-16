package com.teamdev.market.services.interfaces;

import java.util.List;

import com.teamdev.market.entities.Rol;

public interface IrolService {
	
	public List<Rol> getAll();
	
	public Rol getById(Integer id);
	
	public Rol save(Rol rol);
	
	public Rol update(Rol rol, Integer id);
	
	public void delete(Integer id);

}
