package fudan.se.lab4.entity.drinkEntity;

import fudan.se.lab4.constant.InfoConstant;
import fudan.se.lab4.dto.Ingredient;

import java.util.List;

public class RedTea extends Tea {
    public RedTea() {
        setName(InfoConstant.NAME_REDTEA);
    }

    public RedTea(List<Ingredient> ingredients, int size) {
        super(ingredients, size);
        setName(InfoConstant.NAME_REDTEA);
    }
}
