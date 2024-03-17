package com.example.amazonclone.services;

import com.example.amazonclone.dto.DtoEntity;
import com.example.amazonclone.exceptions.CategoryNotFoundException;
import com.example.amazonclone.exceptions.NotFoundException;
import com.example.amazonclone.exceptions.ProductNotFoundException;
import com.example.amazonclone.exceptions.SubcategoryNotFoundException;

import java.util.List;

public interface CrudService<Dto extends DtoEntity<Entity>, Entity, Id> {
    public Dto get(Id id) throws NotFoundException;
    public List<Dto> getAll();
    public void add(Dto dtoEntity) throws CategoryNotFoundException, SubcategoryNotFoundException, ProductNotFoundException;
    public void delete(Id id) throws NotFoundException;
    public void update(Id id, Dto dtoEntity) throws NotFoundException;
}
