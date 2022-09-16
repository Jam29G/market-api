package com.teamdev.market.entities;

import java.io.Serial;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "roles")
public class Rol implements Serializable {
	
	@Serial
    private static final long serialVersionUID = 1;
	
	@Getter @Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)	
	private Integer id;
	
	@Getter @Setter
	@Column(name = "nombre", nullable = false, length = 60, unique = true)
	private String nombre;
	
}
