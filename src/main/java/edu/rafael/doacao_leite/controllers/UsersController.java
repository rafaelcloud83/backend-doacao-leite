package edu.rafael.doacao_leite.controllers;

import edu.rafael.doacao_leite.controllers.dtos.UserDto;
import edu.rafael.doacao_leite.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @PostMapping("/create")
    public ResponseEntity<UserDto> create(@RequestBody UserDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usersService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(usersService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(usersService.getById(id));
    }

    @PutMapping("/update")
    public ResponseEntity<UserDto> update(@RequestBody UserDto request) {
        return ResponseEntity.status(HttpStatus.OK).body(usersService.update(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        usersService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
