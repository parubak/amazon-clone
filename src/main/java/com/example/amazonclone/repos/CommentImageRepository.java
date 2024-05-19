package com.example.amazonclone.repos;

import com.example.amazonclone.models.CommentImage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentImageRepository extends CrudRepository<CommentImage, Long> {
}
