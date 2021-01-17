package com.app.repository;

import java.sql.ResultSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.model.Cliente;
import com.app.model.Grupo;


@Repository
public class ClienteRepository implements CommonRepository<Cliente>{
	
	private static final String SQL_QUERY_FIND_ALL = "SELECT a.id, a.nombre, a.cif, a.direccion, b.nombre as grupo FROM CLIENTE a INNER JOIN GRUPO b ON a.idGrupo = b.id ";
	private static final String SQL_QUERY_FIND_BY_ID = SQL_QUERY_FIND_ALL + "WHERE a.id = :id";
	private static final String SQL_QUERY_FIND_BY_NAME = SQL_QUERY_FIND_ALL + "WHERE a.nombre LIKE :nombre";
	private static final String SQL_QUERY_DELETE_CLEINTE = "DELETE FROM CLIENTE WHERE id = :id";
	private static final String SQL_QUERY_SAVE_CLIENTE = "INSERT INTO CLIENTE (idGrupo, nombre, cif, direccion) VALUES (:idGrupo, :nombre, :cif, :direccion)";

	private final NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
	public ClienteRepository(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Transactional
	@Override
	public Cliente save(Cliente model) {
		Map<String, String> parameter1 = new HashMap<>();
		parameter1.put("idGrupo", model.getGrupo().getNombre());
		parameter1.put("nombre", model.getNombre());
		parameter1.put("cif", model.getCif());
		parameter1.put("direccion", model.getDireccion());

		this.jdbcTemplate.update(SQL_QUERY_SAVE_CLIENTE, parameter1);
		
		return model;
	}

	@Override
	public void delete(Cliente model) {
		Map<String, Long> parameter = new HashMap<>();
		parameter.put("id", model.getId());	
		this.jdbcTemplate.update(SQL_QUERY_DELETE_CLEINTE, parameter);
	}

	@Override
	public Cliente update(Cliente model) {
		throw new UnsupportedOperationException("Metodo no implementado");
	}

	@Override
	public List<Cliente> findAll() {
		return this.jdbcTemplate.query(SQL_QUERY_FIND_ALL, clienteRowMapper);
	}

	@Override
	public Cliente findById(long id) {
		try {
			Map<String, Long> namedParameters = Collections.
					singletonMap("id", id);
			return this.jdbcTemplate.queryForObject(SQL_QUERY_FIND_BY_ID, namedParameters, clienteRowMapper);
		}catch(Exception e) {
			throw new IllegalStateException("Error en la consulta a la base de datos");
		}
	}

	@Override
	public List<Cliente> findByName(String nombre) {
		Map<String, String> parameters = Collections.singletonMap("nombre", "%"+nombre+"%");
		return this.jdbcTemplate.query(SQL_QUERY_FIND_BY_NAME, parameters, clienteRowMapper);
	}

	private RowMapper<Cliente> clienteRowMapper = (ResultSet rs, int rowNum) -> {
		Cliente cliente = new Cliente();
		cliente.setId(rs.getLong("id"));
		cliente.setNombre(rs.getString("nombre"));
		cliente.setCif(rs.getString("cif"));
		cliente.setDireccion(rs.getString("direccion"));
		cliente.setGrupo(new Grupo(rs.getString("grupo")));
		return cliente;
		
	};

}
