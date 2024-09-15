package edu.rafael.doacao_leite.services;

import edu.rafael.doacao_leite.controllers.dtos.OrderDto;
import edu.rafael.doacao_leite.controllers.dtos.OrderResponseDto;
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

    @Autowired
    private UsersService usersService;

    public OrderResponseDto getAll() {
        List<Order> allOrders = orderRepository.findAll();
        return new OrderResponseDto(
                allOrders
                        .stream()
                        .map(order -> new OrderDto(order))
                        .toList()
        );
//        return orderRepository
//                .findAll()
//                .stream()
//                .map(order -> new OrderDto(order))
//                .toList();
    }

    public Long countAll() {
        return orderRepository.count();
    }

    public Long countByStatus(String status) {
        return orderRepository.countByStatus(OrderStatus.valueOf(status));
    }


    //recebedor - id do recebedor
    //buscar todos pedidos do recebedor não filtrando por status, mostra os 3 status
    public OrderResponseDto getByReceiverId(Long receiverId) {
        List<Order> allByReceiverId = orderRepository.findAllByReceiverId(receiverId);
        return new OrderResponseDto(
                allByReceiverId
                        .stream()
                        .map(order -> new OrderDto(order))
                        .toList()
        );
    }

    //doador - id do doador
    //buscar todos os pedidos do doador não filtrando por status, mostra 2 status (DOADO, CONCLUIDO)
    public OrderResponseDto getByDonorId(Long donorId) {
        List<Order> allByDonorId = orderRepository.findAllByDonorId(donorId);
        return new OrderResponseDto(
                allByDonorId
                        .stream()
                        .map(order -> new OrderDto(order))
                        .toList()
        );
    }

    //doador - status = AGUARDANDO
    //buscar todos os pedidos que tem status Aguardando
    public OrderResponseDto getByStatus(OrderStatus status) {
        List<Order> allOrders = orderRepository.findByStatus(status);
        return new OrderResponseDto(
                allOrders
                        .stream()
                        .map(order -> new OrderDto(order))
                        .toList()
        );
    }

    //recebedor
    //criar um pedido para este recebedor
    public OrderDto create(OrderDto request) {
        Order order = new Order(request);
        order.setStatus(OrderStatus.AGUARDANDO);
        order.setDonor(usersService.getUserById(1L)); //seta um doador padrão ao criar o pedido
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
