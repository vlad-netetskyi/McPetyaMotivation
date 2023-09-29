package org.example.repositories;

import org.example.models.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
}
