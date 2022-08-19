package com.onlinestore.repository;

import com.onlinestore.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Transactional
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {

    List<ShoppingCart> findByStatus(String status);

    @Modifying
    @Query("Update ShoppingCart Set status=?1 Where cartId =?2 ")
    void delete(String status, int cartId);

    ShoppingCart findByStatusAndCartId(String status, int cartId);
}
