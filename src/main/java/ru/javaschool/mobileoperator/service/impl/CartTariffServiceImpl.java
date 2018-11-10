package ru.javaschool.mobileoperator.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javaschool.mobileoperator.domain.Cart;
import ru.javaschool.mobileoperator.domain.CartItem;
import ru.javaschool.mobileoperator.domain.TariffPlan;
import ru.javaschool.mobileoperator.domain.TerminalDevice;
import ru.javaschool.mobileoperator.domain.enums.OperationType;
import ru.javaschool.mobileoperator.service.api.CartItemService;
import ru.javaschool.mobileoperator.service.api.CartService;
import ru.javaschool.mobileoperator.service.api.CartTariffService;
import ru.javaschool.mobileoperator.service.api.TariffService;
import ru.javaschool.mobileoperator.service.api.TerminalDeviceService;
import ru.javaschool.mobileoperator.utils.CartItemBuilder;

import javax.servlet.http.HttpSession;

@Service("cartTariffService")
public class CartTariffServiceImpl implements CartTariffService {

    private final Logger logger = LogManager.getLogger(CartTariffServiceImpl.class);

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
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart == null){
            cart = new Cart();
        }
        int id = 0;
        if(!cart.getCartItems().isEmpty()){
            id = cart.getCartItems().get(cart.getCartItems().size()-1).getId()+1;
        }
        CartItemBuilder builder = new CartItemBuilder.Builder(id, OperationType.CHANGE_TARIFF)
                .setTariffPlanId(tariffId)
                .setTerminalDeviceId(terminalDeviceId)
                .build();
        CartItem item = cartItemService.createItem(builder);
        cartService.addItem(cart, item);
        session.setAttribute("cart", cart);
    }
}
