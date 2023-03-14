package com.example.PracticeOneToMany.Controllers;

import com.example.PracticeOneToMany.Models.Item;
import com.example.PracticeOneToMany.Models.Order;
import com.example.PracticeOneToMany.Services.ServicesOrderAndItem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ControllerOANDI {

    private final ServicesOrderAndItem servicesOrderAndItem;

    public ControllerOANDI(ServicesOrderAndItem servicesOrderAndItem) {
        this.servicesOrderAndItem = servicesOrderAndItem;
    }
    @PostMapping("/{orderId}/items")
    public ResponseEntity<Item> addItemToOrder(@PathVariable UUID orderId, @RequestBody Item item) {
        Item savedItem = servicesOrderAndItem.addItemToOrder(orderId, item);
        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
    }

    @GetMapping("/{orderId}/items")
    public ResponseEntity<List<Item>> getOrderItems(@PathVariable UUID orderId) {
        List<Item> items = servicesOrderAndItem.getOrderItems(orderId);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/getAllOrders")
    public ResponseEntity<List<Order>> getAllOrders(){
        List<Order> orders = servicesOrderAndItem.getAllOrders();
        return new ResponseEntity<>(orders,HttpStatus.OK);
    }
    @GetMapping("/getAllItems")
    public ResponseEntity<List<Item>> getAllItems(){
        List<Item> items = servicesOrderAndItem.getAllItems();
        return new ResponseEntity<>(items,HttpStatus.OK);
    }
}
