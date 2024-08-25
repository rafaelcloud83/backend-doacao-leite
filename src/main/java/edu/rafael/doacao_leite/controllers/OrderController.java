package edu.rafael.doacao_leite.controllers;

import edu.rafael.doacao_leite.entities.Order;
import edu.rafael.doacao_leite.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping
    public ResponseEntity<List<Order>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(orderRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> findById(@PathVariable("id") Long id) {
        System.out.println("request -> "+id);
        return ResponseEntity.status(HttpStatus.OK).
                body(orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found")));
    }

    @PostMapping("/create")
    public ResponseEntity<Order> create(@RequestBody Order order) {
        System.out.println("request -> "+order);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderRepository.save(order));
    }

    @PutMapping("/update")
    public ResponseEntity<Order> update(@RequestBody Order order) {
        //System.out.println("request -> "+order);
        this.findById(order.getId());
        //orderRepository.save(order);
        //System.out.println("request salvo -> "+order);
        return ResponseEntity.status(HttpStatus.OK).body(orderRepository.save(order));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        System.out.println("request -> "+id);
        findById(id);
        orderRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
