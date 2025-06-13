package com.dreambliss.dreambliss_backend.controller;

import com.dreambliss.dreambliss_backend.dto.CreateOrderRequest;
import com.dreambliss.dreambliss_backend.dto.OrderResponse;
import com.dreambliss.dreambliss_backend.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService svc;

    public OrderController(OrderService svc) {
        this.svc = svc;
    }

    /** 创建订单 */
    @PostMapping
    public OrderResponse create(@Valid @RequestBody CreateOrderRequest req) {
        return svc.create(req);
    }

    /** 列出所有订单 */
    @GetMapping
    public List<OrderResponse> listAll() {
        return svc.listAll();
    }

    /** 查询单个订单 */
    @GetMapping("/{id}")
    public OrderResponse getOne(@PathVariable Long id) {
        return svc.getOne(id);
    }
}
