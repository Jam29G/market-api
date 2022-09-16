package com.teamdev.market.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.teamdev.market.entities.Producto;
import com.teamdev.market.services.interfaces.IproductoService;

@RestController
@CrossOrigin()
@RequestMapping("/api/productos")
public class ProductoController {
	
	@Autowired
	private IproductoService productoService;
	
	@GetMapping(value = "/image/{filename}")
    public @ResponseBody void getImage(HttpServletResponse response, @PathVariable("filename") String filename ) throws IOException {

		String UPLOAD_FOLDER = "src//main//resources/images";

        File fl = new File(UPLOAD_FOLDER + "//" + filename);

        InputStream resource = new FileInputStream(fl);

        StreamUtils.copy(resource, response.getOutputStream());




    }
	
	@GetMapping
	public ResponseEntity<List<Producto>> getAll() {
		return new ResponseEntity<List<Producto>>(productoService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping("/inactivos")
	public ResponseEntity<List<Producto>> getInactivos() {
		return new ResponseEntity<List<Producto>>(productoService.getInactivos(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Producto> getById(@PathVariable("id") Integer id) {
		return new ResponseEntity<Producto>(productoService.getById(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Producto> create(@RequestPart("producto") Producto producto, @RequestPart(name = "img", required = false) MultipartFile img) {
		return new ResponseEntity<Producto>(productoService.save(producto, img), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Producto> update(
		@RequestPart(name = "producto") Producto producto,
		@RequestPart(name = "img", required = false) MultipartFile img,
		@PathVariable(name = "id") Integer id
	) {
		return new ResponseEntity<Producto>(productoService.update(producto, id, img), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable(name = "id") Integer id) {
		productoService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	
	

}
