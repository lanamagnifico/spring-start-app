package com.newvision.springstart.dao;

import com.newvision.springstart.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource( path="/users" )
public interface UserRepository extends JpaRepository<User,Integer> {
    @Query("select au from User au where au.userName = :userName")
    public User findByUserName(@Param("userName") String userName);
}
