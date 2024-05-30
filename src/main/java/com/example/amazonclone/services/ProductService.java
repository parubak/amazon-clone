package com.example.amazonclone.services;

import com.example.amazonclone.models.*;
import com.example.amazonclone.repos.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductService {
    @Value("${upload.path}")
    private String uploadPath;

    OrderRepository orderRepository;
    ProductRepository productRepository;

    ProductItemRepository productItemRepository;

    CommentRepository commentRepository;

    CommentImageRepository commentImageRepository;

    SellerRepository sellerRepository;

    public ProductService(ProductRepository productRepository, ProductItemRepository productItemRepository,
                          OrderRepository orderRepository, CommentRepository commentRepository, CommentImageRepository commentImageRepository
            , SellerRepository sellerRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.productItemRepository = productItemRepository;
        this.commentRepository = commentRepository;
        this.commentImageRepository = commentImageRepository;
        this.sellerRepository=sellerRepository;

    }

    public ProductItem getProductItemById(Long id) {
        return productItemRepository.findById(id).orElseThrow();
    }

    public ArrayList<Product> getAll() {
        return (ArrayList<Product>) productRepository.findAll();
    }

    public String getPath() {
        return uploadPath;
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);

    }

    public Order getOrder(Long id) {
        return orderRepository.findById(id).get();
    }

    public ArrayList<Order> getAllOrders() {
        return (ArrayList<Order>) orderRepository.findAll();
    }

    public ArrayList<Order> findAllByUser_Id(Long id) {
        return orderRepository.findAllByUser_Id(id);
    }

    public ArrayList<Order> findAllByUser_IdAndStatus(Long id, String status) {
        return orderRepository.findAllByUser_IdAndStatus(id, status);
    }

    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public CommentImage saveCommentImage(CommentImage image) {
        return commentImageRepository.save(image);
    }

    public Seller getShop(Long id) {
        return sellerRepository.findById(id).get();
    }
    public Page<ProductItem> findAllProductItemPag( PageRequest pageRequest) {
        return productItemRepository.findAll(pageRequest);
    }

    public Page<ProductItem> findAllProductItemPagSortRaiting(Long[] category, PageRequest pageRequest) {
        if (category.length<1){
            return productItemRepository.findAllBySortProductRating(pageRequest);
        }
        else {
            return productItemRepository.findAllBySortProductRating(category, pageRequest);
        }


    }

    public Page<ProductItem> findAllProductItemPagSortId(Long[] category, PageRequest pageRequest) {
        return productItemRepository.findAllBySortId(pageRequest);
    }

    public Page<ProductItem> findAllProductItemPagFiltrs(Integer pFrom, Integer pTo, PageRequest pageRequest) {
        if (pTo == 0){
            return productItemRepository.findAllProductItemPagFiltrsPFrom(pFrom,pageRequest);
        }
        if (pFrom == 0){
            return productItemRepository.findAllProductItemPagFiltrsPTo(pTo,pageRequest);
        }
        return productItemRepository.findAllProductItemPagFiltrs(pFrom, pTo,pageRequest);
    }

    public Page<ProductItem> findAllProductItemGetCategoris(Long[] category, PageRequest pageRequest) {
        if (category.length<1){
            return productItemRepository.findAll(pageRequest);
        }
        else {
        return productItemRepository.findAllProductItemGetCategoris(category,pageRequest);
        }



    }
}
