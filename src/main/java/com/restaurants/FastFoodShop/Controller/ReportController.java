package com.restaurants.FastFoodShop.Controller;

import com.restaurants.FastFoodShop.Service.ReportService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Map;

@Controller
@RequestMapping("/admin/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(
            ReportService reportService) {

        this.reportService = reportService;
    }

    @GetMapping
    public String reports(
            @RequestParam(required = false)
            String fromDate,

            @RequestParam(required = false)
            String toDate,

            Model model) {

        LocalDate from;

        LocalDate to;

        if (fromDate == null || fromDate.isBlank()) {

            from = LocalDate.now()
                    .withDayOfMonth(1);

        } else {

            from = LocalDate.parse(fromDate);
        }

        if (toDate == null || toDate.isBlank()) {

            to = LocalDate.now();

        } else {

            to = LocalDate.parse(toDate);
        }

        if (from.isAfter(to)) {

            model.addAttribute(
                    "error",
                    "From Date cannot be later than To Date."
            );

            model.addAttribute(
                    "fromDate",
                    from
            );

            model.addAttribute(
                    "toDate",
                    to
            );

            return "admin/reports";
        }

        double totalSales =
                reportService.getTotalSales(
                        from,
                        to
                );

        long numberOfOrders =
                reportService.getNumberOfOrders(
                        from,
                        to
                );

        Map<String, Double> foodWiseSales =
                reportService.getFoodWiseSales(from, to);

        Map<String, Double> categoryWiseSales =
                reportService.getCategoryWiseSales(from, to);

        String bestSellingFood =
                reportService.getBestSellingFood(from, to);

        int selectedYear = from.getYear();

        Map<String, Double> monthlySales =
                reportService.getMonthlySales(selectedYear);
        double totalExpenditure =
                reportService.getTotalExpenditure(from, to);

        model.addAttribute(
                "fromDate",
                from
        );

        model.addAttribute(
                "toDate",
                to
        );

        model.addAttribute(
                "totalSales",
                totalSales
        );

        model.addAttribute(
                "numberOfOrders",
                numberOfOrders
        );

        model.addAttribute(
                "foodWiseSales",
                foodWiseSales
        );

        model.addAttribute(
                "categoryWiseSales",
                categoryWiseSales
        );

        model.addAttribute(
                "bestSellingFood",
                bestSellingFood
        );

        model.addAttribute(
                "monthlySales",
                monthlySales
        );

        model.addAttribute(
                "totalExpenditure",
                totalExpenditure
        );

        return "admin/reports";
    }
}