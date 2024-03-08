package com.example.amazonclone.services;

import com.example.amazonclone.dto.DtoEntity;
import com.example.amazonclone.exceptions.NotFoundException;

public interface CrudService<Dto extends DtoEntity<Entity>, Entity, Id> {
    public Iterable<Entity> getAll();
    public Entity get(Id id) throws NotFoundException;
    public void add(Dto dtoEntity);
    public void delete(Id id) throws NotFoundException;
    public void update(Id id, Dto dtoEntity) throws NotFoundException;
}
