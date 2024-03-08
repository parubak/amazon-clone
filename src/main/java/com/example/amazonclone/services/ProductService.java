package com.example.amazonclone.services;

import com.example.amazonclone.dto.ProductDto;
import com.example.amazonclone.exceptions.UserNotFoundException;
import com.example.amazonclone.models.Product;
import com.example.amazonclone.repos.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public void save(ProductDto product) {
        productRepository.save(product.build());
    };

    public void delete(long id) throws UserNotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if(optionalProduct.isEmpty())
            throw new UserNotFoundException("Пользователь не найден!");

        productRepository.deleteById(id);
    }

    public void update(long id, ProductDto productDto) throws UserNotFoundException {
        delete(id);

        Product product = productDto.build();
        product.setId(id);
        productRepository.save(product);
    }


    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
