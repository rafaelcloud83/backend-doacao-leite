package edu.rafael.doacao_leite.repositories;

import edu.rafael.doacao_leite.entities.Users;
import edu.rafael.doacao_leite.entities.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    UserDetails findByEmail(String email);

    @Query(value = "SELECT * FROM TB_USERS u WHERE u.email = :email", nativeQuery = true)
    Users getEmail(String email);

    Long countByRole(Role role);
}
