package com.example.demospringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;

import com.example.demospringboot.entity.Transaction;
import com.example.demospringboot.entity.Customer;
import com.example.demospringboot.entity.DetailMenu;
import com.example.demospringboot.entity.Composition;
import com.example.demospringboot.service.TransactionService;
import com.example.demospringboot.service.DetailMenuService;
import com.example.demospringboot.service.CompositionService;

@Controller
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private DetailMenuService detailMenuService;

    @Autowired
    private CompositionService compositionService;

    @GetMapping("/transaction")
    public String transactionPage(
            @RequestParam(required = false) Long menuId,
            Model model,
            HttpSession session) {

        Customer customer = (Customer) session.getAttribute("customer");
        model.addAttribute("customer", customer);
        model.addAttribute("detailMenus", detailMenuService.getAllDetailMenu());
        model.addAttribute("compositions", compositionService.getAllComposition());

        if (menuId != null) {
            DetailMenu selectedMenu = detailMenuService.getDetailMenuById(menuId);
            model.addAttribute("selectedMenu", selectedMenu);
        }

        if (customer != null) {
            model.addAttribute("history", transactionService.getHistoryByCustomer(customer.getId()));
        }

        return "transaction";
    }

    @PostMapping("/transaction/save")
    public String saveTransaction(
            @RequestParam String idTransaksi,
            @RequestParam("detailMenuId") long menuId,
            @RequestParam int qty,
            HttpSession session) {

        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null)
            return "redirect:/login";

        DetailMenu detailMenu = detailMenuService.getDetailMenuById(menuId);

        Transaction trx = new Transaction();
        trx.setIdTransaksi(idTransaksi);
        trx.setCustomer(customer);
        trx.setDetailMenu(detailMenu);
        trx.setQty(qty);
        trx.updateTotalHarga();

        transactionService.saveTransaction(trx);

        return "redirect:/transaction";
    }

    @GetMapping("/transaction/history")
    public String historyPage(Model model, HttpSession session) {
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null) {
            return "redirect:/login";
        }
        model.addAttribute("customer", customer);
        model.addAttribute("detailMenus", detailMenuService.getAllDetailMenu());
        model.addAttribute("history", transactionService.getHistoryByCustomer(customer.getId()));
        return "transaction";
    }

    @GetMapping("/transaction/form")
    public String formTransaksi(HttpSession session, Model model) {
        if (session.getAttribute("customer") == null) {
            return "redirect:/login";
        }
        model.addAttribute("detailMenus", detailMenuService.getAllDetailMenu());
        model.addAttribute("composition", compositionService.getAllComposition());
        return "transaction";
    }

    @PostMapping("/transaction/total")
    public String hitungTotalPembelian(Model model, HttpSession session) {
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null) {
            return "redirect:/login";
        }

        model.addAttribute("customer", customer);
        model.addAttribute("detailMenus", detailMenuService.getAllDetailMenu());
        model.addAttribute("history", transactionService.getHistoryByCustomer(customer.getId()));

        Integer totalHarga = transactionService.hitungTotalByCustomer(customer.getId());
        model.addAttribute("totalHarga", totalHarga);

        return "transaction";
    }
}