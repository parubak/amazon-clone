package com.example.amazonclone.services;

import com.example.amazonclone.dto.ProductColorImageDto;
import com.example.amazonclone.exceptions.NotFoundException;
import com.example.amazonclone.models.Product;
import com.example.amazonclone.models.ProductColor;
import com.example.amazonclone.models.ProductColorImage;
import com.example.amazonclone.repos.ProductColorImageRepository;
import com.example.amazonclone.repos.ProductColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ProductColorImageService implements CrudService<ProductColorImageDto, ProductColorImage, Long>{

    private final ProductColorImageRepository productImageRepository;
    private final ProductColorRepository productColorRepository;

    @Autowired
    public ProductColorImageService(ProductColorImageRepository productImageRepository, ProductColorRepository productColorRepository) {
        this.productImageRepository = productImageRepository;
        this.productColorRepository = productColorRepository;
    }

    private ProductColorImage getImage(Long id) throws NotFoundException {
        Iterable<ProductColorImage> productImages = productImageRepository.findAll();

        for (ProductColorImage productColorImage : productImages) {
            if(productColorImage.getId().equals(id))
                return productColorImage;
        }
        throw new NotFoundException("Product image was not found");
    }

    @Override
    public ProductColorImageDto get(Long id) throws NotFoundException {
        return new ProductColorImageDto(getImage(id));
    }

    @Override
    public List<ProductColorImageDto> getAll() {
        List<ProductColorImageDto> productImageDtos = new LinkedList<>();

        productImageRepository.findAll().forEach(x->productImageDtos.add(new ProductColorImageDto(x)));

        return productImageDtos;
    }

    @Override
    public void add(ProductColorImageDto dtoEntity) throws NotFoundException {
        ProductColorImage productColorImage = dtoEntity.buildEntity();

        Iterable<ProductColor> productColors = productColorRepository.findAll();

        for (ProductColor productColor : productColors) {
            if(productColor.getId().equals(dtoEntity.getProductColorId()))
                productColorImage.setProductColor(productColor);
        }
        if(productColorImage.getProductColor() == null)
            throw new NotFoundException("Product was not found");

        productImageRepository.save(productColorImage);
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        ProductColorImage image = getImage(id);

        productImageRepository.delete(image);
    }

    @Override
    public void update(Long id, ProductColorImageDto dtoEntity) throws NotFoundException {
        delete(id);

        productImageRepository.save(dtoEntity.buildEntity(id));
    }
}
