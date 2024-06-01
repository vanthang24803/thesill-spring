package com.example.thesillapi.services.ipml;

import com.example.thesillapi.domain.dtos.message.Response;
import com.example.thesillapi.domain.entities.RoleEntity;
import com.example.thesillapi.domain.enums.RoleEnum;
import com.example.thesillapi.repositories.RoleRepository;
import com.example.thesillapi.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceIpml implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Response<List<RoleEntity>> createSeedRole() {

        List<String> roles = Arrays.asList(
                RoleEnum.ADMIN.name(),
                RoleEnum.MANAGER.name(),
                RoleEnum.CUSTOMER.name()
        );

        List<RoleEntity> result = new ArrayList<>();

        for (String roleName : roles) {
            RoleEntity roleEntity = this.save(roleName).getMessage();
            result.add(roleEntity);
        }

        return new Response<>(result);
    }

    @Override
    public Response<RoleEntity> save(String name) {

        RoleEntity roleEntity = new RoleEntity();

        roleEntity.setName(RoleEnum.valueOf(name));

        roleRepository.save(roleEntity);

        return new Response<>(roleEntity);
    }
}
