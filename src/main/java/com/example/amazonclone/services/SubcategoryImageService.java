package com.example.amazonclone.services;

import com.example.amazonclone.dto.SubcategoryImageDto;
import com.example.amazonclone.exceptions.SubcategoryImageNotFoundException;
import com.example.amazonclone.exceptions.SubcategoryNotFoundException;
import com.example.amazonclone.models.*;
import com.example.amazonclone.repos.SubcategoryImageRepository;
import com.example.amazonclone.repos.SubcategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class SubcategoryImageService implements CrudService<SubcategoryImageDto, SubcategoryImage, Long>{

    private final SubcategoryImageRepository subcategoryImageRepository;
    private final SubcategoryRepository subcategoryRepository;

    @Autowired
    public SubcategoryImageService(SubcategoryImageRepository subcategoryImageRepository, SubcategoryRepository subcategoryRepository) {
        this.subcategoryImageRepository = subcategoryImageRepository;
        this.subcategoryRepository = subcategoryRepository;
    }

    private SubcategoryImage getImage(Long id) throws SubcategoryImageNotFoundException {
        Iterable<SubcategoryImage> subcategoryImages = subcategoryImageRepository.findAll();

        for (SubcategoryImage subcategoryImage : subcategoryImages) {
            if(subcategoryImage.getId().equals(id))
                return subcategoryImage;
        }
        throw new SubcategoryImageNotFoundException("Subcategory image was not found");
    }

    @Override
    public SubcategoryImageDto get(Long id) throws SubcategoryImageNotFoundException {
        return new SubcategoryImageDto(getImage(id));
    }

    public SubcategoryImageDto getBySubcategory(Long subcategoryId) throws SubcategoryNotFoundException, SubcategoryImageNotFoundException {
        for(Subcategory subcategory : subcategoryRepository.findAll()) {
            if(subcategory.getId().equals(subcategoryId)) {
                if(subcategory.getSubcategoryImage() != null)
                    return new SubcategoryImageDto(subcategory.getSubcategoryImage());
                throw new SubcategoryImageNotFoundException("Subcategory image was not found");
            }
        }
        throw new SubcategoryNotFoundException("Subcategory was not found");
    }

    @Override
    public List<SubcategoryImageDto> getAll() {
        List<SubcategoryImageDto> subcategoryImageDtos = new LinkedList<>();

        subcategoryImageRepository.findAll().forEach(x->subcategoryImageDtos.add(new SubcategoryImageDto(x)));

        return subcategoryImageDtos;
    }

    @Override
    public void add(SubcategoryImageDto dtoEntity) throws SubcategoryNotFoundException {
        SubcategoryImage subcategoryImage = dtoEntity.buildEntity();

        Iterable<Subcategory> subcategories = subcategoryRepository.findAll();

        for (Subcategory subcategory : subcategories) {
            if(subcategory.getId().equals(dtoEntity.getSubcategoryId()))
                subcategoryImage.setSubcategory(subcategory);
        }

        if(subcategoryImage.getSubcategory() == null)
            throw new SubcategoryNotFoundException("Product was not found");

        subcategoryImageRepository.save(subcategoryImage);
    }

    @Override
    public void delete(Long id) throws SubcategoryImageNotFoundException {
        SubcategoryImage image = getImage(id);

        subcategoryImageRepository.delete(image);
    }

    @Override
    public void update(Long id, SubcategoryImageDto dtoEntity) throws SubcategoryImageNotFoundException {
        delete(id);

        SubcategoryImage subcategoryImage = dtoEntity.buildEntity();
        subcategoryImage.setId(id);
        subcategoryImageRepository.save(subcategoryImage);
    }
}
