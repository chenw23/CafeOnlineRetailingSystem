package fudan.se.lab4.entity.drinkEntity;

import fudan.se.lab4.dto.Ingredient;

import java.util.List;

public class Cappuccino extends Coffee {
    public Cappuccino() {
        setPrice(22);
    }
    public Cappuccino(List<Ingredient> ingredients, int size) {
        super(ingredients,size);
        setPrice(22);
    }
}
