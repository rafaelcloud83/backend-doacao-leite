package edu.rafael.doacao_leite.services;

import edu.rafael.doacao_leite.controllers.dtos.UserDto;
import edu.rafael.doacao_leite.controllers.dtos.UserResponseDto;
import edu.rafael.doacao_leite.entities.Users;
import edu.rafael.doacao_leite.entities.enums.Role;
import edu.rafael.doacao_leite.repositories.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    public UserDto create(UserDto request) {
        if (usersRepository.getEmail(request.email()) != null) {
            throw new RuntimeException("Já existe usuário com este email.");
        }
        Users user = new Users(request);

        user.setPassword(new BCryptPasswordEncoder().encode(request.password()));
        user.setActive(true);

        user = usersRepository.save(user);
        log.info("Usuário criado: " + user);
        return new UserDto(user);
    }

    public UserDto update(UserDto request) {
        UserDto dto = getUserDtoById(request.id());
        Users updateUser = new Users(request);

        updateUser.setCreatedAt(dto.createdAt()); //mantem a data de criação do usuário que está no banco

        updateUser = usersRepository.save(updateUser);
        log.info("Usuário atualizado: " + updateUser);
        return new UserDto(updateUser);
    }

    public UserResponseDto getAll() {
        List<Users> allUsers = usersRepository.findAll();
        return new UserResponseDto(
                allUsers
                        .stream()
                        .map(user -> new UserDto(user))
                        .toList()
        );
    }

    public Long countAll() {
        return usersRepository.count();
    }

    public Long countByRole(String role) {
        return usersRepository.countByRole(Role.valueOf(role));
    }

    public UserResponseDto getById(Long id) {
        List<UserDto> usersById = new ArrayList<>();
        UserDto dto = getUserDtoById(id);
        usersById.add(dto);
        return new UserResponseDto(usersById);
    }

    public void deleteById(Long id) {
        UserDto dto = getUserDtoById(id);
        usersRepository.deleteById(dto.id());
    }

    public Users getUserById(Long id) {
        return usersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não existe."));
    }

    public UserDto getUserDtoById(Long id) {
        return new UserDto(getUserById(id));
    }
}
