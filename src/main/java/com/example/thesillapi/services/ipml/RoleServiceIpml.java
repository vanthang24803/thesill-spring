package com.example.thesillapi.services.ipml;

import com.example.thesillapi.dtos.message.Response;
import com.example.thesillapi.entities.Role;
import com.example.thesillapi.enums.RoleEnum;
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
    public Response<List<Role>> createSeedRole() {

        List<String> roles = Arrays.asList(
                RoleEnum.ADMIN.name(),
                RoleEnum.MANAGER.name(),
                RoleEnum.CUSTOMER.name()
        );

        List<Role> result = new ArrayList<>();

        for (String roleName : roles) {
            Role role = this.save(roleName).getMessage();
            result.add(role);
        }

        return new Response<>(result);
    }

    @Override
    public Response<Role> save(String name) {

        Role role = new Role();

        role.setName(RoleEnum.valueOf(name));

        roleRepository.save(role);

        return new Response<>(role);
    }
}
