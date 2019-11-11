package com.newvision.springstart.dao;

import com.newvision.springstart.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    @Query("select ar from Role ar where ar.title = :title")
    public Role findRoleByName(@Param("title") String title);
}
