package com.example.amazonclone.services;

import com.example.amazonclone.dto.DtoEntity;
import com.example.amazonclone.exceptions.NotFoundException;
import com.example.amazonclone.models.ModelEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public class DtoService<Dto extends DtoEntity<Entity>, Entity extends ModelEntity<Id>, Id> implements CrudService<Dto, Entity, Id>{
    private final CrudRepository<Entity, Id> repository;

    public DtoService(CrudRepository<Entity, Id> repository) {
        this.repository = repository;
    }

    public Iterable<Entity> getAll() {
        return repository.findAll();
    }

    public Entity get(Id id) throws NotFoundException {
        Optional<Entity> entity = repository.findById(id);

        if (entity.isEmpty())
            throw new NotFoundException("Entity was not found");

        return entity.get();
    }

    public void add(Dto dtoEntity) {
        repository.save(dtoEntity.buildEntity());
    }

    public void delete(Id id) throws NotFoundException {
        repository.deleteById(get(id).getId());
    }

    public void update(Id id, Dto dtoEntity) throws NotFoundException {
        delete(id);

        Entity entity = dtoEntity.buildEntity();
        entity.setId(id);
        repository.save(entity);
    }
}
