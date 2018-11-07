package ru.javaschool.mobileoperator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javaschool.mobileoperator.domain.Cart;
import ru.javaschool.mobileoperator.domain.CartItem;
import ru.javaschool.mobileoperator.service.api.CartService;
import ru.javaschool.mobileoperator.utils.CartHelper;

@Service("cartService")
public class CartServiceImpl implements CartService {

    @Autowired
    private CartHelper cartHelper;

    @Override
    public void addItem(Cart cart, CartItem item) {
        if(!cartHelper.alreadyExist(cart, item)){
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
}
