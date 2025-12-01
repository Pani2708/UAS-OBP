package com.example.demospringboot.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demospringboot.entity.Menu;
import com.example.demospringboot.entity.DetailMenu;
import com.example.demospringboot.entity.Composition;
import com.example.demospringboot.service.MenuService;

import jakarta.servlet.http.HttpServletRequest;

import com.example.demospringboot.repository.MenuRepository;
import com.example.demospringboot.service.DetailMenuService;
import com.example.demospringboot.service.CompositionService;

@Controller
public class MenuController {
    @Autowired
    private MenuService menuService;

    @Autowired
    private DetailMenuService detailMenuService;

    @Autowired
    private CompositionService compositionService;

    @Autowired
    private MenuRepository menuRepository;

    @GetMapping(value = { "/menu", "/menu/" })
    public String MenuPage(Model model, HttpServletRequest request) {
        if (request.getSession().getAttribute("staff") != null) {
            List<Menu> menuList = menuService.getAllMenu();
            model.addAttribute("menuList", menuList);
            model.addAttribute("menuInfo", new Menu());
            // Tambah ini untuk dropdown
            model.addAttribute("menuOptions", menuRepository.findAll());

            return "menu";

        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/menu/{id}")
    public String menuGetRec(Model model, @PathVariable("id") int id, HttpServletRequest request) {
        if (request.getSession().getAttribute("staff") != null) {
            List<Menu> menuList = menuService.getAllMenu();
            Menu menuRec = menuService.getMenuById(id);
            model.addAttribute("menuList", menuList);
            model.addAttribute("menuRec", menuRec);
            // Dropdown
            model.addAttribute("menuOptions", menuRepository.findAll());
            return "menu";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping(value = { "/menu/submit/", "/menu/submit/{id}" }, params = { "add" })
    public String menuAdd(@ModelAttribute("menuInfo") Menu menuInfo) {
        menuService.addMenu(menuInfo);
        return "redirect:/menu/";
    }

    @PostMapping(value = "/menu/submit/{id}", params = { "edit" })
    public String menuEdit(@ModelAttribute("menuInfo") Menu menuInfo,
            @PathVariable("id") Long id) {
        menuService.updateMenu(id, menuInfo);
        return "redirect:/menu/";
    }

    @PostMapping(value = "/menu/submit/{id}", params = { "delete" })
    public String menuDelete(@PathVariable("id") int id) {
        menuService.deleteMenu(id);
        return "redirect:/menu/";
    }

    @GetMapping(value = { "/detailmenu", "/detailmenu/" })
    public String DetailMenuGetRec(Model model, HttpServletRequest request) {
        if (request.getSession().getAttribute("staff") != null) {
            List<DetailMenu> detailMenuList = detailMenuService.getAllDetailMenu();
            List<Menu> menuList = menuService.getAllMenu();
            model.addAttribute("detailMenuList", detailMenuList);
            model.addAttribute("detailMenuInfo", new DetailMenu());
            model.addAttribute("menuOptions", menuList);
            return "detailmenu";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/detailmenu/{id}")
    public String detailMenuGetRec(Model model, @PathVariable("id") int id, HttpServletRequest request) {
        if (request.getSession().getAttribute("staff") != null) {
            List<DetailMenu> detailMenuList = detailMenuService.getAllDetailMenu();
            List<Menu> menuList = menuService.getAllMenu();
            DetailMenu detailMenuRec = detailMenuService.getDetailMenuById(id);
            model.addAttribute("detailMenuList", detailMenuList);
            model.addAttribute("detailMenuRec", detailMenuRec);
            model.addAttribute("detailMenuInfo", detailMenuRec != null ? detailMenuRec : new DetailMenu());
            model.addAttribute("menuOptions", menuList);

            return "detailmenu";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping(value = { "/detailmenu/submit/", "/detailmenu/submit/{id}" }, params = { "add" })
    public String detailMenuAdd(@ModelAttribute("detailMenuInfo") DetailMenu detailMenuInfo) {
        detailMenuService.addDetailMenu(detailMenuInfo);
        return "redirect:/detailmenu";
    }

    @PostMapping(value = "/detailmenu/submit/{id}", params = { "edit" })
    public String detailMenuEdit(@ModelAttribute("detailMenuInfo") DetailMenu detailMenuInfo,
            @PathVariable("id") int id) {
        detailMenuService.updateDetailMenu(id, detailMenuInfo);
        return "redirect:/detailmenu";
    }

    @PostMapping(value = "/detailmenu/submit/{id}", params = { "delete" })
    public String detailMenuDelete(@PathVariable("id") int id) {
        detailMenuService.deleteDetailMenu(id);
        return "redirect:/detailmenu";
    }

    @GetMapping(value = { "/composition", "/composition/" })
    public String CompositionGetRec(Model model, HttpServletRequest request) {
        if (request.getSession().getAttribute("staff") != null) {
            List<Composition> compositionList = compositionService.getAllComposition();
            List<Menu> menuList = menuService.getAllMenu();
            model.addAttribute("compositionList", compositionList);
            model.addAttribute("compositionInfo", new Composition());
            model.addAttribute("menuOptions", menuList);
            return "composition";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/composition/{id}")
    public String compositionGetRec(Model model, @PathVariable("id") int id, HttpServletRequest request) {
        if (request.getSession().getAttribute("staff") != null) {
            List<Composition> compositionList = compositionService.getAllComposition();
            List<Menu> menuList = menuService.getAllMenu();
            Composition compositionRec = compositionService.getCompositionById(id);
            model.addAttribute("compositionList", compositionList);
            model.addAttribute("compositionRec", compositionRec);
            model.addAttribute("compositionInfo", compositionRec != null ? compositionRec : new Composition());
            model.addAttribute("menuOptions", menuList);
            return "composition";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping(value = { "/composition/submit/", "/composition/submit/{id}" }, params = { "add" })
    public String compositionAdd(@ModelAttribute("compositionInfo") Composition compositionInfo) {
        compositionService.addComposition(compositionInfo);
        return "redirect:/composition";
    }

    @PostMapping(value = "/composition/submit/{id}", params = { "edit" })
    public String compositionEdit(@ModelAttribute("compositionInfo") Composition compositionInfo,
            @PathVariable("id") int id) {
        compositionService.updateComposition(id, compositionInfo);
        return "redirect:/composition";
    }

    @PostMapping(value = "/composition/submit/{id}", params = { "delete" })
    public String compositionDelete(@PathVariable("id") int id) {
        compositionService.deleteComposition(id);
        return "redirect:/composition";
    }

}