package com.example.thesillapi.services;

import com.example.thesillapi.domain.dtos.message.Response;
import com.example.thesillapi.domain.entities.RoleEntity;

import java.util.List;

public interface RoleService {

    Response<List<RoleEntity>> createSeedRole();

    Response<?> save(String name);
}
