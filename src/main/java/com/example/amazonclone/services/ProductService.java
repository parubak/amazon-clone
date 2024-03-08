package com.example.amazonclone.services;

import com.example.amazonclone.dto.ProductDto;
import com.example.amazonclone.models.Product;
import com.example.amazonclone.repos.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends DtoService<ProductDto, Product, Long>{
    public ProductService(ProductRepository repository) {
        super(repository);
    }
}
