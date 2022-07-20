package uz.pdp.appwarehouse.domain;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.pdp.appwarehouse.domain.enums.Permission;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class Users implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Column(nullable = false)
    private String password;

    @ManyToMany
    private Set<Warehouse> warehouse;

    private boolean enabled = true;

    @ManyToOne(optional = false)
    private Role role;

    private boolean accountNonExpired = true;

    private boolean accountNonLocked = true;

    private boolean credentialsNonExpired = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Permission> permissionList = this.role.getPermissions();
        Set<GrantedAuthority> grantedAuthoritySet = new HashSet<>();
        for (Permission permission : permissionList) {
            grantedAuthoritySet.add(new SimpleGrantedAuthority(permission.name()));
        }
        return grantedAuthoritySet;
    }

    @Override
    public String getUsername() {
        return this.phoneNumber;
    }

    public Users(String firstName, String lastName, String phoneNumber, String password, Set<Warehouse> warehouse, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.warehouse = warehouse;
        this.role = role;
    }
}
