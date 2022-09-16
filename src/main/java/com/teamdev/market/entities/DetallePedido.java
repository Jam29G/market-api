package com.teamdev.market.entities;

import java.io.Serial;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "detalles_pedido")
public class DetallePedido implements Serializable {
	
	@Serial
    private static final long serialVersionUID = 1;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	@Getter @Setter
	private int id;
	
	@Column(name = "cantidad", nullable = false)
	@Getter @Setter
	private int cantidad;
	
	@Column(name = "precio_unitario", nullable = false, precision = 2 )
	@Getter @Setter
	private double precioUnitario;
	
	@ManyToOne()
	@JoinColumn(name = "producto_id", nullable = false)
	@Getter @Setter
	private Producto producto;
	
	@JsonBackReference
	@ManyToOne()
	@JoinColumn(name = "pedido_id", nullable = false)
	@Getter @Setter
	private Pedido pedido;
}
