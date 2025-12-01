package com.example.demospringboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demospringboot.entity.Person;
import com.example.demospringboot.entity.Staff;
import com.example.demospringboot.entity.Customer;
import com.example.demospringboot.service.PersonService;
import com.example.demospringboot.service.StaffService;
import com.example.demospringboot.service.CustomerService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class PersonController {

    @Autowired
    private PersonService personService;
    @Autowired
    private StaffService staffService;
    @Autowired
    private CustomerService customerService;

    @GetMapping(value = { "/person", "/person/" })
    public String personPage(Model model, HttpServletRequest request) {
        if (request.getSession().getAttribute("staff") != null) {
            List<Person> personList = personService.getAllPerson();
            model.addAttribute("personList", personList);
            model.addAttribute("personInfo", new Person());
            return "person.html";

        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/person/{id}")
    public String personGetRec(Model model, @PathVariable("id") long id, HttpServletRequest request) {
        if (request.getSession().getAttribute("staff") != null) {
            List<Person> personList = personService.getAllPerson();
            Person personRec = personService.getPersonById(id);
            model.addAttribute("personList", personList);
            model.addAttribute("personRec", personRec);
            return "person.html";

        } else {
            return "redirect:/login";
        }
    }

    @PostMapping(value = { "/person/submit/", "/person/submit/{id}" }, params = { "add" })
    public String personAdd(@ModelAttribute("personInfo") Person personInfo) {
        personService.addPerson(personInfo);
        return "redirect:/person";
    }

    @PostMapping(value = "/person/submit/{id}", params = { "edit" })
    public String personEdit(@ModelAttribute("personInfo") Person personInfo, @PathVariable("id") long id) {
        personService.updatePerson(id, personInfo);
        return "redirect:/person";
    }

    @PostMapping(value = "/person/submit/{id}", params = { "delete" })
    public String personDelete(@PathVariable("id") long id) {
        personService.deletePerson(id);
        return "redirect:/person";
    }

    @GetMapping(value = { "/staff", "/staff/" })
    public String staffPage(Model model, HttpServletRequest request) {
        if (request.getSession().getAttribute("staff") != null) {
            List<Staff> staffList = staffService.getAllStaff();
            model.addAttribute("staffList", staffList);
            model.addAttribute("staffInfo", new Staff());
            model.addAttribute("logStaff", request.getSession().getAttribute("staff"));
            return "staff.html";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/staff/logout")
    public String staffLogout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/login";
    }

    @GetMapping("/staff/{id:[0-9]+}")
    public String staffGetRec(Model model, @PathVariable("id") long id, HttpServletRequest request) {
        List<Staff> staffList = staffService.getAllStaff();
        Staff staffRec = staffService.getStaffById(id);
        model.addAttribute("staffList", staffList);
        model.addAttribute("staffRec", staffRec);
        model.addAttribute("logCustomer", request.getSession().getAttribute("customer"));
        return "staff.html";
    }

    @PostMapping(value = { "/staff/submit/", "/staff/submit/{id}" }, params = { "add" })
    public String staffAdd(@ModelAttribute("staffInfo") Staff staffInfo) {
        staffService.addStaff(staffInfo);
        return "redirect:/staff";
    }

    @PostMapping(value = "/staff/submit/{id}", params = { "edit" })
    public String staffEdit(@ModelAttribute("staffInfo") Staff staffInfo, @PathVariable("id") long id) {
        staffService.updateStaff(id, staffInfo);
        return "redirect:/staff";
    }

    @PostMapping(value = "/staff/submit/{id}", params = { "delete" })
    public String staffDelete(@PathVariable("id") long id) {
        staffService.deleteStaff(id);
        return "redirect:/staff";
    }

    @GetMapping("/staff/menu")
    public String staffMenu(HttpSession session) {
        if (session.getAttribute("staff") == null) {
            return "redirect:/login";
        }
        return "menustaff.html";
    }

    @GetMapping({ "/customer", "/customer/" })
    public String customerPage(Model model, HttpSession session) {

        Staff loggedStaff = (Staff) session.getAttribute("staff");

        if (loggedStaff == null) {
            return "redirect:/login";
        }

        model.addAttribute("customerList", customerService.getAllCustomer());
        model.addAttribute("customerInfo", new Customer());
        model.addAttribute("logStaff", loggedStaff);

        return "customer.html";
    }

    @GetMapping(value = "/customer/logout")
    public String customerLogout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/login";
    }

    @GetMapping("/customer/{id:[0-9]+}")
    public String customerGetRec(Model model, @PathVariable("id") long id, HttpServletRequest request) {
        List<Customer> customerList = customerService.getAllCustomer();
        Customer customerRec = customerService.getCustomerById(id);
        model.addAttribute("customerList", customerList);
        model.addAttribute("customerRec", customerRec);
        model.addAttribute("logCustomer", request.getSession().getAttribute("customer"));
        return "customer.html";
    }

    @PostMapping(value = { "/customer/submit/", "/customer/submit/{id}" }, params = { "add" })
    public String customerAdd(@ModelAttribute("customerInfo") Customer customerInfo) {
        customerService.addCustomer(customerInfo);
        return "redirect:/customer";
    }

    @PostMapping(value = "/customer/submit/{id}", params = { "edit" })
    public String customerEdit(@ModelAttribute("customerInfo") Customer customerInfo, @PathVariable("id") long id) {
        customerService.updateCustomer(id, customerInfo);
        return "redirect:/customer";
    }

    @PostMapping(value = "/customer/submit/{id}", params = { "delete" })
    public String customerDelete(@PathVariable("id") long id) {
        customerService.deleteCustomer(id);
        return "redirect:/customer";
    }

    @GetMapping("/customer/menu")
    public String customerMenu(HttpSession session) {
        if (session.getAttribute("customer") == null) {
            return "redirect:/login";
        }
        return "menureservasi.html";
    }

    @GetMapping("/customer/profile")
    public String profile(HttpSession session, Model model) {
        Customer c = (Customer) session.getAttribute("customer");
        if (c == null)
            return "redirect:/login";

        model.addAttribute("customer", c);
        return "profile.html";
    }

    @GetMapping(value = "/login")
    public String customerLoginPage(Model model, HttpServletRequest request) {
        Customer loggedCustomer = (Customer) request.getSession().getAttribute("customer");
        if (loggedCustomer != null) {
            return "redirect:/customer";
        }
        return "login.html";
    }

    @PostMapping("/validateLogin")
    public String login(
            @RequestParam String email,
            @RequestParam String password,
            HttpServletRequest request,
            Model model) {

        Customer c = customerService.findCustomer(email, password);
        Staff s = staffService.findStaff(email, password);

        if (c != null) {
            request.getSession().setAttribute("customer", c);
            return "redirect:/customer/menu";
        }

        if (s != null) {
            request.getSession().setAttribute("staff", s);
            return "redirect:/staff/menu";
        }

        model.addAttribute("error", "Email atau password salah!");
        return "login.html";
    }

}
