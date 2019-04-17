package fudan.se.lab4.service.impl;

import fudan.se.lab4.dto.Ingredient;
import fudan.se.lab4.dto.Order;
import fudan.se.lab4.dto.OrderItem;
import fudan.se.lab4.dto.PaymentInfo;
import fudan.se.lab4.entity.drinkEntity.Cappuccino;
import fudan.se.lab4.entity.drinkEntity.Espresso;
import fudan.se.lab4.entity.drinkEntity.GreenTea;
import fudan.se.lab4.entity.drinkEntity.RedTea;
import fudan.se.lab4.entity.ingredientEntity.Chocolate;
import fudan.se.lab4.entity.ingredientEntity.Cream;
import fudan.se.lab4.entity.ingredientEntity.Milk;
import fudan.se.lab4.entity.ingredientEntity.Sugar;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class OrderServiceImplTest {
    /**
     * chech the method pay when we should choose fullReduction
     */
    @Test
    public void testPayWithFullReduction() {
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(new GreenTea(getIngredients(),2));
        orderItems.add(new Espresso(getIngredients(),3));
        orderItems.add(new Cappuccino(getIngredients(),3));
        orderItems.add(new RedTea(getIngredients(),3));
        orderItems.add(new RedTea(getIngredients(),1));
        OrderServiceImpl orderService = new OrderServiceImpl();
        PaymentInfo paymentInfo = orderService.pay(getOrder(orderItems));
        assertEquals(paymentInfo.getDiscount(),orderService.fullReduction(getOrder(orderItems).getTotalPrice()),0.01);
    }

    /**
     * chech the method pay when we should choose combination discount
     */
    @Test
    public void testPayWithCombination() {
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(new Cappuccino(getIngredients(),3));
        orderItems.add(new Cappuccino(getIngredients(),3));
        OrderServiceImpl orderService = new OrderServiceImpl();
        PaymentInfo paymentInfo = orderService.pay(getOrder(orderItems));
        assertEquals(paymentInfo.getDiscount(),orderService.combination(getOrder(orderItems),getMsg()),0.01);
    }

    /**
     * check the method discountOfLargeEspresso when there
     * are three large cups of  Espresso
     */
    @Test
    public void testDiscountOfLargeEspresso(){
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(new Espresso(getIngredients(),2));
        orderItems.add(new Espresso(getIngredients(),3));
        orderItems.add(new Cappuccino(getIngredients(),3));
        orderItems.add(new RedTea(getIngredients(),3));
        orderItems.add(new Espresso(getIngredients(),3));
        orderItems.add(new Espresso(getIngredients(),3));
        OrderServiceImpl orderService = new OrderServiceImpl();
        assertEquals(orderService.discountOfLargeEspresso(getOrder(orderItems),getMsg()),8.0,0.01);
    }

    /**
     * check the method discountOfLargeEspresso when there
     * are three large cups of  Espresso
     */
    @Test
    public void testDiscountOfCappuccino(){
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(new Espresso(getIngredients(),3));
        orderItems.add(new Cappuccino(getIngredients(),3));
        orderItems.add(new RedTea(getIngredients(),3));
        orderItems.add(new Cappuccino(getIngredients(),3));
        orderItems.add(new Cappuccino(getIngredients(),1));
        OrderServiceImpl orderService = new OrderServiceImpl();
        assertEquals(orderService.discountOfCappuccino(getOrder(orderItems),getMsg()),11.0,0.01);
    }

    /**
     * check the method discountOfLargeEspresso when there
     * are three large cups of  Espresso
     */
    @Test
    public void testFullReduction(){
        OrderServiceImpl orderService = new OrderServiceImpl();
        assertEquals(orderService.fullReduction(160),30.0,0.01);
    }


    /**
     * check the method discountOfTea when there are two cups
     * of RedTea and two cups of GreenTea
     */
    @Test
    public void testDiscountOfTea(){
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(new Espresso(getIngredients(),2));
        orderItems.add(new GreenTea(getIngredients(),3));
        orderItems.add(new GreenTea(getIngredients(),3));
        orderItems.add(new RedTea(getIngredients(),3));
        orderItems.add(new RedTea(getIngredients(),3));
        orderItems.add(new Cappuccino(getIngredients(),3));
        OrderServiceImpl orderService = new OrderServiceImpl();
        assertEquals(orderService.discountOfTea(getOrder(orderItems),getMsg()),18.0,0.01);
    }

    /**
     * offer a new order with a random ID
     * @param orderItems
     * @return
     */
    private Order getOrder(ArrayList<OrderItem> orderItems){
        String orderID = "ID" + (int)(Math.random() * 10000);
        return new Order(orderID,orderItems);
    }

    /**
     * offer a arraylist of some ingredients
     * @return
     */
    private ArrayList<Ingredient> getIngredients(){
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Milk(1));
        ingredients.add(new Chocolate(1));
        ingredients.add(new Sugar(1));
        ingredients.add(new Cream(1));
        return ingredients;
    }

    /**
     * offer a arraylist of some message
     * @return
     */
    private ArrayList<String> getMsg(){
        return new ArrayList<>();
    }
}