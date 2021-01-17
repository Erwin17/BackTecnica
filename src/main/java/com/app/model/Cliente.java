package com.app.model;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Cliente {

	private long id;
	
	@NotBlank(message = "Nombre es requerido")
	private String nombre;
	
	@NotBlank(message = "CIF es requerido")
	private String cif;
	
	@NotBlank(message = "Direccion es requerido")
	private String direccion;
	
	@Valid
	@NotNull(message = "Grupo es requerido")
	private Grupo grupo;
	
	public Cliente() {}
	
	public Cliente(long id, String nombre, String cif, String direccion, Grupo grupo) {
		this.id = id;
		this.nombre = nombre;
		this.cif = cif;
		this.direccion = direccion;
		this.grupo = grupo;
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

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	
	public Grupo getGrupo() {
		return grupo;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + ", cif=" + cif + ", direccion=" + direccion + ", grupo="
				+ grupo + "]";
	}
	
}
