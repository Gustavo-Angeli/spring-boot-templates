package com.gusta.template.service;

import com.gusta.template.exceptions.*;
import com.gusta.template.mapper.*;
import com.gusta.template.model.entities.*;
import com.gusta.template.model.vo.*;
import com.gusta.template.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

import static com.gusta.template.utils.ParamValidation.*;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ProductRepository productRepository;

    public CartVO createCart() {
        CartEntity cart = CartEntity.builder()
                .totalPrice(0D)
                .totalAmount(0)
                .items(new ArrayList<>())
                .build();

        return DozerMapper.parseObject(cartRepository.save(cart), CartVO.class);
    }

    public CartVO addItemInCart(Long cartId, Long productId, Integer amount) {
        checkIfIsNullOrBlankThrowingEx(cartId, productId, amount);

        if (amount <= 0) throw new InvalidValueException("Not possible add this amount into cart");

        CartEntity cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RequiredObjectIsNullException("Cart not found!"));
        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new RequiredObjectIsNullException("Product not found!"));

        // If the item list of cart is empty, these condition add an item to cart
        if (cart.getItems().isEmpty()) {
            cart.getItems().add(
                    ItemEntity.builder()
                            .product(product)
                            .amount(amount)
                            .build()
            );
            cart.setTotalAmountAndTotalPrice();
            cartRepository.save(cart);
            return DozerMapper.parseObject(cart, CartVO.class);
        }

        /*
            If the item list of cart isn't empty,
             the list will be checked to see if the desired item does not already exist.
         */
        for (ItemEntity item : cart.getItems()) {
            int index = cart.getItems().indexOf(item);
            /*
              If the desired item exists, just the variable "amount" will be modified,
               adding the "item.getAmount()" and the "amount"
             */
            if (item.getProduct().getId().equals(product.getId())) {
                cart.getItems().get(index).setAmount(item.getAmount() + amount);
                cart.setTotalAmountAndTotalPrice();
                cartRepository.save(cart);
                return DozerMapper.parseObject(cart, CartVO.class);
            }
        }

        // If none of the conditions are true, a new item will be created and added to the list of items
        cart.getItems().add(
                ItemEntity.builder()
                        .product(product)
                        .amount(amount)
                        .build());
        cart.setTotalAmountAndTotalPrice();
        cartRepository.save(cart);
        return DozerMapper.parseObject(cart, CartVO.class);
    }

    public CartVO getCart(Long cartId) {
        checkIfIsNullOrBlankThrowingEx(cartId);

        CartEntity cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RequiredObjectIsNullException("Cart not found!"));

        return DozerMapper.parseObject(cart, CartVO.class);
    }

    public CartVO removeItem(Long cartId, Long itemId) {
        checkIfIsNullOrBlankThrowingEx(cartId, itemId);

        CartEntity cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RequiredObjectIsNullException("Cart not found!"));

        int index = 0;

        for (ItemEntity item : cart.getItems()) {
            if (item.getId().equals(itemId)) {
                index = cart.getItems().indexOf(item);
            }
        }

        cart.getItems().remove(index);
        cart.setTotalAmountAndTotalPrice();
        cartRepository.save(cart);
        itemRepository.deleteById(itemId);

        /*
        The reason why I didn't make the code above like this:

        for (ItemEntity item : cart.getItems()) {
            if (item.getId().equals(itemId)) {
                cart.getItems().remove(item);
                cart.setTotalAmountAndTotalPrice();
                cartRepository.save(cart);
                itemRepository.deleteById(itemId);
            }
        }

        is because every time I remove a specific item from this list, the code generates an error
        */

        return CartVO.builder()
                .id(cart.getId())
                .items(DozerMapper.parseListObjects(cart.getItems(), ItemVO.class))
                .totalAmount(cart.getTotalAmount())
                .totalPrice(cart.getTotalPrice())
                .build();
    }

    public CartVO reduceItemAmount(Long cartId, Long productId, Integer amount) {
        checkIfIsNullOrBlankThrowingEx(cartId, productId, amount);

        CartEntity cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RequiredObjectIsNullException("Cart not found!"));

        for (ItemEntity item : cart.getItems()) {
            int index = cart.getItems().indexOf(item);
            if (item.getProduct().getId().equals(productId)) {
                if (item.getAmount() < amount) throw new InvalidValueException();
                cart.getItems().get(index).setAmount(item.getAmount() - amount);
                cart.setTotalAmountAndTotalPrice();
                cartRepository.save(cart);
            }
        }
        return DozerMapper.parseObject(cart, CartVO.class);
    }

}