package csd230.lab1.repositories;

import csd230.lab1.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderEntityRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findByOrderDate(LocalDateTime orderDate);
}