package com.app.repository;

import java.util.List;

public interface CommonRepository<T> {

	public T save(T model);
	public void delete(T model);
	public T update(T model);
	public List<T> findAll();
	public T findById(long id);
	public List<T> findByName(String nombre);
	
}
