
package com.pawan.DAO;

import com.pawan.model.Order;

import java.util.List;

public interface OrderDAO {
    List<Order> getAllOrders();
    Order getOrderById(int id);
    void addOrder(Order order);
    void updateOrder(Order order);
    void deleteOrder(int id);
    List<Order> getOrdersSortedByDate();
}
