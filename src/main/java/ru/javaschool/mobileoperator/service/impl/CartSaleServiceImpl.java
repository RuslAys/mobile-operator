package ru.javaschool.mobileoperator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javaschool.mobileoperator.domain.*;
import ru.javaschool.mobileoperator.domain.enums.OperationType;
import ru.javaschool.mobileoperator.service.api.*;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Service("cartSaleService")
public class CartSaleServiceImpl implements CartSaleService {

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private CartService cartService;

    @Autowired
    private TariffService tariffService;

    @Autowired
    private PhoneNumberService phoneNumberService;

    @Override
    public void sale(String firstName, String lastName, Date birthDate, String city, String street,
                     String house, String email, String passport, Long tariffId, Long numberId,
                     HttpSession session) {
        TariffPlan tariffPlan = tariffService.find(tariffId);
        PhoneNumber phoneNumber = phoneNumberService.find(numberId);
        Customer customer = new Customer(firstName, lastName, birthDate);
        customer.getAddress().setCity(city);
        customer.getAddress().setStreet(street);
        customer.getAddress().setHouseNumber(house);
        customer.setEmail(email);
        customer.setPassport(passport);

        Cart cart = (Cart) session.getAttribute("cart");
        if(cart == null){
            cart = new Cart();
        }
        int id = 0;
        if(!cart.getCartItems().isEmpty()){
            id = cart.getCartItems().get(cart.getCartItems().size()-1).getId()+1;
        }

        CartItem item = cartItemService.createItem(
                id, OperationType.ADD_OPTION, tariffPlan, null, null, customer, null, phoneNumber);
        cartService.addItem(cart, item);
        session.setAttribute("cart", cart);
    }
}
