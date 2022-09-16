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
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Value;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "productos")
public class Producto implements Serializable {
	
	@Serial
    private static final long serialVersionUID = 1;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	@Getter @Setter
	private int id;
	
	@Column(name = "nombre", nullable = false, length = 100)
	@Getter @Setter
	private String nombre;
	
	@Column(name = "precio", nullable = false, precision = 2)
	@Getter @Setter
	private double precio;
	
	@Column(name = "cantidad", nullable = false)
	@Getter @Setter
	private int cantidad;
	
	// A = activo, D = deshabilitado
	@Value("A")
	@Column(name = "estado", nullable = false, length = 1)
	@Getter @Setter
	private String estado;
	
	@Column(name = "imagen")
	@Getter @Setter
	private String imagen;
	
	@Getter @Setter
    @Column(name = "descripcion", columnDefinition="TEXT")
    private String descripcion;
	
	@ManyToOne()
	@JoinColumn(name = "id_categoria", nullable = false)
	@Getter @Setter
	private Categoria categoria;
	
	@PrePersist
	private void setEstado() {
		this.estado = "A";
	}

}
