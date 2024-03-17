package com.example.amazonclone.services;

import com.example.amazonclone.dto.DiscountDto;
import com.example.amazonclone.exceptions.DiscountNotFoundException;
import com.example.amazonclone.exceptions.ProductNotFoundException;
import com.example.amazonclone.models.Discount;
import com.example.amazonclone.models.Product;
import com.example.amazonclone.repos.DiscountRepository;
import com.example.amazonclone.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class DiscountService implements CrudService<DiscountDto, Discount, Long>{
    private final DiscountRepository discountRepository;
    private final ProductRepository productRepository;

    @Autowired
    public DiscountService(DiscountRepository repository, ProductRepository productRepository) {
        this.discountRepository = repository;
        this.productRepository = productRepository;
    }

    private Discount getDiscount(Long id) throws DiscountNotFoundException {
        Iterable<Discount> discounts = discountRepository.findAll();

        for(Discount discount : discounts) {
            if(discount.getId().equals(id))
                return discount;
        }

        throw new DiscountNotFoundException("Discount was not found");
    }

    @Override
    public DiscountDto get(Long id) throws DiscountNotFoundException {
        return new DiscountDto(getDiscount(id));
    }

    @Override
    public List<DiscountDto> getAll() {
        List<DiscountDto> discountDtos = new LinkedList<>();

        discountRepository.findAll().forEach(x->discountDtos.add(new DiscountDto(x)));

        return discountDtos;
    }

    @Override
    public void add(DiscountDto dtoEntity) throws ProductNotFoundException {
        Discount discount = dtoEntity.buildEntity();

        Iterable<Product> products = productRepository.findAll();

        for (Product product : products) {
            if(product.getId().equals(dtoEntity.getProductId()))
                discount.setProduct(product);
        }

        if(discount.getProduct() == null)
            throw new ProductNotFoundException("Product was not found");

        discountRepository.save(discount);
    }

    @Override
    public void delete(Long id) throws DiscountNotFoundException {
        Discount discount = getDiscount(id);

        discountRepository.delete(discount);
    }

    @Override
    public void update(Long id, DiscountDto dtoEntity) throws DiscountNotFoundException {
        delete(id);

        Discount discount = dtoEntity.buildEntity();
        discount.setId(id);
        discountRepository.save(discount);
    }
}
