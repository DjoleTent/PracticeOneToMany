package com.example.PracticeOneToMany.Repository;

import com.example.PracticeOneToMany.Models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface ItemRepository extends JpaRepository<Item, UUID> {
    List<Item> findByOrderId(UUID orderId);
    List<Item> findAll();
}
