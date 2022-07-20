package uz.pdp.appwarehouse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.domain.Role;
import uz.pdp.appwarehouse.domain.Users;
import uz.pdp.appwarehouse.domain.Warehouse;
import uz.pdp.appwarehouse.payload.ApiResponse;
import uz.pdp.appwarehouse.payload.LoginDto;
import uz.pdp.appwarehouse.payload.RegisterDto;
import uz.pdp.appwarehouse.repository.RoleRepository;
import uz.pdp.appwarehouse.repository.UserRepository;
import uz.pdp.appwarehouse.repository.WarehouseRepository;
import uz.pdp.appwarehouse.security.JwtProvider;
import uz.pdp.appwarehouse.service.AuthService;

import java.util.*;

@Service
public class AuthServiceImpl implements AuthService, UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;
    @Override
    public ApiResponse register(RegisterDto dto) {
        if (userRepository.existsByPhoneNumber(dto.getPhoneNumber()))
            return new ApiResponse("Bunday foydalanuvchi bor", false);
        Optional<Role> optionalRole = roleRepository.findById(dto.getRoleId());
        if (!optionalRole.isPresent())
            return new ApiResponse("Role topilmadi", false);
        Role role = optionalRole.get();
        Set<Warehouse> listWarehouse = new HashSet<>();
        for (Long warehouses : dto.getWarehouses()) {
            Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(warehouses);
            if (!optionalWarehouse.isPresent())
                return new ApiResponse("Warehouse topilmadi", false);
            Warehouse warehouse = optionalWarehouse.get();
            listWarehouse.add(warehouse);
        }
        Users users = new Users(
                dto.getFirstName(),
                dto.getLastName(),
                dto.getPhoneNumber(),
                passwordEncoder.encode(dto.getPassword()),
                listWarehouse,
                role
        );
        userRepository.save(users);
        return new ApiResponse("Ishchi saqlandi", true);
    }

    @Override
    public ApiResponse login(LoginDto dto) {
        try {
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.getPhoneNumber(), dto.getPassword())
            );
           Users users = (Users) authenticate.getPrincipal();
            String token = jwtProvider.generateToken(users.getPhoneNumber(), users.getRole());
            return new ApiResponse("Token", true, token);
        }catch (BadCredentialsException badCredentialsException){
            return new ApiResponse("Login yoki parol hato", false);
        }

    }

    @Override
    public ApiResponse completedUser(Long id) {
        Optional<Users> optionalUsers = userRepository.findById(id);
        if (!optionalUsers.isPresent())
            return new ApiResponse("User topilmadi", false);
        Users users = optionalUsers.get();
        if (users.isEnabled())
            users.setEnabled(false);
        users.setEnabled(true);
        return new ApiResponse("Ozgartildi", true);
    }

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        return userRepository.findByPhoneNumber(phoneNumber).orElseThrow(() -> new UsernameNotFoundException("phoneNumber"));
    }
}
