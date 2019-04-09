package fudan.se.lab4.dto;

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
}
