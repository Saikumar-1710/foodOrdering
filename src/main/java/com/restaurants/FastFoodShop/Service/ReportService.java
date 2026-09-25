package com.restaurants.FastFoodShop.Service;

import java.time.LocalDate;
import java.util.Map;

public interface ReportService {

    double getTotalSales(
            LocalDate fromDate,
            LocalDate toDate
    );

    long getNumberOfOrders(
            LocalDate fromDate,
            LocalDate toDate
    );

    Map<String, Double> getFoodWiseSales(
            LocalDate fromDate,
            LocalDate toDate
    );

    Map<String, Double> getCategoryWiseSales(
            LocalDate fromDate,
            LocalDate toDate
    );

    Map<String, Double> getMonthlySales(
            int year
    );

    String getBestSellingFood(
            LocalDate fromDate,
            LocalDate toDate
    );

    double getTotalExpenditure(
            LocalDate fromDate,
            LocalDate toDate
    );

}