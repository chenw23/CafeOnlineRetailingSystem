package fudan.se.lab4.entity.drinkEntity;

import fudan.se.lab4.dto.Ingredient;

import java.util.List;

public class Espresso extends Coffee {
    public Espresso() {
        setPrice(20);
    }
    public Espresso(List<Ingredient> ingredients, int size) {
        super(ingredients,size);
        setPrice(20);
    }
}
