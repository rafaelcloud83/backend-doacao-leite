package edu.rafael.doacao_leite.controllers;

import edu.rafael.doacao_leite.controllers.dtos.UserDto;
import edu.rafael.doacao_leite.entities.Users;
import edu.rafael.doacao_leite.entities.enums.Role;
import edu.rafael.doacao_leite.services.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UsersService usersService;

    @Override
    public void run(String... args) throws Exception {
        Users user1 = new Users("Nenhum", "nenhum@nenhum", "5684", "1", "nenhum", Role.DOADOR, true);
        Users user2 = new Users("ADMIN", "admin@admin", "56845684", "1", "admin", Role.ADMIN, true);

        long count = usersService.countAll();
        if (count == 0) {
            usersService.create(new UserDto(user1));
            usersService.create(new UserDto(user2));
        } else {
            log.info("Já existem " + count + " usuários na tabela.");
        }
    }
}