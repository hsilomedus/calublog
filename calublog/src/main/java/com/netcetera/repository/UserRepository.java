package com.netcetera.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.netcetera.model.User;

@RepositoryRestResource(path="users")
public interface UserRepository extends JpaRepository<User, Long> {
	
	public User findByName(@Param("name") String name);

}
