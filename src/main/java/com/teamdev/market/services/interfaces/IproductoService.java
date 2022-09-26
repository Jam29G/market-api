package com.teamdev.market.services.interfaces;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.teamdev.market.entities.Producto;

public interface IproductoService {
	
	public List<Producto> getAll();
	
	public List<Producto> getInactivos();
	
	public Producto getById(Integer id);
	
	public boolean isExist(Producto producto);
	
	public Producto save(Producto producto, MultipartFile img);
	
	public Producto update(Producto producto, Integer id, MultipartFile img);
	
	public Producto changeState(Integer id, String estado);
	
	public void delete(Integer id);

}
