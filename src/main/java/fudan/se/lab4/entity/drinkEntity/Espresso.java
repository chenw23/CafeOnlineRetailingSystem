package fudan.se.lab4.entity.drinkEntity;

import fudan.se.lab4.constant.InfoConstant;
import fudan.se.lab4.dto.Ingredient;

import java.util.List;

public class Espresso extends Coffee {
    public Espresso() {
        setPrice(20);
        setName(InfoConstant.NAME_ESPRESSO);
    }

    public Espresso(List<Ingredient> ingredients, int size) {
        super(ingredients, size);
        setPrice(20);
        setName(InfoConstant.NAME_ESPRESSO);
    }
}
