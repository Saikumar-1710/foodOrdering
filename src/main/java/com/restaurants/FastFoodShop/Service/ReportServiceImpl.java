package com.restaurants.FastFoodShop.Service;

import com.restaurants.FastFoodShop.Entity.Order;
import com.restaurants.FastFoodShop.Repository.OrderRepository;
import com.restaurants.FastFoodShop.Entity.OrderItem;
import com.restaurants.FastFoodShop.Repository.OrderItemRepository;
import com.restaurants.FastFoodShop.Entity.Expense;
import com.restaurants.FastFoodShop.Repository.ExpenseRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private ExpenseRepository expenseRepository;

    public ReportServiceImpl(
            OrderRepository orderRepository,
            OrderItemRepository orderItemRepository,
            ExpenseRepository expenseRepository) {

        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.expenseRepository = expenseRepository;
    }

    @Override
    public double getTotalSales(
            LocalDate fromDate,
            LocalDate toDate) {

        List<Order> orders =
                getOrdersBetweenDates(
                        fromDate,
                        toDate
                );

        double totalSales = 0.0;

        for (Order order : orders) {

            if ("COMPLETED".equalsIgnoreCase(
                    order.getStatus())) {

                totalSales +=
                        order.getTotalAmount();
            }
        }

        return totalSales;
    }

    @Override
    public long getNumberOfOrders(
            LocalDate fromDate,
            LocalDate toDate) {

        List<Order> orders =
                getOrdersBetweenDates(
                        fromDate,
                        toDate
                );

        long numberOfOrders = 0;

        for (Order order : orders) {

            if ("COMPLETED".equalsIgnoreCase(
                    order.getStatus())) {

                numberOfOrders++;
            }
        }

        return numberOfOrders;
    }

    private List<Order> getOrdersBetweenDates(
            LocalDate fromDate,
            LocalDate toDate) {

        LocalDateTime start =
                fromDate.atStartOfDay();

        LocalDateTime end =
                toDate.plusDays(1)
                        .atStartOfDay()
                        .minusNanos(1);

        return orderRepository
                .findByOrderDateBetween(
                        start,
                        end
                );
    }

    @Override
    public Map<String, Double> getFoodWiseSales(
            LocalDate fromDate,
            LocalDate toDate) {

        LocalDateTime start = fromDate.atStartOfDay();

        LocalDateTime end =
                toDate.plusDays(1)
                        .atStartOfDay()
                        .minusNanos(1);

        List<OrderItem> orderItems =
                orderItemRepository.findByOrderOrderDateBetween(
                        start,
                        end
                );

        Map<String, Double> foodSales =
                new LinkedHashMap<>();

        for (OrderItem item : orderItems) {

            if (!"COMPLETED".equalsIgnoreCase(
                    item.getOrder().getStatus())) {
                continue;
            }

            String foodName =
                    item.getFood().getName();

            double subtotal =
                    item.getSubtotal();

            foodSales.put(
                    foodName,
                    foodSales.getOrDefault(foodName, 0.0)
                            + subtotal
            );
        }

        return foodSales;
    }

    @Override
    public Map<String, Double> getCategoryWiseSales(
            LocalDate fromDate,
            LocalDate toDate) {

        LocalDateTime start =
                fromDate.atStartOfDay();

        LocalDateTime end =
                toDate.plusDays(1)
                        .atStartOfDay()
                        .minusNanos(1);

        List<OrderItem> orderItems =
                orderItemRepository.findByOrderOrderDateBetween(
                        start,
                        end
                );

        Map<String, Double> categorySales =
                new LinkedHashMap<>();

        for (OrderItem item : orderItems) {

            if (!"COMPLETED".equalsIgnoreCase(
                    item.getOrder().getStatus())) {
                continue;
            }

            String category =
                    item.getFood().getCategory();

            double subtotal =
                    item.getSubtotal();

            categorySales.put(
                    category,
                    categorySales.getOrDefault(category, 0.0)
                            + subtotal
            );
        }

        return categorySales;
    }

    @Override
    public String getBestSellingFood(
            LocalDate fromDate,
            LocalDate toDate) {

        LocalDateTime start =
                fromDate.atStartOfDay();

        LocalDateTime end =
                toDate.plusDays(1)
                        .atStartOfDay()
                        .minusNanos(1);

        List<OrderItem> orderItems =
                orderItemRepository.findByOrderOrderDateBetween(
                        start,
                        end
                );

        Map<String, Integer> foodQuantities =
                new LinkedHashMap<>();

        for (OrderItem item : orderItems) {

            if (!"COMPLETED".equalsIgnoreCase(
                    item.getOrder().getStatus())) {
                continue;
            }

            String foodName =
                    item.getFood().getName();

            Integer quantity =
                    item.getQuantity();

            foodQuantities.put(
                    foodName,
                    foodQuantities.getOrDefault(foodName, 0)
                            + quantity
            );
        }

        String bestSellingFood = "No sales";

        int highestQuantity = 0;

        for (Map.Entry<String, Integer> entry
                : foodQuantities.entrySet()) {

            if (entry.getValue() > highestQuantity) {

                highestQuantity = entry.getValue();

                bestSellingFood = entry.getKey();
            }
        }

        return bestSellingFood;
    }

    @Override
    public Map<String, Double> getMonthlySales(int year) {

        Map<String, Double> monthlySales =
                new LinkedHashMap<>();

        for (int month = 1; month <= 12; month++) {

            LocalDate firstDay =
                    LocalDate.of(year, month, 1);

            LocalDate lastDay =
                    firstDay.withDayOfMonth(
                            firstDay.lengthOfMonth()
                    );

            LocalDateTime start =
                    firstDay.atStartOfDay();

            LocalDateTime end =
                    lastDay.plusDays(1)
                            .atStartOfDay()
                            .minusNanos(1);

            List<OrderItem> orderItems =
                    orderItemRepository
                            .findByOrderOrderDateBetween(
                                    start,
                                    end
                            );

            double total = 0.0;

            for (OrderItem item : orderItems) {

                if (!"COMPLETED".equalsIgnoreCase(
                        item.getOrder().getStatus())) {
                    continue;
                }

                total += item.getSubtotal();
            }

            String monthName =
                    firstDay.getMonth()
                            .toString();

            monthlySales.put(
                    monthName,
                    total
            );
        }

        return monthlySales;
    }

    @Override
    public double getTotalExpenditure(
            LocalDate fromDate,
            LocalDate toDate) {

        List<Expense> expenses =
                expenseRepository.findByExpenseDateBetween(
                        fromDate,
                        toDate
                );

        double total = 0.0;

        for (Expense expense : expenses) {

            total += expense.getAmount();

        }

        return total;
    }

}