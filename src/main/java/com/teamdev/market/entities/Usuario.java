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
@Table(name = "usuarios")
public class Usuario implements Serializable {
	
	@Serial
    private static final long serialVersionUID = 1;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	@Getter @Setter
	private Integer id;
	
	@Column(name = "nombre", nullable = false, length = 80)
	@Getter @Setter
	private String nombre;
	
	@Column(name = "apellido", length = 80)
	@Getter @Setter
	private String apellido;
	
	@Column(name = "correo", nullable = false, length = 230)
	@Getter @Setter
	private String correo;
	
	@Column(name = "direccion", nullable = false, length = 240)
	@Getter @Setter
	private String direccion;
	
	@Column(name = "password", nullable = false, length = 20)
	@Getter @Setter
	private String password;
	
	// A = activo, D = deshabilitado
	@Value("A")
	@Column(name = "estado", nullable = false, length = 1)
	@Getter @Setter
	private String estado;
	
	@ManyToOne
	@JoinColumn(name = "rol_id", nullable = false)
	@Getter @Setter
	private Rol rol;
	
	@PrePersist
	public void setEstado() {
		this.estado = "A";
	}
}
