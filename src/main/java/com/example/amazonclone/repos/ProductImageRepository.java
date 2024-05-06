package com.example.amazonclone.repos;

import com.example.amazonclone.models.ProductImage;
import org.hibernate.mapping.Array;
import org.hibernate.mapping.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ProductImageRepository extends CrudRepository<ProductImage, Long> {

//    @Query("SELECT p from ProductImage as p where p.t_shirt.id=:id")
//    ArrayList<ProductImage> findAllByT_shirt_id(Long id);
}
