package fudan.se.lab4.service;

import fudan.se.lab4.constant.InfoConstant;
import fudan.se.lab4.dto.Ingredient;
import fudan.se.lab4.dto.Order;
import fudan.se.lab4.dto.OrderItem;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Date: 2019/5/18 12:44
 *
 * @author jiaxing liu
 */
public class TestHelper {

    /**
     * offer a new order with a random ID
     *
     * @return a new order with an id generated with the date
     */
    public static Order getOrder(String currency, ArrayList<OrderItem> orderItems) {
        return new Order(currency, orderItems);
    }

    /**
     * offer an array list of some ingredients
     *
     * @return a easy array list of ingredient
     */
    public static ArrayList<Ingredient> getIngredients() {
        //rmb:4.6 hkd:5.4
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient(InfoConstant.NAME_MILK, 1));
        ingredients.add(new Ingredient(InfoConstant.NAME_CHOCOLATE, 1));
        ingredients.add(new Ingredient(InfoConstant.NAME_SUGAR, 1));
        ingredients.add(new Ingredient(InfoConstant.NAME_CREAM, 1));
        return ingredients;
    }
}
