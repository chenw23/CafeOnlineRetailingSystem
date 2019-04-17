package fudan.se.lab4.service.impl;

import fudan.se.lab4.dto.Ingredient;
import fudan.se.lab4.dto.Order;
import fudan.se.lab4.dto.OrderItem;
import fudan.se.lab4.entity.drinkEntity.Cappuccino;
import fudan.se.lab4.entity.drinkEntity.Espresso;
import fudan.se.lab4.entity.drinkEntity.RedTea;
import fudan.se.lab4.entity.ingredientEntity.Chocolate;
import fudan.se.lab4.entity.ingredientEntity.Cream;
import fudan.se.lab4.entity.ingredientEntity.Milk;
import fudan.se.lab4.entity.ingredientEntity.Sugar;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class OrderServiceImplTest {

    @Test
    public void pay() {
    }

    /**
     * check the method discountOfLargeEspresso when there are three large cups of  Espresso
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
        assertEquals(orderService.dis,8);
    }

    public Order getOrder(ArrayList<OrderItem> orderItems){
        String orderID = "ID" + (int)(Math.random() * 10000);
        return new Order(orderID,orderItems);
    }

    public ArrayList<Ingredient> getIngredients(){
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Milk(1));
        ingredients.add(new Chocolate(1));
        ingredients.add(new Sugar(1));
        ingredients.add(new Cream(1));
        return ingredients;
    }
}