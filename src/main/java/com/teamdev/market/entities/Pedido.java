package com.teamdev.market.entities;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pedidos")
public class Pedido implements Serializable {
	
	@Serial
    private static final long serialVersionUID = 1;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	@Getter @Setter
	private int id;
	
	// R = recibido, E = enviado, A = anulado, F = finalizado
	@Column(name = "estado", nullable = false, length = 1)
	@Getter @Setter
	private String estado;
	
	@Column(name = "fecha_pedido", nullable = false)
	@Getter @Setter
	@Temporal(TemporalType.DATE)
	private Date fechaPedido;
	
	@Column(name = "fecha_entrega")
	@Getter @Setter
	@Temporal(TemporalType.DATE)
	private Date fechaEntrega;
	
	@Column(name = "monto", precision = 2, nullable = false)
	@Getter @Setter
	private Double monto;
	
	
	@ManyToOne()
	@JoinColumn(name = "usuario_id", nullable = false)
	@Getter @Setter
	private Usuario usuario;
	
	@OneToMany(mappedBy = "pedido")
	@Getter @Setter
	private List<DetallePedido> detallesPedido;
	
	@PrePersist
	public void setFechaPedido() {
		this.fechaPedido = new Date();
		this.estado = "R";
	}
}
