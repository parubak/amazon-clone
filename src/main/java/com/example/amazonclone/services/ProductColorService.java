package com.example.amazonclone.services;

import com.example.amazonclone.dto.ProductColorDto;
import com.example.amazonclone.exceptions.NotFoundException;
import com.example.amazonclone.models.Product;
import com.example.amazonclone.models.ProductColor;
import com.example.amazonclone.models.ProductSize;
import com.example.amazonclone.repos.ProductColorRepository;
import com.example.amazonclone.repos.ProductRepository;
import com.example.amazonclone.repos.ProductSizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductColorService implements CrudService<ProductColorDto, ProductColor, Long>{

    private final ProductColorRepository productColorRepository;
    private final ProductRepository productRepository;
    private final ProductSizeRepository productSizeRepository;


    @Autowired
    public ProductColorService(ProductColorRepository productColorRepository, ProductRepository productRepository, ProductSizeRepository productSizeRepository) {
        this.productColorRepository = productColorRepository;
        this.productRepository = productRepository;
        this.productSizeRepository = productSizeRepository;
    }

    private ProductColor getProductColor(Long id) throws NotFoundException {
        for(ProductColor productColor : productColorRepository.findAll())
            if(productColor.getId().equals(id))
                return productColor;
        throw new NotFoundException("Product color was not found!");
    }

    @Override
    public ProductColorDto get(Long id) throws NotFoundException {
        return new ProductColorDto(getProductColor(id));
    }

    @Override
    public List<ProductColorDto> getAll() {
        List<ProductColorDto> productColorDtos = new ArrayList<>();

        productColorRepository.findAll().forEach(x->productColorDtos.add(new ProductColorDto(x)));

        return productColorDtos;
    }

    @Override
    public void add(ProductColorDto dtoEntity) throws NotFoundException {
        ProductColor productColor = dtoEntity.buildEntity();

        for (Product product : productRepository.findAll())
            if(product.getId().equals(dtoEntity.getProductId()))
                productColor.setProduct(product);
        if(productColor.getProduct() == null)
            throw new NotFoundException("Product was not found!");
    }

    public void add(Long productSizeId, Long productColorId) throws NotFoundException {
        ProductColor productColor = getProductColor(productColorId);

        for (ProductSize productSize : productSizeRepository.findAll())
            if(productSize.getId().equals(productSizeId))
                productColor.getProductSizes().add(productSize);
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        productColorRepository.delete(getProductColor(id));
    }

    @Override
    public void update(Long id, ProductColorDto dtoEntity) throws NotFoundException {
        delete(id);

        productColorRepository.save(dtoEntity.buildEntity(id));
    }
}
