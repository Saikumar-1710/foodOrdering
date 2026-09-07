package com.foodordering.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminPageController {

    @GetMapping("/admin/dashboard")
    public String dashboard() {
        return "admin/dashboard";
    }

    @GetMapping("/admin/foods")
    public String foods() {
        return "admin/food-list";
    }

    @GetMapping("/admin/foods/add")
    public String addFood() {
        return "admin/add-food";
    }
    
    @GetMapping("/admin/foods/view")
    public String viewFood() {
        return "admin/view-food";
    }
    
    @GetMapping("/admin/foods/edit")
    public String editFood() {
        return "admin/edit-food";
    }
}