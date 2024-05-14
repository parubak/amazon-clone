package com.example.amazonclone.repos;

import com.example.amazonclone.models.SubcategoryImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface SubcategoryImageRepository extends RefreshableRepository<SubcategoryImage, Long> {
}
