package com.example.thesillapi.services.ipml;

import com.example.thesillapi.domain.dtos.message.Response;
import com.example.thesillapi.domain.entities.RoleEntity;
import com.example.thesillapi.domain.enums.RoleEnum;
import com.example.thesillapi.exceptions.BadRequestException;
import com.example.thesillapi.repositories.RoleRepository;
import com.example.thesillapi.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceIpml implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Response<List<RoleEntity>> createSeedRole() {
        List<RoleEnum> roles = Arrays.asList(RoleEnum.ADMIN, RoleEnum.MANAGER, RoleEnum.CUSTOMER);
        List<RoleEntity> result = new ArrayList<>();

        for (RoleEnum role : roles) {
            RoleEntity roleEntity = save(role).getResult();
            result.add(roleEntity);
        }

        return new Response<>(HttpStatus.OK.value(), result);
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
