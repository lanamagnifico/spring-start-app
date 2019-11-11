package com.newvision.springstart.service;

import com.newvision.springstart.dao.RoleRepository;
import com.newvision.springstart.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findRoleByName(String title) {
        return roleRepository.findRoleByName(title);
    }
}
