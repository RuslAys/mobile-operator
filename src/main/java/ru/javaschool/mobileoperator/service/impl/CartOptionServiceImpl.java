package ru.javaschool.mobileoperator.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javaschool.mobileoperator.domain.Cart;
import ru.javaschool.mobileoperator.domain.CartItem;
import ru.javaschool.mobileoperator.domain.Option;
import ru.javaschool.mobileoperator.domain.TerminalDevice;
import ru.javaschool.mobileoperator.domain.enums.OperationType;
import ru.javaschool.mobileoperator.service.api.CartOptionService;
import ru.javaschool.mobileoperator.service.api.CartItemService;
import ru.javaschool.mobileoperator.service.api.CartService;
import ru.javaschool.mobileoperator.service.api.OptionService;
import ru.javaschool.mobileoperator.service.api.TerminalDeviceService;
import ru.javaschool.mobileoperator.utils.CartItemBuilder;

import javax.servlet.http.HttpSession;

@Service("cartOptionService")
public class CartOptionServiceImpl implements CartOptionService {

    private final Logger logger = LogManager.getLogger(CartOptionServiceImpl.class);

    @Autowired
    private TerminalDeviceService terminalDeviceService;

    @Autowired
    private OptionService optionService;

    @Autowired
    private CartService cartService;

    @Autowired
    private CartItemService cartItemService;

    @Override
    public void addOption(Long terminalDeviceId, Long optionId, HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart == null){
            cart = new Cart();
        }
        int id = 0;
        if(!cart.getCartItems().isEmpty()){
            id = cart.getCartItems().get(cart.getCartItems().size()-1).getId()+1;
        }
        CartItemBuilder builder = new CartItemBuilder.Builder(id, OperationType.ADD_OPTION)
                .setOptionId(optionId)
                .setTerminalDeviceId(terminalDeviceId)
                .build();
        CartItem item = cartItemService.createItem(builder);
        cartService.addItem(cart, item);
        session.setAttribute("cart", cart);
    }

    @Override
    public void removeOption(Long terminalDeviceId, Long optionId, HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart == null){
            cart = new Cart();
        }
        int id = 0;
        if(!cart.getCartItems().isEmpty()){
            id = cart.getCartItems().get(cart.getCartItems().size()-1).getId()+1;
        }
        CartItemBuilder builder = new CartItemBuilder.Builder(id, OperationType.REMOVE_OPTION)
                .setOptionId(optionId)
                .setTerminalDeviceId(terminalDeviceId)
                .build();
        CartItem item = cartItemService.createItem(builder);
        cartService.addItem(cart, item);
        session.setAttribute("cart", cart);
    }
}
