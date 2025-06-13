package com.dreambliss.dreambliss_backend.entity;

import com.dreambliss.dreambliss_backend.dto.OrderResponse;
import jakarta.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 订单id

    // 可以加订单创建时间、客户名等

    // 订单项，一对多关联
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemEntity> items;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public List<OrderItemEntity> getItems() { return items; }
    public void setItems(List<OrderItemEntity> items) { this.items = items; }

    //把主订单和所有明细项转换为 DTO
    public OrderResponse toResponse() {
        OrderResponse resp = new OrderResponse();
        resp.setId(this.id);
        // 把所有订单项转换成 DTO 列表
        resp.setItems(this.items == null ? null : this.items.stream().map(OrderItemEntity::toDto).collect(Collectors.toList()));
        return resp;
    }
}
