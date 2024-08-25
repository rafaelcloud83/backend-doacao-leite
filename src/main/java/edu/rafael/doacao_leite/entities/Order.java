package edu.rafael.doacao_leite.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

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

    @ManyToOne //um recebedor pode receber muitas doações
    @JoinColumn(name = "receiver_id") //cria no banco
    private Users receiver;
    @ManyToOne //um doador pode fazer muitas doações
    @JoinColumn(name = "donor_id") //cria no banco
    private Users donor;

    public Order(String productName, Users receiver, Users donor) {
        this.productName = productName;
        this.receiver = receiver;
        this.donor = donor;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", receiver=" + receiver +
                ", donor=" + donor +
                '}';
    }
}
