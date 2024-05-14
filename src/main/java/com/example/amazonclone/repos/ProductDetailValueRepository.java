package com.example.amazonclone.repos;

import com.example.amazonclone.models.ProductDetailValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ProductDetailValueRepository extends RefreshableRepository<ProductDetailValue, Long> {
}
