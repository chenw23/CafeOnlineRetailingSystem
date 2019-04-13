package fudan.se.lab4.dto;

import fudan.se.lab4.entity.drinkEntity.Coffee;
import fudan.se.lab4.entity.drinkEntity.Tea;

import java.io.Serializable;
import java.util.List;

public class OrderItem implements Serializable {
    private static final long serialVersionUID = -2451304424331432011L;

    private String name;
    private int size;
    private List<Ingredient> ingredients;
    private double price;

    public OrderItem(List<Ingredient> ingredients, int size) {
        this.name = this.getClass().getName();
        this.ingredients = ingredients;
        this.size = size;
    }

    public OrderItem() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    private double size2price(int size){
        int cupPrice = 0;
        switch(size){
            case 1:
                cupPrice = 2;
                break;
            case 2:
                cupPrice = 4;
                break;
            case 3:
                if(this instanceof Coffee){
                    cupPrice = 6;
                }else if(this instanceof Tea){
                    cupPrice = 5;
                }
                break;
            default:
                throw new RuntimeException("Tea size error");
        }
        return cupPrice;

    }

    public double cost(){
        double drinkPrice;
        drinkPrice = getPrice() + size2price(getSize());
        return drinkPrice;
    }

}
