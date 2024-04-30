package com.example.amazonclone.services;

import com.example.amazonclone.dto.DiscountDto;
import com.example.amazonclone.dto.ProductColorDto;
import com.example.amazonclone.exceptions.NotFoundException;
import com.example.amazonclone.models.Discount;
import com.example.amazonclone.models.DiscountType;
import com.example.amazonclone.models.ProductColor;
import com.example.amazonclone.repos.DiscountRepository;
import com.example.amazonclone.repos.DiscountTypeRepository;
import com.example.amazonclone.repos.ProductColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class DiscountService implements JpaService<DiscountDto, Discount, Long> {
    private final DiscountRepository discountRepository;
    private final ProductColorRepository productColorRepository;
    private final DiscountTypeRepository discountTypeRepository;

    @Autowired
    public DiscountService(DiscountRepository repository,
                           ProductColorRepository productColorRepository,
                           DiscountTypeRepository discountTypeRepository) {
        this.discountRepository = repository;
        this.productColorRepository = productColorRepository;
        this.discountTypeRepository = discountTypeRepository;
    }

    private Discount getDiscount(Long id) throws NotFoundException {
        Iterable<Discount> discounts = discountRepository.findAll();

        for(Discount discount : discounts) {
            if(discount.getId().equals(id))
                return discount;
        }

        throw new NotFoundException("Discount was not found");
    }

    private List<ProductColorDto> getProductColors() {
        List<ProductColorDto> productColorDtos = new ArrayList<>();

        productColorRepository.findAll().forEach(x->productColorDtos.add(new ProductColorDto(x)));

        return productColorDtos;
    }

    @Override
    public DiscountDto get(Long id) throws NotFoundException {
        return new DiscountDto(getDiscount(id));
    }

    @Override
    public List<DiscountDto> getAll(PageRequest pageRequest) {
        List<DiscountDto> discountDtos = new LinkedList<>();
        Page<Discount> page = discountRepository.findAll(pageRequest);

        page.getContent().forEach(x->discountDtos.add(new DiscountDto(x)));

        return discountDtos;
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

        for (ProductColor productColor : productColorRepository.findAll()) {
            if(productColor.getId().equals(dtoEntity.getProductColorId()))
                discount.setProductColor(productColor);
        }

        for (DiscountType discountType : discountTypeRepository.findAll()) {
            if(discountType.getId().equals(dtoEntity.getDiscountTypeId()))
                discount.setDiscountType(discountType);
        }

        if(discount.getProductColor() == null)
            throw new NotFoundException("Product color was not found");
        if(discount.getDiscountType() == null)
            throw new NotFoundException("Discount type was not found");

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
