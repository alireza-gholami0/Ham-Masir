package com.example.foodapi.domain;

import com.example.foodapi.domain.log.DatabaseLogger;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EntityListeners(DatabaseLogger.class)
@Table(name = "order_food")
public class OrderFood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food;
    @Column(name = "quantity")
    private int quantity;

    @Override
    public String toString() {
        return "OrderFood{" +
                "id=" + id +
                "quantity=" + quantity +
                ", order_id='='" + order.getId() + '\'' +
                ", food_id='" + food.getId() + '\'' +
                '}';
    }
}
