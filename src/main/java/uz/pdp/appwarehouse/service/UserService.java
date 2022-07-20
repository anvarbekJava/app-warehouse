package uz.pdp.appwarehouse.service;

import uz.pdp.appwarehouse.payload.ApiResponse;
import uz.pdp.appwarehouse.payload.MemberDto;

import java.util.List;

public interface UserService {
    ApiResponse completedUser(Long id);

    List<MemberDto> getMemberAll();

    MemberDto getOneMember(Long id);
}
