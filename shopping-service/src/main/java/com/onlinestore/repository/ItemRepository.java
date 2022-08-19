package com.onlinestore.repository;

import com.onlinestore.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Transactional
public interface ItemRepository extends JpaRepository<Item, Integer> {

    List<Item> findByStatus(String status);

    @Modifying
    @Query("Update Item Set status=?1 Where itemId =?2 ")
    void delete(String status, int itemId);

    Item findByStatusAndItemId(String status, int itemId);
}
