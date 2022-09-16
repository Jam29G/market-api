package com.teamdev.market.services.interfaces;

import java.util.List;

import com.teamdev.market.entities.Usuario;

public interface IusuarioService {
	
	public List<Usuario> getAll();
	
	public Usuario getById(Integer id);
	
	public Usuario save(Usuario usuario);
	
	public Usuario update(Usuario usuario, Integer integer);
	
	public void delete(Integer id);

}
