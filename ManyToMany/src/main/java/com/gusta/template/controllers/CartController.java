package com.gusta.template.controllers;

import com.gusta.template.models.vo.*;
import com.gusta.template.services.*;
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
        return service.addItemInCartByProductId(cartId, productId, Integer.valueOf(amount.toString()));
    }

    @GetMapping("getCart/{cartId}")
    public CartVO getCart(@PathVariable(name = "cartId") Long cartId) {
        return service.getCartByCartId(cartId);
    }

    @DeleteMapping("removeItem/{cartId}/{itemId}")
    public CartVO removeItem(
            @PathVariable(name = "cartId") Long cartId,
            @PathVariable(name = "itemId") Long itemId
    ) {
        return service.removeItemByItemId(cartId, itemId);
    }

    @PatchMapping("reduceItemAmount/{cartId}/{productId}/{amount}")
    public CartVO reduceItemAmount(
            @PathVariable(name = "cartId") Long cartId,
            @PathVariable(name = "productId") Long productId,
            @PathVariable(name = "amount") Long amount
    ) {
        return service.reduceItemAmountByProductId(cartId, productId, Integer.valueOf(amount.toString()));
    }
}
