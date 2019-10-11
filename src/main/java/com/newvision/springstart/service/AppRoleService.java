package com.newvision.springstart.service;

import com.newvision.springstart.entity.AppRole;

public interface AppRoleService {
    public AppRole save(AppRole role);
    public AppRole findRoleByName(String name);
}
