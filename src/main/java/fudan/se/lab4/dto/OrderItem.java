package fudan.se.lab4.dto;

import fudan.se.lab4.constant.InfoConstant;

import java.io.Serializable;
import java.util.List;

public class OrderItem implements Serializable {
    private static final long serialVersionUID = -2451304424331432011L;

    private String name;
    private int size;
    private List<Ingredient> ingredients;

    public OrderItem(String name, List<Ingredient> ingredients, int size) {
        this.name = name;
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

    private double size2price(int size) {
        int cupPrice = 0;
        switch (size) {
            case 1:
                cupPrice = 2;
                break;
            case 2:
                cupPrice = 4;
                break;
            case 3:
                if (Menu.isTea(this.getName())) {
                    cupPrice = 5;
                } else {
                    cupPrice = 6;
                }
                break;
            default:
                throw new RuntimeException(InfoConstant.CUP_SIZE_ERROR);
        }
        return cupPrice;
    }

    double cost() {
        double drinkPrice;
        drinkPrice = Menu.getValue(this.name) + size2price(getSize());
        return drinkPrice;
    }
}
