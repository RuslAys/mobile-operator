package ru.javaschool.mobileoperator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javaschool.mobileoperator.domain.*;
import ru.javaschool.mobileoperator.domain.enums.OperationType;
import ru.javaschool.mobileoperator.service.api.CartLockService;
import ru.javaschool.mobileoperator.service.api.CartItemService;
import ru.javaschool.mobileoperator.service.api.CartService;
import ru.javaschool.mobileoperator.service.api.LockService;
import ru.javaschool.mobileoperator.service.api.TerminalDeviceService;

import javax.servlet.http.HttpSession;

@Service("cartLockService")
public class CartLockServiceImpl implements CartLockService {

    @Autowired
    private TerminalDeviceService terminalDeviceService;

    @Autowired
    private LockService lockService;

    @Autowired
    private CartService cartService;

    @Autowired
    private CartItemService cartItemService;

    @Override
    public void addLock(Long terminalDeviceId, Long lockId, HttpSession session) {
        TerminalDevice terminalDevice = terminalDeviceService.find(terminalDeviceId);
        Lock lock = lockService.find(lockId);
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart == null){
            cart = new Cart();
        }
        int id = 0;
        if(!cart.getCartItems().isEmpty()){
            id = cart.getCartItems().get(cart.getCartItems().size()-1).getId()+1;
        }

        CartItem item = cartItemService.createItem(
                id, OperationType.ADD_LOCK, null, null, lock, null, terminalDevice, null);
        cartService.addItem(cart, item);
        session.setAttribute("cart", cart);
    }

    @Override
    public void removeLock(Long terminalDeviceId, Long lockId, HttpSession session) {
        TerminalDevice terminalDevice = terminalDeviceService.find(terminalDeviceId);
        Lock lock = lockService.find(lockId);
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart == null){
            cart = new Cart();
        }
        int id = 0;
        if(!cart.getCartItems().isEmpty()){
            id = cart.getCartItems().get(cart.getCartItems().size()-1).getId()+1;
        }

        CartItem item = cartItemService.createItem(
                id, OperationType.REMOVE_LOCK, null, null, lock, null, terminalDevice, null);
        cartService.addItem(cart, item);
        session.setAttribute("cart", cart);
    }
}
