package com.example.amazonclone.repos;

import com.example.amazonclone.models.Subgroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubgroupRepository extends CrudRepository<Subgroup, Long> {
}
