package com.app.model;

import javax.validation.constraints.NotBlank;

public class Grupo {
	
	private long id;
	
	@NotBlank(message = "Grupo es requerido")
	private String nombre;
	
	public Grupo() {}
	
	public Grupo(long id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}
	
	public Grupo(String nombre) {
		this.nombre = nombre;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Grupo [id=" + id + ", nombre=" + nombre + "]";
	}
	
}
