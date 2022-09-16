package com.teamdev.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teamdev.market.entities.DetallePedido;

@Repository
public interface DetallePedidoRepo extends JpaRepository<DetallePedido, Integer> {

}
