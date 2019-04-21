package fudan.se.lab4.service.impl;

import fudan.se.lab4.constant.InfoConstant;
import fudan.se.lab4.dto.Ingredient;
import fudan.se.lab4.dto.Order;
import fudan.se.lab4.dto.OrderItem;
import fudan.se.lab4.dto.PaymentInfo;
import fudan.se.lab4.entity.drinkEntity.Cappuccino;
import fudan.se.lab4.entity.drinkEntity.Espresso;
import fudan.se.lab4.entity.drinkEntity.GreenTea;
import fudan.se.lab4.entity.drinkEntity.RedTea;
import fudan.se.lab4.service.OrderService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


public class OrderServiceImplTest {
    private ArrayList<Ingredient> ingredients;
    private OrderServiceImpl orderService;
    @Before
    public void setUp() {
        ingredients = getIngredients();
        orderService = new OrderServiceImpl();
    }

    @After
    public void tearDown() {
        ingredients = null;
        orderService = null;
    }

    /**
     * check the method pay when the order is null
     */
    @Test
    public void testPayWithOrderNull() {
        try {
            PaymentInfo paymentInfo = orderService.pay(null);
        }catch (AssertionError e){
            assertEquals(e.getMessage(),InfoConstant.ORDER_NULL);
        }
    }

    /**
     * check the method pay when the order items are null
     */
    @Test
    public void testPayWithOrderItemsNull() {
        try {
            orderService.pay(getOrder(null));
        }catch (AssertionError e){
            assertEquals(e.getMessage(),InfoConstant.ORDER_ITEMS_NULL);
        }
    }

    /**
     * check the method pay when one of the order items is null
     */
    @Test
    public void testPayWithOrderItemNull() {
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(new GreenTea(ingredients, 2));
        orderItems.add(null);
        try {
            orderService.pay(getOrder(orderItems));
        }catch (AssertionError e){
            assertEquals(e.getMessage(),InfoConstant.ORDER_ITEM_NULL);
        }
    }

    /**
     * check the method pay when the ingredients are null
     */
    @Test
    public void testPayWithIngredientsNull() {
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(new GreenTea(null, 2));
        try {
            orderService.pay(getOrder(orderItems));
        }catch (AssertionError e){
            assertEquals(e.getMessage(),InfoConstant.INGREDIENTS_NULL);
        }
    }

    /**
     * check the method pay when one of the ingredients is null
     */
    @Test
    public void testPayWithIngredientNull() {
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient(InfoConstant.NAME_SUGAR,2));
        ingredients.add(null);
        orderItems.add(new GreenTea(ingredients, 2));
        try {
            orderService.pay(getOrder(orderItems));
        }catch (AssertionError e){
            assertEquals(e.getMessage(),InfoConstant.INGREDIENT_NULL);
        }
    }

    /**
     * check the method pay when one of the ingredients is of illegal number
     */
    @Test
    public void testPayWithIngredientSizeError() {
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient(InfoConstant.NAME_SUGAR,-1));
        orderItems.add(new GreenTea(ingredients, 2));
        try {
            orderService.pay(getOrder(orderItems));
        }catch (AssertionError e){
            assertEquals(e.getMessage(),InfoConstant.INGREDIENT_NUMBER_ERROR);
        }
    }

    /**
     * check the method pay when one of the ingredients is of null name.
     */
    @Test
    public void testPayWithIngredientNameNull() {
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient(InfoConstant.NAME_SUGAR,2));
        ingredients.add(new Ingredient(null,2));
        orderItems.add(new GreenTea(ingredients, 2));
        try {
            orderService.pay(getOrder(orderItems));
        }catch (AssertionError e){
            assertEquals(e.getMessage(),InfoConstant.INGREDIENT_NAME_ILLEGAL);
        }
    }

    /**
     * check the method pay when one of the ingredients is of illegal name.
     */
    @Test
    public void testPayWithIngredientOtherName() {
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient(InfoConstant.NAME_SUGAR,2));
        ingredients.add(new Ingredient("Othername",2));
        orderItems.add(new GreenTea(ingredients, 2));
        try {
            orderService.pay(getOrder(orderItems));
        }catch (AssertionError e){
            assertEquals(e.getMessage(),InfoConstant.INGREDIENT_NAME_ILLEGAL);
        }
    }

    /**
     * check the method pay when we should choose fullReduction
     */
    @Test
    public void testPayWithFullReduction() {
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(new GreenTea(ingredients, 2));
        orderItems.add(new Espresso(ingredients, 3));
        orderItems.add(new Cappuccino(ingredients, 3));
        orderItems.add(new RedTea(ingredients, 3));
        orderItems.add(new RedTea(ingredients, 1));
        PaymentInfo paymentInfo = orderService.pay(getOrder(orderItems));
        assertEquals(paymentInfo.getDiscount(), orderService.fullReduction(getOrder(orderItems).getTotalPrice()), 0.01);
    }

    /**
     * chech the method pay when we should choose combination discount
     */
    @Test
    public void testPayWithCombination() {
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(new Cappuccino(ingredients, 3));
        orderItems.add(new Cappuccino(ingredients, 3));
        PaymentInfo paymentInfo = orderService.pay(getOrder(orderItems));
        assertEquals(paymentInfo.getDiscount(), orderService.combination(getOrder(orderItems), getMsg()), 0.01);
    }

    /**
     * check the method discountOfLargeEspresso when there
     * are three large cups of  Espresso
     */
    @Test
    public void testDiscountOfLargeEspresso() {
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(new Espresso(ingredients, 2));
        orderItems.add(new Espresso(ingredients, 3));
        orderItems.add(new Cappuccino(ingredients, 3));
        orderItems.add(new RedTea(ingredients, 3));
        orderItems.add(new Espresso(ingredients, 3));
        orderItems.add(new Espresso(ingredients, 3));
        assertEquals(orderService.discountOfLargeEspresso(getOrder(orderItems), getMsg()), 8.0, 0.01);
    }

    /**
     * check the method discountOfLargeEspresso when there
     * are three large cups of  Espresso
     */
    @Test
    public void testDiscountOfCappuccino() {
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(new Espresso(ingredients, 3));
        orderItems.add(new Cappuccino(ingredients, 3));
        orderItems.add(new RedTea(ingredients, 3));
        orderItems.add(new Cappuccino(ingredients, 3));
        orderItems.add(new Cappuccino(ingredients, 1));
        assertEquals(orderService.discountOfCappuccino(getOrder(orderItems), getMsg()), 11.0, 0.01);
    }

    /**
     * check the method discountOfLargeEspresso when there
     * are three large cups of  Espresso
     */
    @Test
    public void testFullReduction() {
        assertEquals(orderService.fullReduction(160), 30.0, 0.01);
    }


    /**
     * check the method discountOfTea when there are two cups
     * of RedTea and two cups of GreenTea
     */
    @Test
    public void testDiscountOfTea() {
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(new Espresso(ingredients, 2));
        orderItems.add(new GreenTea(ingredients, 3));
        orderItems.add(new GreenTea(ingredients, 3));
        orderItems.add(new RedTea(ingredients, 3));
        orderItems.add(new RedTea(ingredients, 3));
        orderItems.add(new Cappuccino(ingredients, 3));
        assertEquals(orderService.discountOfTea(getOrder(orderItems), getMsg()), 18.0, 0.01);
    }

    /**
     * offer a new order with a random ID
     *
     * @param orderItems
     * @return
     */
    private Order getOrder(ArrayList<OrderItem> orderItems) {
        String orderID = "ID" + (int) (Math.random() * 10000);
        return new Order(orderID, orderItems);
    }

    /**
     * offer an array list of some ingredients
     *
     * @return
     */
    private ArrayList<Ingredient> getIngredients() {
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient(InfoConstant.NAME_MILK, 1));
        ingredients.add(new Ingredient(InfoConstant.NAME_CHOCOLATE, 1));
        ingredients.add(new Ingredient(InfoConstant.NAME_SUGAR, 1));
        ingredients.add(new Ingredient(InfoConstant.NAME_CREAM, 1));
        return ingredients;
    }

    /**
     * offer an array list of some message
     *
     * @return
     */
    private ArrayList<String> getMsg() {
        return new ArrayList<>();
    }
}