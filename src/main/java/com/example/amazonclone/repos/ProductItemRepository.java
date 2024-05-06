package com.example.amazonclone.repos;

import com.example.amazonclone.models.ProductImage;
import com.example.amazonclone.models.ProductItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductItemRepository extends CrudRepository<ProductItem, Long> {


}
