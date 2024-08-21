package edu.rafael.doacao_leite.repositories;

import edu.rafael.doacao_leite.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    UserDetails findByEmail(String email);

    @Query(value = "SELECT * FROM Users u WHERE u.email = :email", nativeQuery = true)
    Users getEmail(String email);

}
