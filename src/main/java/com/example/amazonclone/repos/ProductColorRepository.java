package com.example.amazonclone.repos;

import com.example.amazonclone.models.ProductColor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProductColorRepository extends JpaRepository<ProductColor, Long> {
}
