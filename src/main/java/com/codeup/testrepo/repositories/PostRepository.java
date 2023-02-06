package com.codeup.testrepo.repositories;

import com.codeup.testrepo.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

    Post findByTitle(String title);

    Post findByBody(String body);

}