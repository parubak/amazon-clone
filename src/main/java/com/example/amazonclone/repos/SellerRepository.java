package com.example.amazonclone.repos;

import com.example.amazonclone.models.Group;
import com.example.amazonclone.models.Seller;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends CrudRepository<Seller, Long> {
}
