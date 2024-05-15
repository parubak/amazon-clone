package com.example.amazonclone.repos;

import com.example.amazonclone.models.Order;
import com.example.amazonclone.models.ProductItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

    public ArrayList<Order> findAllByUser_Id(Long id);
    public ArrayList<Order> findAllByUser_IdAndStatus(Long id, String status);
//    public ArrayList<Order> findAllByUser_IdAndStatusaAndProductItems(Long id, String status);

}
