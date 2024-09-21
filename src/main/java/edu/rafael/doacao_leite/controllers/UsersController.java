package edu.rafael.doacao_leite.controllers;

import edu.rafael.doacao_leite.controllers.dtos.UserDto;
import edu.rafael.doacao_leite.controllers.dtos.UserResponseDto;
import edu.rafael.doacao_leite.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<UserResponseDto> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(usersService.getAll());
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        Long countAll = usersService.countAll();
        return ResponseEntity.status(HttpStatus.OK).body(countAll-1L);
    }

    @GetMapping("/count/{role}")
    public ResponseEntity<Long> countByStatus(@PathVariable("role") String role) {
        return ResponseEntity.status(HttpStatus.OK).body(usersService.countByRole(role));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable("id") Long id) {
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
