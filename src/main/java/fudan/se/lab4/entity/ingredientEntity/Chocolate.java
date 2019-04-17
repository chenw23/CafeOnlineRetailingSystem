package fudan.se.lab4.entity.ingredientEntity;

import fudan.se.lab4.constant.InfoConstant;
import fudan.se.lab4.dto.Ingredient;

public class Chocolate extends Ingredient {
    public Chocolate(int number){
        super(number);
        setPrice(1.2);
        setName(InfoConstant.NAME_CHOCOLATE);
    }
}
