package ru.javaschool.mobileoperator.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javaschool.mobileoperator.domain.Cart;
import ru.javaschool.mobileoperator.domain.CartItem;
import ru.javaschool.mobileoperator.service.api.CartItemService;
import ru.javaschool.mobileoperator.service.api.CartService;
import ru.javaschool.mobileoperator.utils.CartHelper;

import java.util.Iterator;

@Service("cartService")
public class CartServiceImpl implements CartService {

    private final Logger logger = LogManager.getLogger(CartServiceImpl.class);

    @Autowired
    private CartHelper cartHelper;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private CartService cartService;

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

    @Override
    public void confirm(Cart cart) {
        Iterator<CartItem> itemIterator = cart.getCartItems().iterator();
        while (itemIterator.hasNext()){
            try {
                cartItemService.proceed(itemIterator.next());
                itemIterator.remove();
                cart.setQuantity(cart.getQuantity() - 1);
            }catch (RuntimeException e){
                itemIterator.remove();
                cart.setQuantity(cart.getQuantity() - 1);
                logger.error(e.getMessage());
                throw e;
            }
        }
    }
}
