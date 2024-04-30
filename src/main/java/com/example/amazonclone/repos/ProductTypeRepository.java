package com.example.amazonclone.repos;

import com.example.amazonclone.models.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {

}
