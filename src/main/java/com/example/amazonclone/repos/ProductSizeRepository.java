package com.example.amazonclone.repos;

import com.example.amazonclone.models.ProductSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ProductSizeRepository extends RefreshableRepository<ProductSize, Long> {
}
