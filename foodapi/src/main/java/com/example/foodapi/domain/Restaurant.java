package com.example.foodapi.domain;

import com.example.foodapi.domain.log.DatabaseLogger;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import java.util.List;

@Entity
@RequiredArgsConstructor
@Setter
@Getter
@Builder
@AllArgsConstructor
@EntityListeners(DatabaseLogger.class)
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "phonenumber")
    private String phoneNumber;
    @Column(name = "city")
    private String city;
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Food> foods;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Order> restaurantOrders;

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                "name=" + name +
                ", address='='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", city='" + city + '\'' +
                ", owner_id='" + owner.getId() + '\'' +
                '}';
    }
}
