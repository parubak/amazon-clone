package com.example.amazonclone.repos;

import com.example.amazonclone.models.Identity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface IdentityRepository extends CrudRepository<Identity, Long> {

//    public ArrayList<Identity> findAllByOrderByProductPriceAsc();
//    public ArrayList<Identity> findAllByOrderByProductPriceDesc();

//    List<Passenger> findByOrderBySeatNumberAsc();
}

