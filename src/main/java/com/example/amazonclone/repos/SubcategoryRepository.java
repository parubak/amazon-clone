package com.example.amazonclone.repos;

import com.example.amazonclone.models.Subcategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubcategoryRepository extends CrudRepository<Subcategory, Long> {
}
