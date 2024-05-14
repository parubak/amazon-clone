package com.example.amazonclone.repos;

import com.example.amazonclone.models.Color;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorRepository extends RefreshableRepository<Color, Long> {
}
