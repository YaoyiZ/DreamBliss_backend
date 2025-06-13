package com.dreambliss.dreambliss_backend.entity;

import com.dreambliss.dreambliss_backend.dto.OrderResponse;
import jakarta.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String product;
    private Integer quantity;
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id") // 外键关联主订单表
    private OrderEntity order;

    // Getter & Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getProduct() { return product; }
    public void setProduct(String product) { this.product = product; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public OrderEntity getOrder() { return order; }
    public void setOrder(OrderEntity order) { this.order = order; }

    //把明细项实体转为 DTO
    public OrderResponse.OrderItem toDto() {
        OrderResponse.OrderItem dto = new OrderResponse.OrderItem();
        dto.setProduct(this.product);
        dto.setQuantity(this.quantity);
        dto.setPrice(this.price);
        return dto;
    }
}
