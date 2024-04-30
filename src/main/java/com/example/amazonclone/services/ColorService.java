package com.example.amazonclone.services;

import com.example.amazonclone.dto.ColorDto;
import com.example.amazonclone.exceptions.NotFoundException;
import com.example.amazonclone.models.Color;
import com.example.amazonclone.models.ProductColor;
import com.example.amazonclone.repos.ColorRepository;
import com.example.amazonclone.repos.ProductColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ColorService implements JpaService<ColorDto, Color, Long> {

    private final ColorRepository colorRepository;
    private final ProductColorRepository productColorRepository;

    @Autowired
    public ColorService(ColorRepository colorRepository, ProductColorRepository productColorRepository) {
        this.colorRepository = colorRepository;
        this.productColorRepository = productColorRepository;
    }

    private Color getColor(Long id) throws NotFoundException {
        for (Color color : colorRepository.findAll())
            if(color.getId().equals(id))
                return color;

        throw new NotFoundException("Color was not found!");
    }

    @Override
    public ColorDto get(Long id) throws NotFoundException {
        return new ColorDto(getColor(id));
    }

    public ColorDto getByProductColorId(Long productColorId) throws NotFoundException {
        for (ProductColor productColor : productColorRepository.findAll())
            if(productColor.getId().equals(productColorId))
                return new ColorDto(productColor.getColor());

        throw new NotFoundException("Product color was not found!");
    }

    @Override
    public List<ColorDto> getAll(PageRequest pageRequest) {
        List<ColorDto> colorDtos = new ArrayList<>();

        colorRepository.findAll(pageRequest).forEach(color -> colorDtos.add(new ColorDto(color)));

        return colorDtos;
    }

    @Override
    public List<ColorDto> getAll() {
        List<ColorDto> colorDtos = new ArrayList<>();

        colorRepository.findAll().forEach(color->colorDtos.add(new ColorDto(color)));

        return colorDtos;
    }

    @Override
    public void add(ColorDto dtoEntity) {
        colorRepository.save(dtoEntity.buildEntity());
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        for (Color color : colorRepository.findAll())
            if(color.getId().equals(id))
                colorRepository.delete(color);

        throw new NotFoundException("Color was not found!");
    }

    @Override
    public void update(Long id, ColorDto dtoEntity) throws NotFoundException {
        delete(id);

        colorRepository.save(dtoEntity.buildEntity(id));
    }
}
