package com.teamdev.market.entities;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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
	
	@Column(name = "password", nullable = false, length = 244)
	@Getter @Setter
	private String password;
	
	//Boolean
	@Column(name = "estado", nullable = false)
	@Getter @Setter
	private Boolean estado;
	
	@ManyToMany(cascade = CascadeType.MERGE, fetch=FetchType.EAGER)
	@JoinTable(
		name = "usuarios_roles", 
		joinColumns = @JoinColumn(name = "usuario_id"),
		inverseJoinColumns = @JoinColumn(name = "rol_id"),
		uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario_id", "rol_id"})}
	)
	@Getter @Setter
	private List<Rol> roles;
	
	@PrePersist
	public void setEstado() {
		this.estado = true;
	}
}
