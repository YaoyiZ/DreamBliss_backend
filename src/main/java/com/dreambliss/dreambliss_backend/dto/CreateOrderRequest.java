package com.dreambliss.dreambliss_backend.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class CreateOrderRequest {

    private List<OrderItem> items;
    public List<OrderItem> getItems() { return items; }
    public void setItems(List<OrderItem> items) { this.items = items; }


    //OrderItem 静态内部类
    public static class OrderItem {
        @NotBlank(message = "商品名称不能为空")
        private String product;

        @Min(value = 1, message = "数量至少要 1 件")
        private int quantity;

        @DecimalMin(value = "0.0", inclusive = true, message = "价格不能为负")
        private double price;

        public OrderItem() {
        }

        public OrderItem(String product, int quantity, double price) {
            this.product = product;
            this.quantity = quantity;
            this.price = price;
        }


        public String getProduct() {
            return product;
        }

        public void setProduct(String product) {
            this.product = product;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

    }
}
