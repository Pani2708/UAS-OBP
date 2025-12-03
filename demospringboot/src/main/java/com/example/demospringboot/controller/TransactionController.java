package com.example.demospringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import java.util.Map;
import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.List;
import com.example.demospringboot.entity.Transaction;
import com.example.demospringboot.entity.Customer;
import com.example.demospringboot.entity.DetailMenu;
import com.example.demospringboot.entity.Reservation;
import com.example.demospringboot.service.TransactionService;
import com.example.demospringboot.service.ReservationService;
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

    @Autowired
    private ReservationService reservationService;

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

    @GetMapping("/kelolatransaction")
    public String kelolaTransaction(Model model) {
        List<Transaction> allTransactions = transactionService.getAllTransactions();
        Map<Long, String> tanggalMap = new HashMap<>();
        for (Transaction trx : allTransactions) {
            if (trx.getCustomer() != null) {
                Reservation res = reservationService.getLastReservationByCustomer(trx.getCustomer().getId());
                if (res != null) {
                    tanggalMap.put(trx.getIdTransaksi(), res.getTanggal().toString());
                } else {
                    tanggalMap.put(trx.getIdTransaksi(), "-");
                }
            } else {
                tanggalMap.put(trx.getIdTransaksi(), "-");
            }
        }
        model.addAttribute("tanggalMap", tanggalMap);
        model.addAttribute("allTransactions", allTransactions);
        return "kelolatransaction";
    }

    @PostMapping("/transaction/save")
    public String saveTransaction(
            @RequestParam("detailMenuId") long menuId,
            @RequestParam int qty,
            HttpSession session, Model model) {

        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null)
            return "redirect:/login";

        DetailMenu detailMenu = detailMenuService.getDetailMenuById(menuId);

        if (detailMenu.getStatus().equalsIgnoreCase("Habis")) {
            model.addAttribute("errorMessage",
                    "Mohon maaf, " + detailMenu.getMenuList() + " sedang habis.");
            model.addAttribute("customer", customer);
            model.addAttribute("detailMenus", detailMenuService.getAllDetailMenu());
            model.addAttribute("history", transactionService.getHistoryByCustomer(customer.getId()));

            return "transaction";
        }

        Transaction trx = new Transaction();
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
        return "history";
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