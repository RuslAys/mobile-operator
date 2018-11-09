package ru.javaschool.mobileoperator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javaschool.mobileoperator.domain.Cart;
import ru.javaschool.mobileoperator.domain.CartItem;
import ru.javaschool.mobileoperator.service.api.CartItemService;
import ru.javaschool.mobileoperator.service.api.CartService;
import ru.javaschool.mobileoperator.service.exceptions.TariffPlanException;
import ru.javaschool.mobileoperator.service.exceptions.TerminalDeviceException;
import ru.javaschool.mobileoperator.utils.CartHelper;

import java.util.Iterator;

@Service("cartService")
public class CartServiceImpl implements CartService {

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
            }catch (TerminalDeviceException e){
                itemIterator.remove();
                e.printStackTrace();
            }catch (TariffPlanException e){
                itemIterator.remove();
                e.printStackTrace();
            }
        }
    }
}
