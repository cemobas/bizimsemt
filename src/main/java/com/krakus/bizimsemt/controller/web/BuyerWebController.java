package com.krakus.bizimsemt.controller.web;

import com.krakus.bizimsemt.domain.Buyer;
import com.krakus.bizimsemt.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/buyers")
public class BuyerWebController {

    @Autowired
    private BuyerService buyerService;

    @GetMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addBuyerUI(Buyer buyer, Model model){
        return "addBuyer";
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addBuyer(@ModelAttribute @Valid Buyer buyer, BindingResult bindingResult, Model model) {
        if(bindingResult.hasFieldErrors()) {
            return "addBuyer";
        } else {
            model.addAttribute("buyer", buyerService.add(buyer));
        }
        return "buyerAdded";
    }
}
