package com.example.amazonclone.services;

import com.example.amazonclone.dto.ProductImageDto;
import com.example.amazonclone.exceptions.NotFoundException;
import com.example.amazonclone.exceptions.ProductImageNotFoundException;
import com.example.amazonclone.exceptions.ProductNotFoundException;
import com.example.amazonclone.models.Discount;
import com.example.amazonclone.models.Product;
import com.example.amazonclone.models.ProductImage;
import com.example.amazonclone.repos.ProductImageRepository;
import com.example.amazonclone.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ProductImageService implements CrudService<ProductImageDto, ProductImage, Long>{

    private final ProductImageRepository productImageRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ProductImageService(ProductImageRepository repository, ProductRepository productRepository) {
        this.productImageRepository = repository;
        this.productRepository = productRepository;
    }

    private ProductImage getImage(Long id) throws ProductImageNotFoundException {
        Iterable<ProductImage> productImages = productImageRepository.findAll();

        for (ProductImage productImage : productImages) {
            if(productImage.getId().equals(id))
                return productImage;
        }
        throw new ProductImageNotFoundException("Product image was not found");
    }

    @Override
    public ProductImageDto get(Long id) throws ProductImageNotFoundException {
        return new ProductImageDto(getImage(id));
    }

    @Override
    public List<ProductImageDto> getAll() {
        List<ProductImageDto> productImageDtos = new LinkedList<>();

        productImageRepository.findAll().forEach(x->productImageDtos.add(new ProductImageDto(x)));

        return productImageDtos;
    }

    @Override
    public void add(ProductImageDto dtoEntity) throws ProductNotFoundException {
        ProductImage productImage = dtoEntity.buildEntity();

        Iterable<Product> products = productRepository.findAll();

        for (Product product : products) {
            if(product.getId().equals(dtoEntity.getProductId()))
                productImage.setProduct(product);
        }

        if(productImage.getProduct() == null)
            throw new ProductNotFoundException("Product was not found");

        productImageRepository.save(productImage);
    }

    @Override
    public void delete(Long id) throws ProductImageNotFoundException {
        ProductImage image = getImage(id);

        productImageRepository.delete(image);
    }

    @Override
    public void update(Long id, ProductImageDto dtoEntity) throws ProductImageNotFoundException {
        delete(id);

        ProductImage productImage = dtoEntity.buildEntity();
        productImage.setId(id);
        productImageRepository.save(productImage);
    }
}
