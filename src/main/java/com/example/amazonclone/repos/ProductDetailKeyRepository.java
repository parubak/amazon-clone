package com.example.amazonclone.repos;

import com.example.amazonclone.models.ProductDetailKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ProductDetailKeyRepository extends RefreshableRepository<ProductDetailKey, Long> {
}
