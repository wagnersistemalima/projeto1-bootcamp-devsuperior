package com.sistemalima.dscatalog.dto;

import java.io.Serializable;

import com.sistemalima.dscatalog.entities.Category;

// objeto para carregar os dados

public class CategoryDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	// atributos basicos
	
	private Long id;
	private String name;
	
	// construtor padrão
	
	public CategoryDTO() {
		
	}
	
	// construtor com argumento

	public CategoryDTO(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	// construtor recebendo uma entidade
	
	public CategoryDTO(Category entity) {
		this.id = entity.getId();
		this.name = entity.getName();
	}
	
	// Getters & Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
