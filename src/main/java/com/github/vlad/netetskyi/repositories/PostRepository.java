package com.github.vlad.netetskyi.repositories;

import com.github.vlad.netetskyi.repositories.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
}
