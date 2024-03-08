package com.example.amazonclone.services;

import com.example.amazonclone.dto.DiscountDto;
import com.example.amazonclone.models.Discount;
import com.example.amazonclone.repos.DiscountRepository;
import org.springframework.stereotype.Service;

@Service
public class DiscountService extends DtoService<DiscountDto, Discount, Long>{
    public DiscountService(DiscountRepository repository) {
        super(repository);
    }
}
