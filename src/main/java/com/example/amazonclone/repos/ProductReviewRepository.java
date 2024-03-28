package com.example.amazonclone.repos;

import com.example.amazonclone.models.ProductReview;
import org.springframework.data.repository.CrudRepository;

public interface ProductReviewRepository extends CrudRepository<ProductReview, Long> {
}
