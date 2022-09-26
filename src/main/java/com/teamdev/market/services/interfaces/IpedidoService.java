package com.teamdev.market.services.interfaces;

import java.util.Date;
import java.util.List;

import com.teamdev.market.entities.Pedido;

public interface IpedidoService {
	
	public List<Pedido> findAll(Date fecha_inicio, String estado);
	
	public Pedido findById(Integer id);
	
	public Pedido save(Pedido pedido);
	
	public Pedido changeState(Integer id, String estado);
	
	public List<Pedido> findByUsuarioId(Integer id);
	
	

}
