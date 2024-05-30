package com.example.amazonclone.services;

import com.example.amazonclone.models.ProductItem;
import com.example.amazonclone.repos.ProductItemRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilterService {
    ProductItemRepository productItemRepository;

    public FilterService(ProductItemRepository productItemRepository) {
        this.productItemRepository = productItemRepository;
    }


}