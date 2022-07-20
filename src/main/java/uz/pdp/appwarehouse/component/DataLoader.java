package uz.pdp.appwarehouse.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import sun.security.util.Password;
import uz.pdp.appwarehouse.constans.AppConstans;
import uz.pdp.appwarehouse.domain.Role;
import uz.pdp.appwarehouse.domain.Users;
import uz.pdp.appwarehouse.domain.enums.Permission;
import uz.pdp.appwarehouse.repository.RoleRepository;
import uz.pdp.appwarehouse.repository.UserRepository;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Value("${spring.sql.init.mode}")

    private String initialMode;
    @Override
    public void run(String... args) throws Exception {
        if (initialMode.equals("always")){
            Permission[] permissions = Permission.values();
            Role admin = roleRepository.save(new Role(
                    AppConstans.ADMIN,
                    Arrays.asList(permissions),
                    "Yaxshi bola"
            ));

            userRepository.save(new Users(
                    "Anvarbek",
                    "Turdaliyev",
                    "+998975027300",
                    passwordEncoder.encode("123456789"),
                    null,
                    admin
            ));
        }
    }
}
