package com.teamdev.market.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.teamdev.market.entities.Pedido;
import com.teamdev.market.services.interfaces.IpedidoService;

@Repository
@CrossOrigin()
@RequestMapping("/api/pedidos")
public class PedidoController {
	
	@Autowired
	private IpedidoService pedidoService;
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping()
	public ResponseEntity<List<Pedido>> getAll(
		@RequestParam(name = "estado", required = false) String estado, 
		@RequestParam(name = "fecha_inicio", required = false) Date fecha_inicio
	) 
	{
		
		if(estado == null ) estado = "R";
		
		return new ResponseEntity<List<Pedido>>(pedidoService.findAll(fecha_inicio, estado), HttpStatus.OK);
		
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/usuario/{id}")
	public ResponseEntity<List<Pedido>> getByUser(@PathVariable(name = "id") Integer id) {
		return new ResponseEntity<List<Pedido>>(pedidoService.findByUsuarioId(id), HttpStatus.OK);
	}
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/{id}")
	public ResponseEntity<Pedido> getById(@PathVariable(name = "id") Integer id) {
		return new ResponseEntity<Pedido>(pedidoService.findById(id), HttpStatus.OK);
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@PostMapping
	public ResponseEntity<Pedido> save(@RequestBody() Pedido pedido) {
		return new ResponseEntity<Pedido>(pedidoService.save(pedido), HttpStatus.CREATED);
	}
	
	@Secured({"ROLE_ADMIN"})
	@PutMapping("/{id}")
	public ResponseEntity<Pedido> changeState(@PathVariable("id") Integer id, @RequestParam("estado") String estado) {
		return new ResponseEntity<Pedido>(pedidoService.changeState(id, estado), HttpStatus.OK);
	}
	
	
}
