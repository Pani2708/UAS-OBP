package com.example.demospringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.time.LocalTime;

import com.example.demospringboot.entity.Customer;
import com.example.demospringboot.entity.Reservation;
import com.example.demospringboot.service.ReservationService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ReservationController {

    @Autowired
    private ReservationService service;

    @GetMapping("/reservation")
    public String reservationPage(Model model, HttpSession session) {
        Customer c = (Customer) session.getAttribute("customer");
        model.addAttribute("customer", c);
        return "reservation";
    }

    @PostMapping("/reservation/save")
    public String saveReservation(
            @RequestParam String tanggal,
            @RequestParam String jam,
            @RequestParam int jumlah,
            HttpSession session) {
                
        return "redirect:/reservation/history";
    }

    @GetMapping("/reservation/history")
    public String history(Model model, HttpSession session) {
        Customer c = (Customer) session.getAttribute("customer");
        if (c == null) {
            return "redirect:/login";
        }
        model.addAttribute("history",
                service.getHistoryByCustomer(c.getId()));

        return "history.html";
    }

    @GetMapping("/reservation/form")
    public String formReservasi(HttpSession session) {
        if (session.getAttribute("customer") == null) {
            return "redirect:/login";
        }
        return "reservation";
    }

    @GetMapping("/profile")
    public String profilePage(Model model, HttpSession session) {
        Customer c = (Customer) session.getAttribute("customer");

        if (c == null) {
            return "redirect:/login";
        }

        model.addAttribute("customer", c);
        return "profile";
    }

    @PostMapping("/profile/update")
    public String updateProfile(
            @RequestParam String personName,
            @RequestParam String email,
            @RequestParam String phoneNo,
            HttpSession session) {

        Customer c = (Customer) session.getAttribute("customer");
        if (c == null)
            return "redirect:/login";

        c.setPersonName(personName);
        c.setEmail(email);
        c.setPhoneNo(phoneNo);

        service.saveProfile(c);

        session.setAttribute("customer", c);

        return "redirect:/profile";
    }

    @GetMapping("/profile/edit")
    public String editProfilePage(Model model, HttpSession session) {
        Customer c = (Customer) session.getAttribute("customer");

        if (c == null) {
            return "redirect:/login";
        }

        model.addAttribute("customer", c);
        return "editprofile";
    }
}
