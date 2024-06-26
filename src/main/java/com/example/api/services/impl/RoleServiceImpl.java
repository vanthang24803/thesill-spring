package com.example.api.services.impl;

import com.example.api.common.helpers.Response;
import com.example.api.domain.entities.RoleEntity;
import com.example.api.domain.enums.RoleEnum;
import com.example.api.common.exceptions.BadRequestException;
import com.example.api.repositories.RoleRepository;
import com.example.api.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public void createSeedRole() {
        List<RoleEnum> roles = Arrays.asList(RoleEnum.ADMIN, RoleEnum.MANAGER, RoleEnum.CUSTOMER);

        for (RoleEnum role : roles) {
            if (roleRepository.findByName(role).isEmpty()) {
                this.save(role);
            }
        }

    }

    @Override
    public Response<RoleEntity> save(RoleEnum role) {
        Optional<RoleEntity> existingRoleOpt = roleRepository.findByName(role);
        if (existingRoleOpt.isPresent()) {
            throw new BadRequestException("Role already exists");
        }


        RoleEntity roleEntity = new RoleEntity();

        roleEntity.setName(role);

        roleRepository.save(roleEntity);

        return new Response<>(HttpStatus.OK.value(), roleEntity);
    }
}
