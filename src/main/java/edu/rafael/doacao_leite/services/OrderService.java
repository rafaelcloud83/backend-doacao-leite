package edu.rafael.doacao_leite.services;

import edu.rafael.doacao_leite.controllers.dtos.OrderDto;
import edu.rafael.doacao_leite.entities.Order;
import edu.rafael.doacao_leite.entities.enums.OrderStatus;
import edu.rafael.doacao_leite.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<OrderDto> getAll() {
        return orderRepository
                .findAll()
                .stream()
                .map(order -> new OrderDto(order))
                .toList();
    }

    //recebedor - id do recebedor
    //buscar todos pedidos do recebedor não filtrando por status
    public List<OrderDto> getByReceptorId(Long receptorId) {
        return orderRepository
                .findAllByReceiverId(receptorId)
                .stream()
                .map(order -> new OrderDto(order))
                .toList();
    }

    //recebedor
    //criar um pedido para este recebedor
    public OrderDto create(OrderDto request) {
        Order order = new Order(request);
        order.setStatus(OrderStatus.AGUARDANDO);
        order = orderRepository.save(order);
        return new OrderDto(order);
    }

    //todos - id do pedido
    //buscar um pedido pelo id para qualquer recebedor ou doador
    public OrderDto getById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não existe."));
        return new OrderDto(order);
    }

    //doador - status = AGUARDANDO
    //buscar todos os pedidos que tem status Aguardando
    public List<OrderDto> getByStatus(OrderStatus status) {
        return orderRepository
                .findByStatus(status)
                .stream()
                .map(order -> new OrderDto(order))
                .toList();
    }

    //doador - id do doador
    //buscar todos os pedidos do doador filtrando por status Doado e Concluido
    public List<OrderDto> getByDoadorId(Long doadorId) {
        return orderRepository
                .findAllByDonorId(doadorId)
                .stream()
                .map(order -> new OrderDto(order))
                .toList();
    }

    public void deleteById(Long id) {
        OrderDto dto = getById(id);
        orderRepository.deleteById(dto.id());
    }

    public OrderDto update(OrderDto request) {
        OrderDto dto = getById(request.id());
        Order updateOrder = new Order(request);
        updateOrder.setCreatedAt(dto.createdAt()); //mantem a data de criação do pedido que está no banco

        updateOrder = orderRepository.save(updateOrder);
        return new OrderDto(updateOrder);
    }
}
