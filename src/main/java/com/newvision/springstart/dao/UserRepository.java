package com.newvision.springstart.dao;

import com.newvision.springstart.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User,Integer> {
    @Query("select u from User u where u.userName = :userName")
    public User findByUserName(@Param("userName") String userName);
}
