package com.newvision.springstart.service;

import com.newvision.springstart.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    public User save(User theUser);
    public User findByUserName(String userName);
    public void delete(int theId);
}
