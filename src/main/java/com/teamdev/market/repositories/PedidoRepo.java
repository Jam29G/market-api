package com.teamdev.market.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.teamdev.market.entities.Pedido;

public interface PedidoRepo extends JpaRepository<Pedido, Integer> {
	
	public List<Pedido> findByEstado(String estado);
	
	@Query("select p from Pedido p where p.usuario.id = :usuario_id")
	public List<Pedido> findByUsuarioId( @Param("usuario_id") Integer id);
	
	public List<Pedido> findByEstadoAndFechaPedidoBetween(String estado, Date fecha_inicio, Date fecha_fin);

}
