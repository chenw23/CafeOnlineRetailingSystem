package fudan.se.lab4.entity;

import fudan.se.lab4.constant.IngredientEnum;
import fudan.se.lab4.dto.Ingredient;

public class Chocolate extends Ingredient {
    public Chocolate(int number){
        super(IngredientEnum.chocolate,number);
        price = 1.2;
    }
}
