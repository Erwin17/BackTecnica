package com.app.model;

public class ClienteBuilder {
	
	private static ClienteBuilder instance = new ClienteBuilder();
	private long id = -1;
	
	public ClienteBuilder() {}
	
	public static ClienteBuilder create() {
		return instance;
	}
	
	
	public ClienteBuilder widthid(long id) {
		this.id = id;
		return instance;
	}
	
	public Cliente builder() {
		Cliente cliente = new Cliente();
		if(this.id >= -1) {
			cliente.setId(this.id);
		}
		return cliente;
	}
}
