package com.sistemalima.dscatalog.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.sistemalima.dscatalog.entities.Category;
import com.sistemalima.dscatalog.entities.Product;

// objeto para carregar os dados

public class ProductDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	// atributos basicos
	
	private Long id;
	private String name;
	private String description;
	private double price;
	private String imgUrl;
	
	private Instant date;
	
	private List<CategoryDTO> categories = new ArrayList<>();
	
	// construtor padrao
	
	public ProductDTO() {
		
	}
	
	// construtor com argumentos

	public ProductDTO(Long id, String name, String description, double price, String imgUrl, Instant date) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
		this.date = date;
	}
	
	
	// construtor recebendo uma entidade
	
	public ProductDTO(Product entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.description = entity.getDescription();
		this.price = entity.getPrice();
		this.imgUrl = entity.getImgUrl();
		this.date = entity.getDate();
	}
	
	// construtor recebendo uma entidade e uma coleção
	
	public ProductDTO(Product entity, Set<Category> categories) {
		this(entity);
		categories.forEach(cat -> this.categories.add(new CategoryDTO(cat)));
	}
	
	// getters & setters

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Instant getDate() {
		return date;
	}

	public void setDate(Instant date) {
		this.date = date;
	}

	public List<CategoryDTO> getCategories() {
		return categories;
	}

}
