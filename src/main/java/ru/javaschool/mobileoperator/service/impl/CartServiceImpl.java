package ru.javaschool.mobileoperator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javaschool.mobileoperator.domain.Cart;
import ru.javaschool.mobileoperator.domain.CartItem;
import ru.javaschool.mobileoperator.domain.CartResult;
import ru.javaschool.mobileoperator.domain.enums.CartItemResult;
import ru.javaschool.mobileoperator.service.api.CartItemService;
import ru.javaschool.mobileoperator.service.api.CartService;
import ru.javaschool.mobileoperator.utils.CartHelper;

import java.util.Iterator;

@Service("cartService")
public class CartServiceImpl implements CartService {

    @Autowired
    private CartHelper cartHelper;

    @Autowired
    private CartItemService cartItemService;

    @Override
    public void addItem(Cart cart, CartItem item) {
        if (!cartHelper.alreadyExist(cart, item)) {
            cart.addItem(item);
        }
    }

    @Override
    public void removeItem(Cart cart, CartItem item) {
        cart.removeItem(item);
    }

    @Override
    public void updateItem(Cart cart, CartItem item) {
        cart.updateItem(item);
    }

    @Override
    public void confirm(Cart cart, CartResult cartResult) {
        Iterator<CartItem> itemIterator = cart.getCartItems().iterator();
        while (itemIterator.hasNext()) {
            CartItem item = itemIterator.next();
            try {
                cartItemService.proceed(item);
                item.setResult(CartItemResult.SUCCESS);
                item.setResultMessage("Completed");
                cartResult.getCartItems().add(item);
                itemIterator.remove();
                cart.setQuantity(cart.getQuantity() - 1);
            } catch (RuntimeException e) {
                item.setResult(CartItemResult.FAIL);
                item.setResultMessage(e.getMessage());
                cartResult.getCartItems().add(item);
                itemIterator.remove();
                cart.setQuantity(cart.getQuantity() - 1);
            }
        }
    }
}
