package edu.rafael.doacao_leite.services;

import edu.rafael.doacao_leite.controllers.dtos.UserDto;
import edu.rafael.doacao_leite.entities.Users;
import edu.rafael.doacao_leite.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
        return new UserDto(user);
    }

    public UserDto update(UserDto request) {
        UserDto dto = getById(request.id());
        Users updateUser = new Users(request);

        updateUser.setPassword(new BCryptPasswordEncoder().encode(request.password()));
        updateUser.setCreatedAt(dto.createdAt()); //mantem a data de criação do usuário que está no banco

        updateUser = usersRepository.save(updateUser);
        return new UserDto(updateUser);
    }

    public List<UserDto> getAll() {
        return usersRepository
                .findAll()
                .stream()
                .map(user -> new UserDto(user))
                .toList();
    }

    public UserDto getById(Long id) {
        Users user = usersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não existe."));
        return new UserDto(user);
    }

    public void deleteById(Long id) {
        UserDto dto = getById(id);
        usersRepository.deleteById(dto.id());
    }
}
