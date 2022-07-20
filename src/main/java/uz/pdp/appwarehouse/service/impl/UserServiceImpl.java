package uz.pdp.appwarehouse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.domain.Users;
import uz.pdp.appwarehouse.payload.ApiResponse;
import uz.pdp.appwarehouse.payload.MemberDto;
import uz.pdp.appwarehouse.repository.UserRepository;
import uz.pdp.appwarehouse.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public ApiResponse completedUser(Long id) {
        Optional<Users> optionalUsers = userRepository.findById(id);
        if (!optionalUsers.isPresent())
            return new ApiResponse("User topilmadi", false);
        Users users = optionalUsers.get();
        if (users.isEnabled()) {
            users.setEnabled(false);
        }else {
            users.setEnabled(true);
        }
        userRepository.save(users);
        return new ApiResponse("Ozgartildi", true);
    }

    @Override
    public List<MemberDto> getMemberAll() {
        List<Users> usersList = userRepository.findAll();
        return mapMember(usersList);
    }

    @Override
    public MemberDto getOneMember(Long id) {
        Optional<Users> optionalUsers = userRepository.findById(id);
        if (!optionalUsers.isPresent())
            return new MemberDto();
        Users users = optionalUsers.get();
        MemberDto memberDto  = new MemberDto(
                users.getFirstName(),
                users.getLastName(),
                users.getPhoneNumber(),
                users.getWarehouse(),
                users.getRole().getName()
        );
        return memberDto;

    }

    public List<MemberDto> mapMember(List<Users> listUser){
        List<MemberDto> memberList = new ArrayList<>();
        for (Users users : listUser) {
            MemberDto memberDto  = new MemberDto(
                    users.getFirstName(),
                    users.getLastName(),
                    users.getPhoneNumber(),
                    users.getWarehouse(),
                    users.getRole().getName()
            );
            memberList.add(memberDto);
        }
        return memberList;
    }
}
