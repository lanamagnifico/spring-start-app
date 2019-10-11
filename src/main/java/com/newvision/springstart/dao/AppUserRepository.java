package com.newvision.springstart.dao;

import com.newvision.springstart.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource( path="/users" )
public interface AppUserRepository extends JpaRepository<AppUser,Integer> {
    @Query("select au from AppUser au where au.userName = :userName")
    public AppUser findByUserName(@Param("userName") String userName);
}
