package com.example.amazonclone.services;

import com.example.amazonclone.dto.ProductDTO;
import com.example.amazonclone.models.Identity;
import com.example.amazonclone.models.Product;
import com.example.amazonclone.repos.IdentityRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class IdentityService {
    IdentityRepository identityRepository;

    public IdentityService(IdentityRepository identityRepository) {
        this.identityRepository = identityRepository;
    }

    public Identity getById(Long id) {
        return identityRepository.findById(id).get();
    }

    public ArrayList<ProductDTO> getAllProductDTO() {
        return bildProductDTO((ArrayList<Identity>) identityRepository.findAll());
    }

//    public ArrayList<ProductDTO> findAllByOrderByProductPriceAsc() {
//        return bildProductDTO(identityRepository.findAllByOrderByProductPriceAsc());
//    }
//
//    public ArrayList<ProductDTO> findAllByOrderByProductPriceDesc() {
//        return bildProductDTO(identityRepository.findAllByOrderByProductPriceDesc());
//    }

    private ArrayList<ProductDTO> bildProductDTO(ArrayList<Identity> identities) {
        ArrayList<ProductDTO> dtos = new ArrayList<>();
        identities.forEach(i -> {
            Product p = i.getProduct();
            p.getProductItems().forEach(t -> dtos.add(new ProductDTO(t, t.getDiscount(), t.getImage(), p)));
        });
        return dtos;
    }
}
