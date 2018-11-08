package ru.javaschool.mobileoperator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javaschool.mobileoperator.domain.*;
import ru.javaschool.mobileoperator.domain.enums.OperationType;
import ru.javaschool.mobileoperator.service.api.*;

import javax.servlet.http.HttpSession;

@Service("cartTariffService")
public class CartTariffServiceImpl implements CartTariffService {

    @Autowired
    private TerminalDeviceService terminalDeviceService;

    @Autowired
    private TariffService tariffService;

    @Autowired
    private CartService cartService;

    @Autowired
    private CartItemService cartItemService;

    @Override
    public void changeTariffPlan(Long terminalDeviceId, Long tariffId, HttpSession session) {
        TerminalDevice terminalDevice = terminalDeviceService.find(terminalDeviceId);
        TariffPlan tariffPlan = tariffService.find(tariffId);
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart == null){
            cart = new Cart();
        }
        int id = 0;
        if(!cart.getCartItems().isEmpty()){
            id = cart.getCartItems().get(cart.getCartItems().size()-1).getId()+1;
        }

        CartItem item = cartItemService.createItem(
                id, OperationType.CHANGE_TARIFF, tariffPlan, null, null, null, terminalDevice, null);
        cartService.addItem(cart, item);
        session.setAttribute("cart", cart);
    }
}
