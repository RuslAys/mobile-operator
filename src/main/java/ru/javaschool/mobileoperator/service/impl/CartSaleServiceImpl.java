package ru.javaschool.mobileoperator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javaschool.mobileoperator.domain.Cart;
import ru.javaschool.mobileoperator.domain.CartItem;
import ru.javaschool.mobileoperator.domain.Customer;
import ru.javaschool.mobileoperator.domain.enums.OperationType;
import ru.javaschool.mobileoperator.repository.api.TariffDao;
import ru.javaschool.mobileoperator.service.api.CartItemService;
import ru.javaschool.mobileoperator.service.api.CartSaleService;
import ru.javaschool.mobileoperator.service.api.CartService;
import ru.javaschool.mobileoperator.service.api.PhoneNumberService;
import ru.javaschool.mobileoperator.service.api.TariffService;
import ru.javaschool.mobileoperator.utils.CartItemBuilder;

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
    private TariffDao tariffDao;

    @Autowired
    private PhoneNumberService phoneNumberService;

    @Override
    public void sale(String firstName, String lastName, Date birthDate, String city, String street,
                     String house, String email, String passport, Long tariffId, Long numberId,
                     HttpSession session) {
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
        CartItemBuilder builder = new CartItemBuilder.Builder(id, OperationType.SALE)
                .setCustomer(customer)
                .setTariffPlanId(tariffId)
                .setPhoneNumberId(numberId)
                .build();
        CartItem item = cartItemService.createItem(builder);
        cartService.addItem(cart, item);
        session.setAttribute("cart", cart);
    }

    @Override
    public void saleToPersonalAccount(Long personalAccountId, Long tariffId, Long numberId, HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart == null){
            cart = new Cart();
        }
        int id = 0;
        if(!cart.getCartItems().isEmpty()){
            id = cart.getCartItems().get(cart.getCartItems().size()-1).getId()+1;
        }
        CartItemBuilder builder = new CartItemBuilder.Builder(id, OperationType.SALE_TO_EXIST_PERSONAL_ACCOUNT)
                .setPersonalAccountId(personalAccountId)
                .setTariffPlanId(tariffId)
                .setPhoneNumberId(numberId)
                .build();
        CartItem item = cartItemService.createItem(builder);
        cartService.addItem(cart, item);
        session.setAttribute("cart", cart);
    }
}
