package com.newvision.springstart.service;

import com.newvision.springstart.dao.AppRoleRepository;
import com.newvision.springstart.dao.AppUserRepository;
import com.newvision.springstart.entity.AppRole;
import com.newvision.springstart.entity.AppUser;
import com.newvision.springstart.entity.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class AppUserServiceImpl implements AppUserService {
    @Autowired
    private AppUserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AppRoleService roleService;

    private Logger logger = Logger.getLogger(getClass().getName());

    @Override
    @Transactional
    public AppUser save(AppUser user) {
        AppRole roleUser = roleService.findRoleByName(Roles.ROLE_USER.toString());
        AppUser dbUser = saveUser(user,roleUser);
        logger.info("User created " + dbUser.toString());
        return dbUser;
    }

    private AppUser saveUser(AppUser user, AppRole role){
        user.setUserPass(passwordEncoder.encode(user.getUserPass()));
        user.setRoles(Arrays.asList(role));
        return userRepository.save(user);
    }

    @PostConstruct
    public void createFirstAdminIfNotExist() {
        AppUser dbAdmin = userRepository.findByUserName("admin");
        if (dbAdmin!=null){
            boolean isAdmin = dbAdmin.getRoles().contains(roleService.findRoleByName(Roles.ROLE_ADMIN.toString()));
            if (isAdmin) {
                return;
            }
        }
        AppUser admin = new AppUser();
        admin.setUserName("admin");
        admin.setUserPass("admin-password");
        dbAdmin = saveUser(admin,roleService.findRoleByName(Roles.ROLE_ADMIN.toString()));
        logger.info("Admin created " + dbAdmin.toString());
    }

    @Override
    @Transactional
    public AppUser findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        AppUser user = userRepository.findByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getUserPass(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<AppRole> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
