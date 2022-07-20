package uz.pdp.appwarehouse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.domain.Role;
import uz.pdp.appwarehouse.domain.enums.Permission;
import uz.pdp.appwarehouse.payload.ApiResponse;
import uz.pdp.appwarehouse.payload.RoleDto;
import uz.pdp.appwarehouse.repository.RoleRepository;
import uz.pdp.appwarehouse.service.RoleService;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public ApiResponse add(RoleDto dto) {
        if(roleRepository.existsByName(dto.getName()))
            return new ApiResponse("Role name mavjud boshqa kiririting", false);
        Role role = new Role();
        role.setName(dto.getName());
        role.setDescription(dto.getDescription());
        role.setPermissions(dto.getPermissions());
        roleRepository.save(role);
        return new ApiResponse("Saqlandi", true);
    }
}
