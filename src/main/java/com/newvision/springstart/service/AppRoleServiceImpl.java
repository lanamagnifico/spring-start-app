package com.newvision.springstart.service;

import com.newvision.springstart.dao.AppRoleRepository;
import com.newvision.springstart.entity.AppRole;
import com.newvision.springstart.entity.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Service
public class AppRoleServiceImpl implements AppRoleService{

    @Autowired
    private AppRoleRepository roleRepository;

    private AppRole roleAdmin;
    private AppRole roleUser;

    @Override
    @Transactional
    public AppRole save(AppRole role) {
        return roleRepository.save(role);
    }

    @Override
    @Transactional
    public AppRole findRoleByName(String name) {
        return roleRepository.findRoleByName(name);
    }

    @PostConstruct
    @Transactional
    private void createRolesIfNotExist() {
        String adminString = Roles.ROLE_ADMIN.toString();
        String userString = Roles.ROLE_USER.toString();

        roleAdmin = roleRepository.findRoleByName(adminString);
        if (roleAdmin==null){
            setRoleAdmin(roleRepository.save(new AppRole(adminString)));
        }
        roleUser = roleRepository.findRoleByName(userString);
        if (roleUser==null){
            setRoleUser(roleRepository.save(new AppRole(userString)));
        }
    }

    public AppRole getRoleAdmin() {
        return roleAdmin;
    }

    public void setRoleAdmin(AppRole roleAdmin) {
        this.roleAdmin = roleAdmin;
    }

    public AppRole getRoleUser() {
        return roleUser;
    }

    public void setRoleUser(AppRole roleUser) {
        this.roleUser = roleUser;
    }
}
