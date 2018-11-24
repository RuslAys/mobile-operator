package ru.javaschool.mobileoperator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.javaschool.mobileoperator.domain.User;
import ru.javaschool.mobileoperator.service.api.UserService;
import ru.javaschool.mobileoperator.utils.CartHelper;
import ru.javaschool.mobileoperator.utils.RoleHelper;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private RoleHelper roleHelper;

    @Autowired
    private UserService userService;

    @Autowired
    private CartHelper cartHelper;

    /**
     * Get method to index page
     * @param model ui model
     * @param user Auth principal
     * @return if user redirect to profile, else show the search page
     */
    @GetMapping
    public String index(Model model, @AuthenticationPrincipal UserDetails user, HttpSession session){
        if(roleHelper.isOnlyUser(user)){
            return "redirect:/profile/" + user.getUsername();
        }
        model.addAttribute("cart", cartHelper.getCart(session));
        return "index";
    }

    /**
     * Post method to search by users by username
     * @param model ui model
     * @param username username to search
     * @return redirect to index {@link #index(Model, UserDetails, HttpSession)}
     */
    @PreAuthorize("hasRole('ROLE_OPERATOR')")
    @PostMapping("/search")
    public String search(Model model, @RequestParam("username") String username){
        User user = userService.getUser(username);
        if(user == null){
            model.addAttribute("user", "Not found");
        }else{
            model.addAttribute("user", userService.getUser(username));
        }
        return "index";
    }
}
