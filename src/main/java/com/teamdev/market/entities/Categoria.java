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
@Table(name = "categorias")
public class Categoria implements Serializable {
	
	@Serial
    private static final long serialVersionUID = 1;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	@Getter @Setter
	private Integer id;
	
	@Column(name = "nombre", nullable = false, length = 100, unique = true)
	@Getter @Setter
	private String nombre;
	

}
