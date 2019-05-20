package fudan.se.lab4.service.impl;

import fudan.se.lab4.constant.InfoConstant;
import fudan.se.lab4.dto.Ingredient;
import fudan.se.lab4.dto.Order;
import fudan.se.lab4.dto.OrderItem;
import fudan.se.lab4.dto.PaymentInfo;
import fudan.se.lab4.service.MarketingStrategy;
import fudan.se.lab4.service.TestHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;

public class OrderServiceImplTest {
    private ArrayList<Ingredient> ingredients;
    private OrderServiceImpl orderService;
    private String defaultCurr;
    @Before
    public void setUp() {
        defaultCurr = "cny";
        ingredients = TestHelper.getIngredients();
        orderService = new OrderServiceImpl();
        ArrayList<MarketingStrategy> strategies = new ArrayList<>();
        strategies.add(new TeaAndCoffee15OffStrategy());
        strategies.add(new DoubleElevenStrategy());
        strategies.add(new FullDiscountStrategy());
        strategies.add(new CombinationDiscountStrategy());
        orderService.setStrategies(strategies);
    }

    @After
    public void tearDown() {
        ingredients = null;
        orderService = null;
        defaultCurr = null;
    }

    /**
     * check the method pay when the order is null
     */
    @Test
    public void testPayWithOrderNull() {
        try {
            PaymentInfo paymentInfo = orderService.pay(null);
        } catch (AssertionError e) {
            assertEquals(e.getMessage(), InfoConstant.ORDER_NULL);
        }
    }

    /**
     * check the method pay when the order items are null
     */
    @Test
    public void testPayWithOrderItemsNull() {
        try {
            orderService.pay(TestHelper.getOrder(defaultCurr,null));
        } catch (AssertionError e) {
            assertEquals(e.getMessage(), InfoConstant.ORDER_ITEMS_NULL);
        }
    }

    /**
     * check the method pay when the currency are null
     */
    @Test
    public void testPayWithCurrencyNull() {
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(new OrderItem(InfoConstant.NAME_GREENTEA, ingredients, 2));
        try {
            orderService.pay(TestHelper.getOrder(null,orderItems));
        } catch (AssertionError e) {
            assertEquals(e.getMessage(), InfoConstant.CURRENCY_ERROR);
        }
    }

    /**
     * check the method pay when one of the order items is null
     */
    @Test
    public void testPayWithOrderItemNull() {
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(new OrderItem(InfoConstant.NAME_GREENTEA, ingredients, 2));
        orderItems.add(null);
        try {
            orderService.pay(TestHelper.getOrder(defaultCurr,orderItems));
        } catch (AssertionError e) {
            assertEquals(e.getMessage(), InfoConstant.ORDER_ITEM_NULL);
        }
    }

    /**
     * check the method pay when the ingredients are null
     */
    @Test
    public void testPayWithIngredientsNull() {
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(new OrderItem(InfoConstant.NAME_GREENTEA, null, 2));
        try {
            orderService.pay(TestHelper.getOrder(defaultCurr,orderItems));
        } catch (AssertionError e) {
            assertEquals(e.getMessage(), InfoConstant.INGREDIENTS_NULL);
        }
    }

    /**
     * check the method pay when one of the ingredients is null
     */
    @Test
    public void testPayWithIngredientNull() {
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient(InfoConstant.NAME_SUGAR, 2));
        ingredients.add(null);
        orderItems.add(new OrderItem(InfoConstant.NAME_GREENTEA, ingredients, 2));
        try {
            orderService.pay(TestHelper.getOrder(defaultCurr,orderItems));
        } catch (AssertionError e) {
            assertEquals(e.getMessage(), InfoConstant.INGREDIENT_NULL);
        }
    }

    /**
     * check the method pay when one of the ingredients is of illegal number
     */
    @Test
    public void testPayWithIngredientSizeError() {
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient(InfoConstant.NAME_SUGAR, -1));
        orderItems.add(new OrderItem(InfoConstant.NAME_GREENTEA, ingredients, 2));
        try {
            orderService.pay(TestHelper.getOrder(defaultCurr,orderItems));
        } catch (AssertionError e) {
            assertEquals(e.getMessage(), InfoConstant.INGREDIENT_NUMBER_ERROR);
        }
    }

    /**
     * check the method pay when one of the ingredients is of null name.
     */
    @Test
    public void testPayWithIngredientNameNull() {
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient(InfoConstant.NAME_SUGAR, 2));
        ingredients.add(new Ingredient(null, 2));
        orderItems.add(new OrderItem(InfoConstant.NAME_GREENTEA, ingredients, 2));
        try {
            orderService.pay(TestHelper.getOrder(defaultCurr,orderItems));
        } catch (AssertionError e) {
            assertEquals(e.getMessage(), InfoConstant.MENUITEM_ERROR);
        }
    }

    /**
     * check the method pay when one of the ingredients is of illegal name.
     */
    @Test
    public void testPayWithIngredientOtherName() {
        String defaultCurr = "cny";
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient(InfoConstant.NAME_SUGAR, 2));
        ingredients.add(new Ingredient("OtherName", 2));
        orderItems.add(new OrderItem(InfoConstant.NAME_GREENTEA, ingredients, 2));
        try {
            orderService.pay(TestHelper.getOrder(defaultCurr,orderItems));
        } catch (AssertionError e) {
            assertEquals(e.getMessage(), InfoConstant.MENUITEM_ERROR);
        }
    }

    /**
     * check the method pay when we should choose fullReduction
     */
    @Test
    public void testPayWithFullReduction() {
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(new OrderItem(InfoConstant.NAME_GREENTEA, ingredients, 1));
        orderItems.add(new OrderItem(InfoConstant.NAME_ESPRESSO, ingredients, 3));
        orderItems.add(new OrderItem(InfoConstant.NAME_CAPPUCCINO, ingredients, 3));
        orderItems.add(new OrderItem(InfoConstant.NAME_REDTEA, ingredients, 3));
        orderItems.add(new OrderItem(InfoConstant.NAME_REDTEA, ingredients, 1));
        PaymentInfo paymentInfo = orderService.pay(TestHelper.getOrder(defaultCurr,orderItems));
        assertEquals(paymentInfo.getDiscount(), 30.0, 0.01);
    }

    /**
     * chech the method pay when we should choose combination discount
     */
    @Test
    public void testPayWithCombination() {
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(new OrderItem(InfoConstant.NAME_CAPPUCCINO, ingredients, 3));
        orderItems.add(new OrderItem(InfoConstant.NAME_CAPPUCCINO, ingredients, 3));
        PaymentInfo paymentInfo = orderService.pay(TestHelper.getOrder(defaultCurr,orderItems));
        assertEquals(paymentInfo.getDiscount(), 11.0, 0.01);
    }

    /**
     * check the method pay when there
     * are three large cups of Espresso
     */
    @Test
    public void testPayWithCombinationOfLargeEspresso() {
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(new OrderItem(InfoConstant.NAME_ESPRESSO, ingredients, 3));
        orderItems.add(new OrderItem(InfoConstant.NAME_ESPRESSO, ingredients, 3));
        orderItems.add(new OrderItem(InfoConstant.NAME_ESPRESSO, ingredients, 3));
        PaymentInfo paymentInfo = orderService.pay(TestHelper.getOrder(defaultCurr,orderItems));
        assertEquals(paymentInfo.getDiscount(), 8.0, 0.01);
    }

    /**
     * check the method pay when there
     * are two cups of Cappuccino
     */
    @Test
    public void testPayWithCombinationOfOrderItemCappuccino() {
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(new OrderItem(InfoConstant.NAME_CAPPUCCINO, ingredients, 3));
        orderItems.add(new OrderItem(InfoConstant.NAME_REDTEA, ingredients, 3));
        orderItems.add(new OrderItem(InfoConstant.NAME_CAPPUCCINO, ingredients, 1));
        PaymentInfo paymentInfo = orderService.pay(TestHelper.getOrder(defaultCurr,orderItems));
        assertEquals(paymentInfo.getDiscount(), 11.0, 0.01);
    }

    /**
     * check the method pay when there are two cups
     * of Red Tea and two cups of Green Tea
     */
    @Test
    public void testPayWithCombinationOfTea() {
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(new OrderItem(InfoConstant.NAME_GREENTEA, ingredients, 1));
        orderItems.add(new OrderItem(InfoConstant.NAME_GREENTEA, ingredients, 1));
        orderItems.add(new OrderItem(InfoConstant.NAME_REDTEA, ingredients, 1));
        orderItems.add(new OrderItem(InfoConstant.NAME_REDTEA, ingredients, 1));
        PaymentInfo paymentInfo = orderService.pay(TestHelper.getOrder(defaultCurr,orderItems));
        assertEquals(paymentInfo.getDiscount(), 18.0, 0.01);
    }

    /**
     * check the method pay when there are one cup
     * of Red Tea and one cup of Cappuccino
     */
    @Test
    public void testPayWithTeaAndCoffee15Off() {
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(new OrderItem(InfoConstant.NAME_GREENTEA, ingredients, 1));
        orderItems.add(new OrderItem(InfoConstant.NAME_CAPPUCCINO, ingredients, 3));
        PaymentInfo paymentInfo = orderService.pay(TestHelper.getOrder(defaultCurr,orderItems));
        assertEquals(paymentInfo.getDiscount(), 8.28, 0.01);
    }

}