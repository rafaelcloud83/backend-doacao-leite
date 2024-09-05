package edu.rafael.doacao_leite.repositories;

import edu.rafael.doacao_leite.entities.Order;
import edu.rafael.doacao_leite.entities.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByStatus(OrderStatus status);

    List<Order> findAllByDonorId(Long doadorId);

    List<Order> findAllByReceiverId(Long receptorId);
}
