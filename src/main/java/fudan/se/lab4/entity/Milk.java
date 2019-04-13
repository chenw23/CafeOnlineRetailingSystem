package fudan.se.lab4.entity;

import fudan.se.lab4.constant.IngredientEnum;
import fudan.se.lab4.dto.Ingredient;

public class Milk extends Ingredient {

    public Milk(int number){
        super(IngredientEnum.milk,number);
        price = 1.2;
    }



}
