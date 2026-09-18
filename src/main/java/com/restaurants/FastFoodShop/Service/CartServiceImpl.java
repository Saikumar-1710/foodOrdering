package com.restaurants.FastFoodShop.Service;

import com.restaurants.FastFoodShop.Entity.Cart;
import com.restaurants.FastFoodShop.Entity.CartItem;
import com.restaurants.FastFoodShop.Entity.CartItemOption;
import com.restaurants.FastFoodShop.Entity.CustomizationOption;
import com.restaurants.FastFoodShop.Entity.Food;
import com.restaurants.FastFoodShop.Entity.User;
import com.restaurants.FastFoodShop.Repository.CartRepository;
import com.restaurants.FastFoodShop.Repository.CustomizationOptionRepository;
import com.restaurants.FastFoodShop.Repository.FoodRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private CustomizationOptionRepository optionRepository;

    @Override
    public Cart getCart(User user) {
        if (user == null || user.getId() == null) {
            return null;
        }

        return cartRepository.findByUserId(user.getId())
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUserId(user.getId());
                    newCart.setTotalPrice(0.0);
                    return cartRepository.save(newCart);
                });
    }

    @Override
    @Transactional
    public void addToCart(User user, Integer foodId, Food food) {

        Cart cart = getCart(user);

        if (cart == null || food == null) {
            return;
        }

        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setFood(food);
        cartItem.setQuantity(1);
        cartItem.setUnitPrice(food.getPrice());

        cartItem.setCalories(food.getCalories());
        cartItem.setProtein(food.getProtein());
        cartItem.setCarbohydrates(food.getCarbohydrates());
        cartItem.setFats(food.getFats());
        cartItem.setFiber(food.getFiber());
        cartItem.setMagnesium(food.getMagnesium());

        cart.getItems().add(cartItem);

        calculateCartTotal(cart);

        cartRepository.save(cart);
    }

    @Override
    @Transactional
    public void addToCart(
            User user,
            Integer foodId,
            List<Integer> optionIds) {

        Cart cart = getCart(user);

        if (cart == null || foodId == null) {
            return;
        }

        Food food = foodRepository.findById(foodId.longValue()).orElse(null);

        if (food == null) {
            return;
        }

        CartItem cartItem = new CartItem();

        cartItem.setCart(cart);
        cartItem.setFood(food);
        cartItem.setQuantity(1);

        double price = food.getPrice();

        cartItem.setCalories(food.getCalories());
        cartItem.setProtein(food.getProtein());
        cartItem.setCarbohydrates(food.getCarbohydrates());
        cartItem.setFats(food.getFats());
        cartItem.setFiber(food.getFiber());
        cartItem.setMagnesium(food.getMagnesium());

        List<CartItemOption> itemOptions = new ArrayList<>();

        if (optionIds != null) {

            for (Integer optionId : optionIds) {

                if (optionId == null) {
                    continue;
                }

                CustomizationOption option =
                        optionRepository.findById(optionId.longValue()).orElse(null);

                if (option != null) {

                    CartItemOption itemOption = new CartItemOption();

                    itemOption.setCartItem(cartItem);
                    itemOption.setOption(option);

                    itemOptions.add(itemOption);

                    price += option.getPriceAdjustment();
                }
            }
        }

        cartItem.setUnitPrice(price);
        cartItem.setSelectedOptions(itemOptions);

        cart.getItems().add(cartItem);

        calculateCartTotal(cart);

        cartRepository.save(cart);
    }

    @Override
    @Transactional
    public void removeCartItem(User user, Integer cartItemId) {

        Cart cart = getCart(user);

        if (cart == null || cartItemId == null) {
            return;
        }

        cart.getItems().removeIf(item ->
                item.getId() != null &&
                item.getId().equals(cartItemId.longValue())
        );

        calculateCartTotal(cart);

        cartRepository.save(cart);
    }

    @Override
    @Transactional
    public void clearCart(User user) {

        Cart cart = getCart(user);

        if (cart == null) {
            return;
        }

        cart.getItems().clear();
        cart.setTotalPrice(0.0);

        cartRepository.save(cart);
    }

    @Override
    @Transactional
    public CartItemUpdate updateQuantity(
            User user,
            Integer cartItemId,
            int quantity) {

        Cart cart = getCart(user);

        if (cart == null || cartItemId == null) {
            return new CartItemUpdate(
                    0,
                    0.0,
                    0.0,
                    0.0,
                    0.0
            );
        }

        CartItem selectedItem = null;

        for (CartItem item : cart.getItems()) {

            if (item.getId() != null &&
                    item.getId().equals(cartItemId.longValue())) {

                selectedItem = item;
                break;
            }
        }

        if (selectedItem == null) {
            return new CartItemUpdate(
                    0,
                    0.0,
                    calculateSubtotal(cart),
                    calculateDiscount(user, calculateSubtotal(cart)),
                    calculateFinalAmount(user, cart)
            );
        }

        if (quantity <= 0) {
            cart.getItems().remove(selectedItem);
        } else {
            selectedItem.setQuantity(quantity);
        }

        calculateCartTotal(cart);

        cartRepository.save(cart);

        double subtotal = calculateSubtotal(cart);
        double discount = calculateDiscount(user, subtotal);
        double finalAmount = subtotal - discount;

        return new CartItemUpdate(
                quantity,
                selectedItem.getSubtotal(),
                subtotal,
                discount,
                finalAmount
        );
    }

    @Override
    public double calculateSubtotal(Cart cart) {

        if (cart == null || cart.getItems() == null) {
            return 0.0;
        }

        double subtotal = 0.0;

        for (CartItem item : cart.getItems()) {
            subtotal += item.getSubtotal();
        }

        return subtotal;
    }

    @Override
    public double calculateDiscount(User user, double subtotal) {

        if (user != null && user.isPrime()) {
            return subtotal * 0.10;
        }

        return 0.0;
    }

    @Override
    public double calculateFinalAmount(User user, Cart cart) {

        double subtotal = calculateSubtotal(cart);
        double discount = calculateDiscount(user, subtotal);

        return subtotal - discount;
    }

    private void calculateCartTotal(Cart cart) {

        if (cart == null) {
            return;
        }

        double total = calculateSubtotal(cart);
        cart.setTotalPrice(total);
    }
}