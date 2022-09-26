package com.teamdev.market.services.implement;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.teamdev.market.entities.DetallePedido;
import com.teamdev.market.entities.Pedido;
import com.teamdev.market.repositories.DetallePedidoRepo;
import com.teamdev.market.repositories.PedidoRepo;
import com.teamdev.market.services.interfaces.IpedidoService;

@Service
public class PedidoService implements IpedidoService {
	
	
	private static final Logger log = LoggerFactory.getLogger(PedidoService.class);

	
	@Autowired
	private PedidoRepo repository;
	
	@Autowired
	private DetallePedidoRepo detalleRepo;

	@Override
	public List<Pedido> findAll(Date fecha_inicio, String estado) {
		
		if(estado.isEmpty()) estado = "R";
		
		if(fecha_inicio != null) {
			
			Calendar fecha = Calendar.getInstance();
			fecha.setTime(fecha_inicio);
			fecha.set(Calendar.DAY_OF_MONTH, fecha.getActualMaximum(Calendar.DAY_OF_MONTH));
			Date fechaFin = fecha.getTime();
			return repository.findByEstadoAndFechaPedidoBetween(estado, fecha_inicio, fechaFin);
		}
		
		return repository.findByEstado(estado);
		
	}

	@Override
	public Pedido findById(Integer id) {
		return repository.findById(id).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El pedido no existe") );
	}

	@Override
	public Pedido save(Pedido pedido) {
		List<DetallePedido> detalles = pedido.getDetallesPedido();
		
		pedido.setDetallesPedido(new ArrayList<DetallePedido>());
		
		Pedido pedidoPersisted = repository.save(pedido);
		
		for(DetallePedido detalle : detalles) {
			detalle.setPedido(pedidoPersisted);
		}
		
		detalleRepo.saveAll(detalles);
		
		return findById(pedidoPersisted.getId());
	}

	@Override
	public Pedido changeState(Integer id, String estado) {
		Pedido pedidoChange = findById(id);
		
		pedidoChange.setEstado(estado);
		
		if(estado.equals("F")) {
			pedidoChange.setFechaEntrega(new Date());
			log.info("Fecha de entrega: " + pedidoChange.getFechaEntrega());
		}
		
		return repository.save(pedidoChange);
	}

	@Override
	public List<Pedido> findByUsuarioId(Integer id) {
		
		return repository.findByUsuarioId(id);
	}

}
