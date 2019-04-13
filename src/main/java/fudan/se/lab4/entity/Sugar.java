package fudan.se.lab4.entity;

import fudan.se.lab4.constant.IngredientEnum;
import fudan.se.lab4.dto.Ingredient;

public class Sugar extends Ingredient {
    public Sugar(int number){
        super(IngredientEnum.sugar,number);
        price = 1.0;
    }
}
