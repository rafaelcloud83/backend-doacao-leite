package edu.rafael.doacao_leite.controllers;

import edu.rafael.doacao_leite.controllers.dtos.OrderDto;
import edu.rafael.doacao_leite.entities.enums.OrderStatus;
import edu.rafael.doacao_leite.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> findById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).
            body(orderService.getById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<OrderDto> create(@RequestBody OrderDto request) {
        System.out.println("request -> "+request);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.create(request));
    }

    @PutMapping("/update")
    public ResponseEntity<OrderDto> update(@RequestBody OrderDto request) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.update(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        orderService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/receiver/{receptorId}")
    public ResponseEntity<List<OrderDto>> getByReceptorId(@PathVariable("receptorId") Long receptorId) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getByReceptorId(receptorId));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<OrderDto>> getByStatus(@PathVariable("status") OrderStatus status) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getByStatus(status));
    }

    @GetMapping("/donor/{doadorId}")
    public ResponseEntity<List<OrderDto>> getByDoadorId(@PathVariable("doadorId") Long doadorId) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getByDoadorId(doadorId));
    }
}
