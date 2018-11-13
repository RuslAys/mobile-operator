package ru.javaschool.mobileoperator.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.javaschool.mobileoperator.domain.Cart;
import ru.javaschool.mobileoperator.domain.CartItem;
import ru.javaschool.mobileoperator.domain.enums.OperationType;
import ru.javaschool.mobileoperator.service.api.CartLockService;
import ru.javaschool.mobileoperator.service.api.CartItemService;
import ru.javaschool.mobileoperator.service.api.CartService;
import ru.javaschool.mobileoperator.utils.CartItemBuilder;

import javax.servlet.http.HttpSession;

@Service("cartLockService")
public class CartLockServiceImpl implements CartLockService {

    private final Logger logger = LogManager.getLogger(CartLockServiceImpl.class);

    @Autowired
    private CartService cartService;

    @Autowired
    private CartItemService cartItemService;

    @Override
    public void addLock(Long terminalDeviceId, Long lockId, HttpSession session, UserDetails user) {
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart == null){
            cart = new Cart();
        }
        int id = 0;
        if(!cart.getCartItems().isEmpty()){
            id = cart.getCartItems().get(cart.getCartItems().size()-1).getId()+1;
        }
        CartItemBuilder builder = new CartItemBuilder.Builder(id, OperationType.ADD_LOCK)
                .setTerminalDeviceId(terminalDeviceId)
                .setLockId(lockId)
                .setUserDetails(user)
                .build();
        CartItem item = cartItemService.createItem(builder);
        cartService.addItem(cart, item);
        session.setAttribute("cart", cart);
    }

    @Override
    public void removeLock(Long terminalDeviceId, Long lockId, HttpSession session, UserDetails user) {
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart == null){
            cart = new Cart();
        }
        int id = 0;
        if(!cart.getCartItems().isEmpty()){
            id = cart.getCartItems().get(cart.getCartItems().size()-1).getId()+1;
        }

        CartItemBuilder builder = new CartItemBuilder.Builder(id, OperationType.REMOVE_LOCK)
                .setTerminalDeviceId(terminalDeviceId)
                .setLockId(lockId)
                .setUserDetails(user)
                .build();
        CartItem item = cartItemService.createItem(builder);
        cartService.addItem(cart, item);
        session.setAttribute("cart", cart);
    }
}
