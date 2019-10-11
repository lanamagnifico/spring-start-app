package com.newvision.springstart.service;

import com.newvision.springstart.entity.AppUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AppUserService extends UserDetailsService {
    public AppUser save(AppUser user);
    public AppUser findByUserName(String userName);
}
