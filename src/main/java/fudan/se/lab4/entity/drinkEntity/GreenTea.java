package fudan.se.lab4.entity.drinkEntity;

import fudan.se.lab4.constant.InfoConstant;
import fudan.se.lab4.dto.Ingredient;

import java.util.List;

public class GreenTea extends Tea {
    public GreenTea() {
        setPrice(16);
        setName(InfoConstant.NAME_GREENTEA);
    }
    public GreenTea(List<Ingredient> ingredients, int size) {
        super(ingredients,size);
        setPrice(16);
        setName(InfoConstant.NAME_GREENTEA);
    }
}
