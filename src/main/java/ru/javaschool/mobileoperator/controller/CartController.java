package ru.javaschool.mobileoperator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping
    public String cartPage(Model model,
                           HttpSession session){
        model.addAttribute("cart", cartHelper.getCart(session));
        return "cart";
    }

    @PostMapping("/confirm")
    public String confirmItems(HttpSession session){
        cartService.confirm(cartHelper.getCart(session));
        return "redirect:/cart";
    }
}
