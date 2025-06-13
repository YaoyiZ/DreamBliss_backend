package com.dreambliss.dreambliss_backend.service;

import com.dreambliss.dreambliss_backend.dto.CreateOrderRequest;
import com.dreambliss.dreambliss_backend.dto.OrderResponse;
import com.dreambliss.dreambliss_backend.entity.OrderEntity;
import com.dreambliss.dreambliss_backend.entity.OrderItemEntity;
import com.dreambliss.dreambliss_backend.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository repo;

    public OrderService(OrderRepository repo) {
        this.repo = repo;
    }

    // 创建订单
    public OrderResponse create(CreateOrderRequest req) {
        // 1. 新建订单主表实体
        OrderEntity order = new OrderEntity();

        // 2. 遍历 req.getItems()，生成订单项实体
        List<OrderItemEntity> items = req.getItems().stream()
                .map(item -> {
                    OrderItemEntity entity = new OrderItemEntity();
                    entity.setProduct(item.getProduct());
                    entity.setQuantity(item.getQuantity());
                    entity.setPrice(item.getPrice());
                    entity.setOrder(order); // 建立和订单主表的关系
                    return entity;
                })
                .collect(Collectors.toList());

        // 3. 把订单项集合设置到订单主表
        order.setItems(items);

        // 4. 保存整个订单（主表+明细项都会被保存）
        OrderEntity saved = repo.save(order);

        // 5. 转为 OrderResponse 返回
        return saved.toResponse();
    }

    // 列出所有订单
    public List<OrderResponse> listAll() {
        return repo.findAll()
                .stream()
                .map(OrderEntity::toResponse)
                .toList();
    }

    // 查询单个订单
    public OrderResponse getOne(Long id) {
        return repo.findById(id)
                .map(OrderEntity::toResponse)
                .orElseThrow(() -> new RuntimeException("订单不存在: " + id));
    }
}
