package com.dreambliss.dreambliss_backend.repository;

import com.dreambliss.dreambliss_backend.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    // 标准 JPA repository，无需写其它方法
}
