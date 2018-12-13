package ru.javaschool.mobileoperator.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.javaschool.mobileoperator.domain.Cart;
import ru.javaschool.mobileoperator.domain.dto.AddressDto;
import ru.javaschool.mobileoperator.domain.dto.ContractDto;
import ru.javaschool.mobileoperator.domain.dto.CustomerDto;
import ru.javaschool.mobileoperator.service.api.ContractService;
import ru.javaschool.mobileoperator.service.api.PhoneNumberService;
import ru.javaschool.mobileoperator.service.api.ProfileService;
import ru.javaschool.mobileoperator.service.api.SaleService;
import ru.javaschool.mobileoperator.service.api.TariffService;
import ru.javaschool.mobileoperator.utils.CartHelper;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/sale")
public class SaleController {

    @Autowired
    private TariffService tariffService;

    @Autowired
    private PhoneNumberService phoneNumberService;

    @Autowired
    private SaleService saleService;

    @Autowired
    private ContractService contractService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private CartHelper cartHelper;

    @Autowired
    private Cart cart;


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

    /**
     * Get method for sale page
     *
     * @param model ui model
     * @return sale page
     */
    @GetMapping
    public String salePage(Model model, HttpSession session) {
        model.addAttribute("cart", cart);
        model.addAttribute("tariffs", tariffService.findAll());
        model.addAttribute("numbers", phoneNumberService.getAllEmptyNumbers());
        return "sale";
    }

    /**
     * Post method to sale contract
     *
     * @param customerDto customer dt
     * @param tariffId    chosen tariff id
     * @param numberId    chosen number id
     * @return redirect to sale page {@link #salePage(Model, HttpSession)} (Model)}
     */
    @PostMapping(value = "/confirm")
    public String confirmSale(@Valid CustomerDto customerDto,
                              @Valid AddressDto addressDto,
                              @RequestParam("tariff") Long tariffId,
                              @RequestParam("number") Long numberId,
                              BindingResult result,
                              Model model) {
        if (result.hasErrors()) {
            model.addAttribute("error", "validations error");
            return "sale";
        }
        customerDto.setAddress(addressDto);
        saleService.saleContract(customerDto, tariffId, numberId);
        return "redirect:/sale";
    }

    /**
     * Post method to sale terminal device to existing personal account
     *
     * @param customerId personal account id
     * @param tariffId   tariff id
     * @param numberId   phone number id
     * @param session    http session
     * @return redirect to cart page
     */
    @PostMapping(value = "/confirm/customer")
    public String confirmSaleToExistPersonalAccountToCart(@RequestParam("customerId") Long customerId,
                                                          @RequestParam("tariff") Long tariffId,
                                                          @RequestParam("number") Long numberId,
                                                          HttpSession session) {
        return "redirect:/cart";
    }

    /**
     * Search customer by number
     *
     * @param model  ui model
     * @param number phone number
     * @return sale page with filled fields by customer info
     */
    @PostMapping("/search")
    public String searchCustomer(Model model,
                                 @RequestParam("number") String number) {
        ContractDto contract = contractService.getContractWithOptions(number);
        if (contract == null) {
            model.addAttribute("message", "Not found");
        } else {
            model.addAttribute("contract", contract);
            model.addAttribute("tariffs", tariffService.findAll());
            model.addAttribute("numbers", phoneNumberService.getAllEmptyNumbers());
        }
        return "sale";
    }
}
