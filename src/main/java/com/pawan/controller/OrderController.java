
package com.pawan.controller;

import com.pawan.DAO.OrderDAO;
import com.pawan.model.Order;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderDAO orderDAO;

    public OrderController(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderDAO.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable int id) {
        return orderDAO.getOrderById(id);
    }

    @PostMapping
    public void addOrder(@RequestBody Order order) {
        orderDAO.addOrder(order);
    }

    @PutMapping("/{id}")
    public void updateOrder(@PathVariable int id, @RequestBody Order order) {
        order.setId(id);
        orderDAO.updateOrder(order);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable int id) {
        orderDAO.deleteOrder(id);
    }

    @GetMapping("/sorted-by-date")
    public List<Order> getOrdersSortedByDate() {
        return orderDAO.getOrdersSortedByDate();
    }
}
