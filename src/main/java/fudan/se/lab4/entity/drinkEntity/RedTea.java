package fudan.se.lab4.entity.drinkEntity;

import fudan.se.lab4.constant.InfoConstant;
import fudan.se.lab4.dto.Ingredient;

import java.util.List;

public class RedTea extends Tea {
    public RedTea() {
        setPrice(18);
        setName(InfoConstant.NAME_REDTEA);
    }
    public RedTea(List<Ingredient> ingredients, int size) {
        super(ingredients,size);
        setPrice(18);
        setName(InfoConstant.NAME_REDTEA);
    }
}
