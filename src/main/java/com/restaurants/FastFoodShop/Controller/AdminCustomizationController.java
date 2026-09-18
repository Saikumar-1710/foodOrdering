package com.restaurants.FastFoodShop.Controller;

import com.restaurants.FastFoodShop.Entity.CustomizationOption;
import com.restaurants.FastFoodShop.Entity.Food;
import com.restaurants.FastFoodShop.Service.CustomizationOptionService;
import com.restaurants.FastFoodShop.Service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/customizations")
public class AdminCustomizationController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private CustomizationOptionService customizationOptionService;

    @GetMapping("/food/{foodId}")
    public String getCustomizationsForFood(@PathVariable Long foodId, Model model) {
        Food food = foodService.findFoodById(foodId)
                .orElseThrow(() -> new RuntimeException("Food not found with ID: " + foodId));

        List<CustomizationOption> options = customizationOptionService.getOptionByFood(foodId);
        model.addAttribute("food", food);
        model.addAttribute("options", options);
        return "admin/customizations";
    }

    @PostMapping("/add")
    public String addCustomization(@RequestParam Long foodId, @ModelAttribute CustomizationOption option) {
        Food food = foodService.findFoodById(foodId)
                .orElseThrow(() -> new RuntimeException("Food not found with ID: " + foodId));
        
        option.setFood(food);
        customizationOptionService.saveOption(option);
        return "redirect:/admin/customizations/food/" + foodId;
    }
}