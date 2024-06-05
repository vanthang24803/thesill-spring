package com.example.api.services;

import com.example.api.domain.dtos.message.Response;
import com.example.api.domain.entities.RoleEntity;
import com.example.api.domain.enums.RoleEnum;

public interface RoleService {

    void createSeedRole();

    Response<RoleEntity> save(RoleEnum role);
}
