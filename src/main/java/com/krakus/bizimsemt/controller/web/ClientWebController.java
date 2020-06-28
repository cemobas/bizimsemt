package com.krakus.bizimsemt.controller.web;

import com.krakus.bizimsemt.domain.Buyer;
import com.krakus.bizimsemt.model.Client;
import com.krakus.bizimsemt.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.async.DeferredResult;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.Callable;

@Controller
@RequestMapping("/clients")
public class ClientWebController {

    @Autowired
    private BuyerService buyerService;

    @Autowired
    private AsyncTaskExecutor executor;

    @GetMapping("/search")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Callable<String> searchClientUI(Model model, HttpServletRequest httpServletRequest){
        System.out.println("is asynch supported: " + httpServletRequest.isAsyncSupported());
        System.out.println("thread from servlet container: " + Thread.currentThread().getName());

        return () -> {
            System.out.println("thread from spring mvc task executor: " + Thread.currentThread().getName());
            List<Client> allClients = new ArrayList<>();
            this.buyerService.getAllBuyers().stream().forEach(buyer -> allClients.add(Client.create(buyer)));
            model.addAttribute("clients", allClients);
            model.addAttribute("selectedClient", allClients.get(0));
            return "searchClient";
        };
    }

    @PostMapping("/fetch")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public DeferredResult<String> fetchClient(@ModelAttribute Client client, Model model, HttpServletRequest httpServletRequest) {
        System.out.println("is asynch supported: " + httpServletRequest.isAsyncSupported());
        System.out.println("thread from servlet container: " + Thread.currentThread().getName());

        DeferredResult<String> deferredResult = new DeferredResult<String>();

        executor.execute(() -> {
            System.out.println("thread from spring mvc task executor: " + Thread.currentThread().getName());
            /* Thread.sleep(6000); to test redirecting to common error page */
            Optional<Buyer> buyer = buyerService.find(client.getId());
            if(buyer.isPresent()) {
                model.addAttribute("client", Client.create(buyer.get()));
            } else {
                throw new NoSuchElementException("client.not.found");
            }
            deferredResult.setResult("fetchClient");
        });

        return deferredResult;
    }
}
