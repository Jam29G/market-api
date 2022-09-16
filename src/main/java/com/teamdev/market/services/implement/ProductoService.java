package com.teamdev.market.services.implement;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.teamdev.market.entities.Producto;
import com.teamdev.market.repositories.ProductoRepo;
import com.teamdev.market.services.interfaces.IproductoService;

@Service
public class ProductoService implements IproductoService {
	
	@Autowired
	private ProductoRepo repository;
	
	@Autowired
	private FileUploadService fileManager;

	@Override
	public List<Producto> getAll() {
		return repository.findByEstado("A");
	}

	@Override
	public Producto getById(Integer id) {
		return repository.findById(id).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El producto no existe") ); 
	}

	@Override
	public boolean isExist(Producto producto) {
		Long rows = repository.countByNombreAndDescripcion(producto.getNombre(), producto.getDescripcion());
		
		if(rows > 0) return true;
		
		return false;
	}

	@Override
	public Producto save(Producto producto, MultipartFile img) {
		
		if(this.isExist(producto)) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "El producto ya esta registrado");
		}
		
		if(img != null && !img.isEmpty()) {
			try {
				String imageName = fileManager.copiFile(img);
				producto.setImagen(imageName);
			}catch(IOException ex) {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al subir la imagen " + ex.getMessage());
			}
		}
		
		
		return repository.save(producto);
		
	}

	@Override
	public Producto update(Producto producto, Integer id, MultipartFile img) {
		
		Producto productoUpdate = this.getById(id);
		
		String oldImage = producto.getImagen();
		
		if(img != null && !img.isEmpty()) {
			try {
				String imageName = fileManager.copiFile(img);
				productoUpdate.setImagen(imageName);
			}catch(IOException ex) {
				new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al subir la imagen " + ex.getMessage());
			}
		}
		
		productoUpdate.setId(id);
		productoUpdate.setNombre(producto.getNombre());
		productoUpdate.setPrecio(producto.getPrecio());
		productoUpdate.setCantidad(producto.getCantidad());
		productoUpdate.setEstado(producto.getEstado());
		productoUpdate.setDescripcion(producto.getDescripcion());
		productoUpdate.setCategoria(producto.getCategoria());	
		
		if(img != null) {
			if(!fileManager.deleteFile(oldImage)) {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar la imagen");
			}
		};
		productoUpdate = repository.save(productoUpdate);
		
		
		return productoUpdate;
		
	}

	@Override
	public void delete(Integer id) {
		
		Producto productoDelete = getById(id);
		
		productoDelete.setEstado("D");
		
		repository.save(productoDelete);
	}

	@Override
	public List<Producto> getInactivos() {
		
		return repository.findByEstado("D");
	}
	
	

}
