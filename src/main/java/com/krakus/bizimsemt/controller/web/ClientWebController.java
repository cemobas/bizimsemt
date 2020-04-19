package com.krakus.bizimsemt.controller.web;

import com.krakus.bizimsemt.domain.Buyer;
import com.krakus.bizimsemt.model.Client;
import com.krakus.bizimsemt.service.BuyerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/clients")
public class ClientWebController {
    private BuyerServices buyerServices;

    @Autowired
    public ClientWebController(BuyerServices buyerServices){
        super();
        this.buyerServices = buyerServices;
    }

    @GetMapping("/getAll")
    public String getAllClients(Model model){
        List<Client> allClients = new ArrayList<>();
        this.buyerServices.getAllBuyers().stream().forEach(buyer -> allClients.add(Client.create(buyer)));
        model.addAttribute("clients", allClients);
        model.addAttribute("selectedClient", allClients.get(0));
        model.addAttribute("selectedClientId", allClients.get(0).getId());
        return "clients";
    }

    @PostMapping("/search")
    public String searchClient(@ModelAttribute Client client, Model model) {
        Optional<Buyer> buyer = this.buyerServices.find(client.getId());
        if(buyer.isPresent()) {
            model.addAttribute("client", Client.create(buyer.get()));
        } else {
            return null;
        }
        return "search";
    }
}
