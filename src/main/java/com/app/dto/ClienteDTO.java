package com.app.dto;

import java.util.ArrayList;
import java.util.List;

import com.app.model.Cliente;

public class ClienteDTO {

	private long id;
	private String nombre;
	private String cif;
	private String direccion;
	private String grupo;
	
	public ClienteDTO() {}
	
	public ClienteDTO(Cliente cliente) {
		this.id = (cliente.getId() != 0 ? cliente.getId() : 0);
		this.nombre = cliente.getNombre();
		this.cif = cliente.getCif();
		this.direccion = cliente.getDireccion();
		this.grupo = cliente.getGrupo().getNombre();
	}
	
	public static List<ClienteDTO> getClienteDTO(List<Cliente> clientes) {
		List<ClienteDTO> newClient = new ArrayList<>();
		clientes.stream().forEach(o ->  newClient.add(new ClienteDTO(o)));
		return newClient;
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

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	@Override
	public String toString() {
		return "ClienteDTO [id=" + id + ", nombre=" + nombre + ", cif=" + cif + ", direccion=" + direccion + ", grupo="
				+ grupo + "]";
	}
	
}
