package ru.javaschool.mobileoperator.utils;

import ru.javaschool.mobileoperator.domain.Cart;
import ru.javaschool.mobileoperator.domain.CartItem;

import javax.servlet.http.HttpSession;

public class CartHelper {
    public boolean alreadyExist(Cart cart, CartItem cartItem){
        switch (cartItem.getOperationType()){
            case SALE:
                for (CartItem cartItem1: cart.getCartItems()){
                    if (cartItem.getCustomer().equals(cartItem1.getCustomer())
                            && cartItem.getPhoneNumber().equals(cartItem1.getPhoneNumber())) {
                        return true;
                    }
                }
                break;
            case ADD_LOCK:
                return lockConflict(cart, cartItem);
            case REMOVE_LOCK:
                return lockConflict(cart, cartItem);
            case ADD_OPTION:
                return optionConflict(cart, cartItem);
            case REMOVE_OPTION:
                return optionConflict(cart, cartItem);
            case CHANGE_TARIFF:
                for (CartItem cartItem1: cart.getCartItems()){
                    if (cartItem.getTariffPlan().equals(cartItem1.getTariffPlan())
                            && cartItem.getTerminalDevice().equals(cartItem1.getTerminalDevice())) {
                        return true;
                    }
                }
                break;
        }
        return false;
    }

    public Cart getCart(HttpSession session){
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart == null){
            cart = new Cart();
        }
        return cart;
    }

    private boolean lockConflict(Cart cart, CartItem cartItem){
        for (CartItem cartItem1: cart.getCartItems()){
            if (cartItem.getLock().equals(cartItem1.getLock())
                    && cartItem.getTerminalDevice().equals(cartItem1.getTerminalDevice())) {
                return true;
            }
        }
        return false;
    }

    private boolean optionConflict(Cart cart, CartItem cartItem){
        for (CartItem cartItem1: cart.getCartItems()){
            if (cartItem.getOption().equals(cartItem1.getOption())
                    && cartItem.getTerminalDevice().equals(cartItem1.getTerminalDevice())) {
                return true;
            }
        }
        return false;
    }
}
