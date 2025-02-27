package com.sistemalima.dscatalog.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistemalima.dscatalog.dto.CategoryDTO;
import com.sistemalima.dscatalog.dto.ProductDTO;
import com.sistemalima.dscatalog.entities.Category;
import com.sistemalima.dscatalog.entities.Product;
import com.sistemalima.dscatalog.repositories.CategoryRepository;
import com.sistemalima.dscatalog.repositories.ProductRepository;
import com.sistemalima.dscatalog.service.exceptions.DataBaseException;
import com.sistemalima.dscatalog.service.exceptions.ResourceNotFoundException;

// camada de serviço

@Service
public class ProductService {
	
	// dependencia para a camada de acesso a dados das categorias
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	// dependencia para a camada de acesso a dados
	
	@Autowired
	private ProductRepository repository;
	
	// metodo buscar todos
	
	@Transactional(readOnly = true)
	public Page<ProductDTO> findAllPaged(PageRequest pageRequest) {
		Page<Product> list = repository.findAll(pageRequest);
		return list.map(x -> new ProductDTO(x));
	}
	
	// metodo buscar por id
	
	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		Optional<Product> obj = repository.findById(id);
		Product entity = obj.orElseThrow(()-> new ResourceNotFoundException("Entity not found"));
		return new ProductDTO(entity, entity.getCategories());
	}
	
	// metodo inserir uma novo produto
	
	@Transactional
	public ProductDTO insert(ProductDTO dto) {
		Product entity = new Product();
		copyDTOToEntity(dto, entity);
		entity = repository.save(entity);
		return  new ProductDTO(entity);
	}
	
	// metodo atualizar produto
	
	@Transactional
	public ProductDTO update(Long id, ProductDTO dto) {
		try {
		Product entity = repository.getOne(id);
		copyDTOToEntity(dto, entity);
		entity = repository.save(entity);
		return new ProductDTO(entity);
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found" + id);
		}
	}
	
	// metodo deletar produto
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found");
		}
		catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Integrity violation");
		}
	}
	
	// metodo auxiliar para inserir e atualizar produtos 
	
	private void copyDTOToEntity(ProductDTO dto, Product entity) {
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setDate(dto.getDate());
		entity.setImgUrl(dto.getImgUrl());
		entity.setPrice(dto.getPrice());
		
		
		entity.getCategories().clear();
		
		for (CategoryDTO catDto: dto.getCategories()) {
			Category category =  categoryRepository.getOne(catDto.getId());
			entity.getCategories().add(category);
		}
	}

}
