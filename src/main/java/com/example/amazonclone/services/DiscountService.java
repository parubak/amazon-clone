package com.example.amazonclone.services;

import com.example.amazonclone.dto.DiscountDto;
import com.example.amazonclone.exceptions.NotFoundException;
import com.example.amazonclone.models.Discount;
import com.example.amazonclone.models.Product;
import com.example.amazonclone.models.ProductColor;
import com.example.amazonclone.repos.DiscountRepository;
import com.example.amazonclone.repos.ProductColorRepository;
import com.example.amazonclone.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class DiscountService implements CrudService<DiscountDto, Discount, Long>{
    private final DiscountRepository discountRepository;
    private final ProductColorRepository productColorRepository;

    @Autowired
    public DiscountService(DiscountRepository repository, ProductColorRepository productColorRepository) {
        this.discountRepository = repository;
        this.productColorRepository = productColorRepository;
    }

    private Discount getDiscount(Long id) throws NotFoundException {
        Iterable<Discount> discounts = discountRepository.findAll();

        for(Discount discount : discounts) {
            if(discount.getId().equals(id))
                return discount;
        }

        throw new NotFoundException("Discount was not found");
    }

    @Override
    public DiscountDto get(Long id) throws NotFoundException {
        return new DiscountDto(getDiscount(id));
    }

    @Override
    public List<DiscountDto> getAll() {
        List<DiscountDto> discountDtos = new LinkedList<>();

        discountRepository.findAll().forEach(x->discountDtos.add(new DiscountDto(x)));

        return discountDtos;
    }

    @Override
    public void add(DiscountDto dtoEntity) throws NotFoundException {
        Discount discount = dtoEntity.buildEntity();

        Iterable<ProductColor> productColors = productColorRepository.findAll();

        for (ProductColor productColor : productColors) {
            if(productColor.getId().equals(dtoEntity.getProductColorId()))
                discount.setProductColor(productColor);
        }

        if(discount.getProductColor() == null)
            throw new NotFoundException("Product color was not found");

        discountRepository.save(discount);
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        Discount discount = getDiscount(id);

        discountRepository.delete(discount);
    }

    @Override
    public void update(Long id, DiscountDto dtoEntity) throws NotFoundException {
        delete(id);

        discountRepository.save(dtoEntity.buildEntity(id));
    }
}
