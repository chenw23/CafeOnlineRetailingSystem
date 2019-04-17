package fudan.se.lab4.entity.ingredientEntity;

import fudan.se.lab4.constant.InfoConstant;
import fudan.se.lab4.dto.Ingredient;

public class Sugar extends Ingredient {
    public Sugar(int number){
        super(number);
        price = 1.0;
        setName(InfoConstant.NAME_SUGAR);
    }
}
