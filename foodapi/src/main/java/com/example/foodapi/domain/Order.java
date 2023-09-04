package com.example.foodapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NamedEntityGraph(
        name = "order-entity-graph-with-food-price",
        attributeNodes = {
                @NamedAttributeNode(value = "id"),
                @NamedAttributeNode(value = "orderFoods", subgraph = "orderFood-subgraph"),
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "orderFood-subgraph",
                        attributeNodes = {
                                @NamedAttributeNode(value = "quantity"),
                                @NamedAttributeNode(value = "food", subgraph = "food-subgraph")
                        }
                ),
                @NamedSubgraph(
                        name = "food-subgraph",
                        attributeNodes = {
                                @NamedAttributeNode(value = "price")
                        }
                )
        }
)

@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@Table(name = "order_table")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @OneToMany(mappedBy = "order")
    private List<OrderFood> orderFoods = new ArrayList<>();
}

