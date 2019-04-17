package fudan.se.lab4.entity.ingredientEntity;

import fudan.se.lab4.constant.InfoConstant;
import fudan.se.lab4.dto.Ingredient;

public class Milk extends Ingredient {

    public Milk(int number){
        super(number);
        price = 1.2;
        setName(InfoConstant.NAME_MILK);
    }



}
