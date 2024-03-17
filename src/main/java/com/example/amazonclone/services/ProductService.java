package com.example.amazonclone.services;

import com.example.amazonclone.controllers.ProductImageController;
import com.example.amazonclone.dto.ProductDto;
import com.example.amazonclone.exceptions.NotFoundException;
import com.example.amazonclone.exceptions.ProductImageNotFoundException;
import com.example.amazonclone.exceptions.ProductNotFoundException;
import com.example.amazonclone.exceptions.SubcategoryNotFoundException;
import com.example.amazonclone.models.Product;
import com.example.amazonclone.models.ProductImage;
import com.example.amazonclone.models.Subcategory;
import com.example.amazonclone.repos.ProductImageRepository;
import com.example.amazonclone.repos.ProductRepository;
import com.example.amazonclone.repos.SubcategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductService implements CrudService<ProductDto, Product, Long>{

    private final ProductRepository productRepository;
    private final SubcategoryRepository subcategoryRepository;

    @Autowired
    public ProductService(ProductRepository repository, SubcategoryRepository subcategoryRepository) {
        this.productRepository = repository;
        this.subcategoryRepository = subcategoryRepository;
    }

    private Product getProduct(Long id) throws ProductNotFoundException {
        Iterable<Product> products = productRepository.findAll();

        for (Product product : products) {
            if(product.getId().equals(id))
                return product;
        }

        throw new ProductNotFoundException("Product was not found");
    }

    @Override
    public ProductDto get(Long id) throws ProductNotFoundException {
        return new ProductDto(getProduct(id));
    }

    @Override
    public List<ProductDto> getAll() {
        List<ProductDto> productDtos = new LinkedList<>();

        productRepository.findAll().forEach(x->productDtos.add(new ProductDto(x)));

        return productDtos;
    }

    @Override
    public void add(ProductDto dtoEntity) throws SubcategoryNotFoundException {
        Product product = dtoEntity.buildEntity();

        Iterable<Subcategory> subcategories = subcategoryRepository.findAll();
        for (Subcategory subcategory : subcategories) {
            if(subcategory.getId().equals(dtoEntity.getSubcategoryId()))
                product.setSubcategory(subcategory);
        }
        if(product.getSubcategory() == null)
            throw new SubcategoryNotFoundException("Subcategory was not found");

        productRepository.save(product);
    }

    @Override
    public void delete(Long id) throws ProductNotFoundException {
        Product product = getProduct(id);

        productRepository.delete(product);
    }

    @Override
    public void update(Long id, ProductDto dtoEntity) throws ProductNotFoundException {
        delete(id);

        Product product = dtoEntity.buildEntity();
        product.setId(id);
        productRepository.save(product);
    }
}
