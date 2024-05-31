package com.example.thesillapi.services;

import com.example.thesillapi.dtos.message.Response;
import com.example.thesillapi.entities.Role;

import java.util.List;

public interface RoleService {

    Response<List<Role>> createSeedRole();

    Response<?> save(String name);
}
