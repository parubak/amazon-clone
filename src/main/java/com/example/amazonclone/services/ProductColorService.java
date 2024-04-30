package com.example.amazonclone.services;

import com.example.amazonclone.dto.ProductColorDto;
import com.example.amazonclone.exceptions.NotFoundException;
import com.example.amazonclone.models.*;
import com.example.amazonclone.repos.ColorRepository;
import com.example.amazonclone.repos.ProductColorRepository;
import com.example.amazonclone.repos.ProductRepository;
import com.example.amazonclone.repos.ProductSizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductColorService implements JpaService<ProductColorDto, ProductColor, Long> {

    private final ProductColorRepository productColorRepository;
    private final ProductRepository productRepository;
    private final ProductSizeRepository productSizeRepository;
    private final ColorRepository colorRepository;

    @Autowired
    public ProductColorService(ProductColorRepository productColorRepository,
                               ProductRepository productRepository,
                               ProductSizeRepository productSizeRepository,
                               ColorRepository colorRepository
                                ) {
        this.productColorRepository = productColorRepository;
        this.productRepository = productRepository;
        this.productSizeRepository = productSizeRepository;
        this.colorRepository = colorRepository;
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
    public List<ProductColorDto> getAll(PageRequest pageRequest) {
        List<ProductColorDto> productColorDtos = new ArrayList<>();
        Page<ProductColor> page = productColorRepository.findAll(pageRequest);

        page.getContent().forEach(x->productColorDtos.add(new ProductColorDto(x)));

        return productColorDtos;
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

        for (Color color : colorRepository.findAll())
            if(color.getId().equals(dtoEntity.getColorId()))
                productColor.setColor(color);
        if(productColor.getColor() == null)
            throw new NotFoundException("Color was not found!");

        productColorRepository.save(productColor);
    }

    public void addProductSize(Long productColorId, Long productSizeId) throws NotFoundException {
        ProductColor productColor = getProductColor(productColorId);
        for(ProductSize productSize : productSizeRepository.findAll()) {
            if(productSize.getId().equals(productSizeId)) {
                productColor.getProductSizes().add(productSize);
                productSize.getProductColors().add(productColor);
                productSizeRepository.save(productSize);
            }
        }

        productColorRepository.save(productColor);
    }

    public void setMainImage(Long productColorId, Long productColorImageId) throws NotFoundException {
        ProductColor productColor = getProductColor(productColorId);

        for (ProductColorImage productColorImage : productColor.getProductColorImages()) {
            if(productColorImage.getId().equals(productColorImageId)) {
                productColor.setMainImage(productColorImage);
                productColorRepository.save(productColor);
                return;
            }
        }
        if(productColor.getMainImage() == null)
            throw new NotFoundException("Product color image was not found!");
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        productColorRepository.delete(getProductColor(id));
    }

    @Override
    public void update(Long id, ProductColorDto dtoEntity) throws NotFoundException {
        delete(id);

        add(dtoEntity);
    }
}
