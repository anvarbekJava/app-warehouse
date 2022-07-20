package uz.pdp.appwarehouse.service;

import uz.pdp.appwarehouse.payload.ApiResponse;
import uz.pdp.appwarehouse.payload.RoleDto;

public interface RoleService {
    ApiResponse add(RoleDto dto);
}
