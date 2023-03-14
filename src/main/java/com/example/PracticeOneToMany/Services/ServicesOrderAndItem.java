package com.example.PracticeOneToMany.Services;

import com.example.PracticeOneToMany.Models.Item;
import com.example.PracticeOneToMany.Models.Order;
import com.example.PracticeOneToMany.Repository.ItemRepository;
import com.example.PracticeOneToMany.Repository.OrderRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ServicesOrderAndItem {
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public ServicesOrderAndItem(OrderRepository orderRepository, ItemRepository itemRepository) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
    }

    public Item addItemToOrder(UUID orderId, Item item) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + orderId));
        item.setOrder(order);
        return itemRepository.save(item);
    }

    public List<Item> getOrderItems(UUID orderId) {
        return itemRepository.findByOrderId(orderId);
    }


    // Order methods
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(UUID id) {
        return orderRepository.findById(id);
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order updateOrder(UUID id, Order updatedOrder) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            updatedOrder.setId(id);
            return orderRepository.save(updatedOrder);
        }
        return null;
    }

    public void deleteOrder(UUID id) {
        orderRepository.deleteById(id);
    }

    // Item methods
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Optional<Item> getItemById(UUID id) {
        return itemRepository.findById(id);
    }

    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    public Item updateItem(UUID id, Item updatedItem) {
        Optional<Item> optionalItem = itemRepository.findById(id);
        if (optionalItem.isPresent()) {
            updatedItem.setId(id);
            return itemRepository.save(updatedItem);
        }
        return null;
    }

    public void deleteItem(UUID id) {
        itemRepository.deleteById(id);
    }
}

