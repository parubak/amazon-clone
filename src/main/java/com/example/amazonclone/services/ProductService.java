package com.example.amazonclone.services;

import com.example.amazonclone.models.Order;
import com.example.amazonclone.models.Product;
import com.example.amazonclone.models.ProductItem;
import com.example.amazonclone.repos.OrderRepository;
import com.example.amazonclone.repos.ProductItemRepository;
import com.example.amazonclone.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class ProductService  {
    @Value("${upload.path}")
    private String uploadPath;

    OrderRepository orderRepository;
    ProductRepository productRepository;

    ProductItemRepository productItemRepository;

    public ProductService(ProductRepository productRepository,     ProductItemRepository productItemRepository, OrderRepository orderRepository) {
this.orderRepository=orderRepository;
        this.productRepository = productRepository;
        this.productItemRepository=productItemRepository;

    }
    public ProductItem getProductItemById(Long id){
        return productItemRepository.findById(id).orElseThrow();
    }

    public ArrayList<Product> getAll() {
        return (ArrayList<Product>) productRepository.findAll();
    }

    public String getPath(){
        return uploadPath;
    }

    public Order saveOrder(Order order){
     return   orderRepository.save(order);

    }

    public Order getOrder(Long id){
        return orderRepository.findById(id).get();
    }

    public ArrayList<Order> getAllOrders(){
        return (ArrayList<Order>) orderRepository.findAll();
    }

    public ArrayList<Order> findAllByUser_Id(Long id){
        return orderRepository.findAllByUser_Id(id);
    }
    public ArrayList<Order> findAllByUser_IdAndStatus(Long id, String status){
        return orderRepository.findAllByUser_IdAndStatus(id,status);
    }

}
