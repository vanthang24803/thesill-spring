package com.example.thesillapi.services;

import com.example.thesillapi.domain.dtos.message.Response;
import com.example.thesillapi.domain.entities.RoleEntity;
import com.example.thesillapi.domain.enums.RoleEnum;

import java.util.List;

public interface RoleService {

    Response<List<RoleEntity>> createSeedRole();

    Response<?> save(RoleEnum role);
}
