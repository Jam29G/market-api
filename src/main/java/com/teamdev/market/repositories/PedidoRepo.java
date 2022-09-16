package com.teamdev.market.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teamdev.market.entities.Pedido;

public interface PedidoRepo extends JpaRepository<Pedido, Integer> {
	
	public List<Pedido> findByEstado(String estado);
	
	public List<Pedido> findByEstadoAndFechaPedidoBetween(String estado, Date fecha_inicio, Date fecha_fin);

}
