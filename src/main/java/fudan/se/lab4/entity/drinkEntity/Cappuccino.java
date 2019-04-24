package fudan.se.lab4.entity.drinkEntity;

import fudan.se.lab4.constant.InfoConstant;
import fudan.se.lab4.dto.Ingredient;

import java.util.List;

public class Cappuccino extends Coffee {
    public Cappuccino() {
        setName(InfoConstant.NAME_CAPPUCCINO);
    }

    public Cappuccino(List<Ingredient> ingredients, int size) {
        super(ingredients, size);
        setName(InfoConstant.NAME_CAPPUCCINO);
    }
}
