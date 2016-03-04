package com.netcetera.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.netcetera.model.BlogPost;

@RepositoryRestResource(path="blogposts")
public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {

}
