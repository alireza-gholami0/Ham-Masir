package com.example.foodapi.repository.specification;

import com.example.foodapi.domain.Order;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class OrderDao {
    private static EntityManager entityManager;
    @Autowired
    public OrderDao(EntityManager entityManager) {
        OrderDao.entityManager = entityManager;
    }

    public static double totalPrice(long id) {
        EntityGraph<?> entityGraph = entityManager.getEntityGraph("order-entity-graph-with-food-price");

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Double> query = cb.createQuery(Double.class);
        Root<Order> root = query.from(Order.class);
        entityManager.createEntityGraph(Order.class);

        query.select(cb.sum(cb.prod(
                root.get("orderFoods").get("quantity").as(Double.class),
                root.get("orderFoods").get("food").get("price")
        )));
        query.where(cb.equal(root.get("id"), id));

        TypedQuery<Double> typedQuery = entityManager.createQuery(query);
        typedQuery.setHint("javax.persistence.loadgraph", entityGraph);
        List<Double> result = typedQuery.getResultList();
        if (result.isEmpty()) {
            return 0.0;
        } else {
            return result.get(0);
        }
    }

}