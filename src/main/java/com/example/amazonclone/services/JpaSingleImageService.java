package com.example.amazonclone.services;

import com.example.amazonclone.dto.DtoEntity;
import com.example.amazonclone.exceptions.ImageAlreadyExistsException;
import com.example.amazonclone.exceptions.NotFoundException;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface JpaSingleImageService<Dto extends DtoEntity<Entity, Id>, Entity, Id> {
    public Dto get(Id id) throws NotFoundException;
    public List<Dto> getAll(PageRequest pageRequest);
    public List<Dto> getAll();
    public void add(Dto dtoEntity) throws NotFoundException, ImageAlreadyExistsException;
    public void delete(Id id) throws NotFoundException;
    public void update(Id id, Dto dtoEntity) throws NotFoundException;
}