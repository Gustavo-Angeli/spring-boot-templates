package com.gusta.template.controller;

import com.gusta.template.model.vo.*;
import com.gusta.template.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart/")
public class CartController {
    @Autowired
    private CartService service;

    @PostMapping("createCart")
    public CartVO createCart() {
        return service.createCart();
    }

    @PutMapping("addItemInCart/{cartId}/{productId}/{amount}")
    public CartVO addItemInCart(
            @PathVariable(name = "cartId") Long cartId,
            @PathVariable(name = "productId") Long productId,
            @PathVariable(name = "amount") Long amount
    ) {
        return service.addItemInCart(cartId, productId, Integer.valueOf(amount.toString()));
    }

    @GetMapping("getCart/{cartId}")
    public CartVO getCart(@PathVariable(name = "cartId") Long cartId) {
        return service.getCart(cartId);
    }

    @DeleteMapping("removeItem/{cartId}/{itemId}")
    public CartVO removeItem(
            @PathVariable(name = "cartId") Long cartId,
            @PathVariable(name = "itemId") Long itemId
    ) {
        return service.removeItem(cartId, itemId);
    }

    @PatchMapping("reduceItemAmount/{cartId}/{productId}/{amount}")
    public CartVO reduceItemAmount(
            @PathVariable(name = "cartId") Long cartId,
            @PathVariable(name = "productId") Long productId,
            @PathVariable(name = "amount") Long amount
    ) {
        return service.reduceItemAmount(cartId, productId, Integer.valueOf(amount.toString()));
    }
}
