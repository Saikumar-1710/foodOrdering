package com.restaurants.FastFoodShop.Service;

import com.restaurants.FastFoodShop.Entity.*;
import com.restaurants.FastFoodShop.Repository.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final FoodRepository foodRepository;
    private final CustomizationOptionRepository optionRepository;

    public CartServiceImpl(
            CartRepository cartRepository,
            CartItemRepository cartItemRepository,
            FoodRepository foodRepository,
            CustomizationOptionRepository optionRepository) {

        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.foodRepository = foodRepository;
        this.optionRepository = optionRepository;
    }

    @Override
    @Transactional
    public Cart getCart(User user) {

        return cartRepository
                .findByUserId(user.getId())
                .orElseGet(() -> {

                    Cart cart = new Cart();

                    cart.setUser(user);

                    cart.setItems(new ArrayList<>());

                    return cartRepository.save(cart);
                });
    }

    @Override
    @Transactional
    public void addToCart(
            User user,
            Integer foodId,
            List<Integer> optionIds) {

        Food food = foodRepository
                .findById(foodId)
                .orElseThrow(
                        () -> new RuntimeException("Food Not Found")
                );

        Cart cart = getCart(user);

        CartItem cartItem = new CartItem();

        cartItem.setCart(cart);
        cartItem.setFood(food);
        cartItem.setQuantity(1);

        double price = food.getPrice();

        double calories = food.getCalories();
        double protein = food.getProtein();
        double carbohydrates = food.getCarbohydrates();
        double fats = food.getFats();
        double fiber = food.getFiber();
        double magnesium = food.getMagnesium();

        List<CartItemOption> selectedOptions =
                new ArrayList<>();

        if (optionIds != null) {

            for (Integer optionId : optionIds) {

                CustomizationOption option =
                        optionRepository
                                .findById(optionId)
                                .orElseThrow(
                                        () -> new RuntimeException(
                                                "Customization Option Not Found"
                                        )
                                );

                price += option.getPriceAdjustment();

                calories += option.getCalories();
                protein += option.getProtein();
                carbohydrates += option.getCarbohydrates();
                fats += option.getFats();
                fiber += option.getFiber();
                magnesium += option.getMagnesium();

                CartItemOption cartItemOption =
                        new CartItemOption();

                cartItemOption.setCartItem(cartItem);
                cartItemOption.setOption(option);

                selectedOptions.add(cartItemOption);
            }
        }

        cartItem.setUnitPrice(price);

        cartItem.setCalories(calories);
        cartItem.setProtein(protein);
        cartItem.setCarbohydrates(carbohydrates);
        cartItem.setFats(fats);
        cartItem.setFiber(fiber);
        cartItem.setMagnesium(magnesium);

        cartItem.setSelectedOptions(selectedOptions);

        cart.getItems().add(cartItem);

        cartRepository.save(cart);
    }

    @Override
    @Transactional
    public void removeCartItem(
            User user,
            Integer cartItemId) {

        Cart cart = getCart(user);

        cart.getItems()
                .removeIf(
                        item -> item.getId()
                                .equals(cartItemId)
                );

        cartRepository.save(cart);
    }

    @Override
    @Transactional
    public void clearCart(User user) {

        Cart cart = getCart(user);

        cart.getItems().clear();

        cartRepository.save(cart);
    }
}
