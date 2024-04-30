package com.example.amazonclone.services;

import com.example.amazonclone.dto.ProductReviewDto;
import com.example.amazonclone.exceptions.NotFoundException;
import com.example.amazonclone.models.Product;
import com.example.amazonclone.models.ProductReview;
import com.example.amazonclone.repos.ProductRepository;
import com.example.amazonclone.repos.ProductReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductReviewService implements JpaService<ProductReviewDto, ProductReview, Long> {

    private final ProductReviewRepository productReviewRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ProductReviewService(ProductReviewRepository productReviewRepository, ProductRepository productRepository) {
        this.productReviewRepository = productReviewRepository;
        this.productRepository = productRepository;
    }

    private ProductReview getProductReview(Long id) throws NotFoundException {
        for(ProductReview productReview : productReviewRepository.findAll())
            if(productReview.getId().equals(id))
                return productReview;
        throw new NotFoundException("Product review was not found!");
    }

    @Override
    public ProductReviewDto get(Long id) throws NotFoundException {
        return new ProductReviewDto(getProductReview(id));
    }

    @Override
    public List<ProductReviewDto> getAll(PageRequest pageRequest) {
        List<ProductReviewDto> productReviewDtos = new ArrayList<>();
        Page<ProductReview> page = productReviewRepository.findAll(pageRequest);

        page.getContent().forEach(x->productReviewDtos.add(new ProductReviewDto(x)));

        return productReviewDtos;
    }

    @Override
    public List<ProductReviewDto> getAll() {
        List<ProductReviewDto> productReviewDtos = new ArrayList<>();

        productReviewRepository.findAll().forEach(x->productReviewDtos.add(new ProductReviewDto(x)));

        return productReviewDtos;
    }

    @Override
    public void add(ProductReviewDto dtoEntity) throws NotFoundException {
        ProductReview productReview = dtoEntity.buildEntity();
        for (Product product : productRepository.findAll())
            if(dtoEntity.getProductId().equals(product.getId()))
                productReview.setProduct(product);
        if(productReview.getProduct() == null)
            throw new NotFoundException("Product was not found!");

        productReviewRepository.save(productReview);
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        productReviewRepository.delete(getProductReview(id));
    }

    @Override
    public void update(Long id, ProductReviewDto dtoEntity) throws NotFoundException {
        delete(id);

        productReviewRepository.save(dtoEntity.buildEntity(id));
    }
}
