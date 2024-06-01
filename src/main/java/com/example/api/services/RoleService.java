package com.example.api.services;

import com.example.api.domain.dtos.message.Response;
import com.example.api.domain.entities.RoleEntity;
import com.example.api.domain.enums.RoleEnum;

import java.util.List;

public interface RoleService {

    Response<List<RoleEntity>> createSeedRole();

    Response<?> save(RoleEnum role);
}
