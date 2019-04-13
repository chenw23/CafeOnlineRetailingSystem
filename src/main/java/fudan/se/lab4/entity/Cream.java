package fudan.se.lab4.entity;

import fudan.se.lab4.constant.IngredientEnum;
import fudan.se.lab4.dto.Ingredient;

public class Cream extends Ingredient {
    public Cream(int number){
        super(IngredientEnum.cream,number);
        price = 1.0;
    }
}
