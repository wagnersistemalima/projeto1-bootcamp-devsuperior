package com.sistemalima.dscatalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistemalima.dscatalog.entities.Category;

// camada de acesso a dados

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

}
