package fudan.se.lab4.entity.drinkEntity;

import fudan.se.lab4.dto.Ingredient;
import fudan.se.lab4.dto.OrderItem;

import java.util.List;

public abstract class Tea extends OrderItem {
    Tea(List<Ingredient> ingredients, int size) {
        super(ingredients, size);
    }

    Tea() {
    }
}
