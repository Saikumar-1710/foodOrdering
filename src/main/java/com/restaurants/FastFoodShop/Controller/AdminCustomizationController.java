package com.restaurants.FastFoodShop.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.restaurants.FastFoodShop.Entity.CustomizationOption;
import com.restaurants.FastFoodShop.Entity.Food;
import com.restaurants.FastFoodShop.Service.CustomizationOptionService;
import com.restaurants.FastFoodShop.Service.FoodService;

@Controller
@RequestMapping("/admin/customizations")
public class AdminCustomizationController {

    private final CustomizationOptionService customizationService;

    private final FoodService foodService;

    public AdminCustomizationController(
            CustomizationOptionService customizationService,
            FoodService foodService) {

        this.customizationService =
                customizationService;

        this.foodService =
                foodService;
    }

    @GetMapping("/food/{foodId}")
    public String customizationList(
            @PathVariable Integer foodId,
            Model model) {

        Food food = foodService
                .getFoodById(foodId)
                .orElseThrow(
                        () -> new RuntimeException(
                                "Food Not Found"
                        )
                );

        List<CustomizationOption> options =
                customizationService
                        .getOptionByFood(foodId);

        model.addAttribute(
                "food",
                food
        );

        model.addAttribute(
                "options",
                options
        );

        return "admin/customization-list";
    }

    @GetMapping("/add/{foodId}")
    public String addCustomization(
            @PathVariable Integer foodId,
            Model model) {

        Food food = foodService
                .getFoodById(foodId)
                .orElseThrow(
                        () -> new RuntimeException(
                                "Food Not Found"
                        )
                );

        CustomizationOption option =
                new CustomizationOption();

        option.setFood(food);

        model.addAttribute(
                "food",
                food
        );

        model.addAttribute(
                "option",
                option
        );

        return "admin/add-customization";
    }

    @PostMapping("/save")
    public String saveCustomization(
            @ModelAttribute("option")
            CustomizationOption option) {

        customizationService.saveOption(option);

        return "redirect:/admin/customizations/food/"
                + option.getFood().getId();
    }
}