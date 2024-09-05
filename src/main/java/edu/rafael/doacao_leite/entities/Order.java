package edu.rafael.doacao_leite.entities;

import edu.rafael.doacao_leite.controllers.dtos.OrderDto;
import edu.rafael.doacao_leite.entities.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "TB_ORDER")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Configuration
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String productName;
    @Column(nullable = false)
    private String estimatedPrice;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE", nullable = false)
    private Instant createdAt;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant updatedAt;

    @ManyToOne //um recebedor pode receber muitas doações
    @JoinColumn(name = "receiver_id") //cria no banco
    private Users receiver;
    @ManyToOne //um doador pode fazer muitas doações
    @JoinColumn(name = "donor_id") //cria no banco
    private Users donor;

//    public Order(String productName, Users receiver, Users donor) {
//        this.productName = productName;
//        this.receiver = receiver;
//        this.donor = donor;
//    }

    public Order(String productName, String estimatedPrice, OrderStatus status, Instant createdAt, Instant updatedAt, Users receiver, Users donor) {
        this.productName = productName;
        this.estimatedPrice = estimatedPrice;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.receiver = receiver;
        this.donor = donor;
    }

    public Order(String productName, String estimatedPrice, OrderStatus status, Users receiver, Users donor) {
        this.productName = productName;
        this.estimatedPrice = estimatedPrice;
        this.status = status;
        this.receiver = receiver;
        this.donor = donor;
    }

    public Order(OrderDto dto) {
        this.id = dto.id();
        this.productName = dto.productName();
        this.estimatedPrice = dto.estimatedPrice();
        this.status = dto.status();
        this.createdAt = dto.createdAt();
        this.updatedAt = dto.updatedAt();
        this.receiver = dto.receiver();
        this.donor = dto.donor();
    }

    @PrePersist
    public void preCreate(){
        createdAt = Instant.now();
    }

    @PreUpdate
    public void preUpdate(){
        updatedAt = Instant.now();
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", estimatedPrice=" + estimatedPrice +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", receiver=" + receiver +
                ", donor=" + donor +
                '}';
    }
}
