package com.example.amazonclone.services;

import com.example.amazonclone.dto.ProductImageDto;
import com.example.amazonclone.models.ProductImage;
import com.example.amazonclone.repos.ProductImageRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductImageService extends DtoService<ProductImageDto, ProductImage, Long>{
    public ProductImageService(ProductImageRepository repository) {
        super(repository);
    }
}
