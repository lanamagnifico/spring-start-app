package com.newvision.springstart.dao;

import com.newvision.springstart.entity.AppRole;
import com.newvision.springstart.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AppRoleRepository extends JpaRepository<AppRole,Integer> {
    @Query("select ar from AppRole ar where ar.name = :name")
    public AppRole findRoleByName(@Param("name") String name);
}
