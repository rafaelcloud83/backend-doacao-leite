package edu.rafael.doacao_leite.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.rafael.doacao_leite.controllers.dtos.UserDto;
import edu.rafael.doacao_leite.entities.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
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
    private String password;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    //@OneToOne( mappedBy = "receiver") //referencia ao atributo da outra clsse
    private Order receiver;
    //@ManyToOne( mappedBy = "donor") //referencia ao atributo da outra clsse
    private Order donor;

    public Users(String name, String email, String password, Role role, Order receiver, Order donor) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.receiver = receiver;
        this.donor = donor;
    }

    public Users(String name, String email, String password, Role role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Users(UserDto dto) {
        this.id = dto.id();
        this.name = dto.name();
        this.email = dto.email();
        this.password = dto.password();
        this.role = dto.role();
        this.receiver = dto.receiver();
        this.donor = dto.donor();
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
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
