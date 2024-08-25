package edu.rafael.doacao_leite.entities;

import edu.rafael.doacao_leite.controllers.dtos.UserDto;
import edu.rafael.doacao_leite.entities.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.Instant;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "TB_USERS")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Configuration
public class Users implements UserDetails, Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(nullable = false)
    private Boolean active;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE", nullable = false)
    private Instant createdAt;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant updatedAt;

    //@OneToOne( mappedBy = "receiver") //referencia ao atributo da outra clsse
    private Order receiver;
    //@ManyToOne( mappedBy = "donor") //referencia ao atributo da outra clsse
    private Order donor;

    public Users(String name, String email, String password, String phone,
                 String address,Role role, Boolean active,
                 Order receiver, Order donor) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.active = active;
        this.role = role;
        this.receiver = receiver;
        this.donor = donor;
    }

    public Users(String name, String email, String password, String phone,
                 String address,Role role, Boolean active) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.active = active;
        this.role = role;
    }

//    public Users(String name, String email, String password, Role role) {
//        this.name = name;
//        this.email = email;
//        this.password = password;
//        this.role = role;
//    }

    public Users(UserDto dto) {
        this.id = dto.id();
        this.name = dto.name();
        this.email = dto.email();
        this.password = dto.password();
        this.phone = dto.phone();
        this.address = dto.address();
        this.active = dto.active();
        this.role = dto.role();
        this.receiver = dto.receiver();
        this.donor = dto.donor();
        this.createdAt = dto.createdAt();
        this.updatedAt = dto.updatedAt();
    }

    @PrePersist
    public void preCreate(){
        createdAt = Instant.now();
    }

    @PreUpdate
    public void preUpdate(){
        updatedAt = Instant.now();
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", active=" + active +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", receiver=" + receiver +
                ", donor=" + donor +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (role == Role.ADMIN) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_DOADOR"),
                    new SimpleGrantedAuthority("ROLE_RECEBEDOR")
            );
        } else if (role == Role.DOADOR) {
            return List.of(new SimpleGrantedAuthority("ROLE_DOADOR"));
        } else {
            return List.of(new SimpleGrantedAuthority("ROLE_RECEBEDOR"));
        }
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
