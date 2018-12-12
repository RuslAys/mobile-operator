package ru.javaschool.mobileoperator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.javaschool.mobileoperator.domain.Cart;
import ru.javaschool.mobileoperator.domain.CartItem;
import ru.javaschool.mobileoperator.service.api.CartService;
import ru.javaschool.mobileoperator.utils.CartHelper;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private CartHelper cartHelper;

    @Autowired
    private Cart cart;

    /**
     * Get method for cart page
     *
     * @param model   ui model
     * @param session http session
     * @return cart page
     */
    @GetMapping
    public String cartPage(Model model,
                           HttpSession session) {
        model.addAttribute("cart", cart);
        return "cart";
    }

    /**
     * Post method for confirm items
     *
     * @return redirect to cart page {@link #cartPage(Model, HttpSession)}
     */
    @PostMapping("/confirm")
    public String confirmItems() {
        cartService.confirm(cart);
        return "redirect:/cart";
    }

    /**
     * Post method for confirm items
     *
     * @return redirect to cart page {@link #cartPage(Model, HttpSession)}
     */
    @PostMapping("/remove")
    public String removeItem(@RequestParam("itemId") Integer id) {
        CartItem cartItem = cart.getCartItems().get(id);
        cartService.removeItem(cart, cartItem);
        return "redirect:/cart";
    }
}
