package com.example.tacocloud.repositories.orders;

import com.example.tacocloud.Order;

public interface OrderRepository {
    Order save(Order order);
}
