package fudan.se.lab4.dto;

import fudan.se.lab4.constant.InfoConstant;
import fudan.se.lab4.service.impl.MenuServiceImpl;
import fudan.se.lab4.util.LogUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

@ApiModel
public class OrderItem implements Serializable {
    private static final long serialVersionUID = -2451304424331432011L;

    @ApiModelProperty(notes = "order item name", required = true, dataType = "String")
    private String name;

    @ApiModelProperty(notes = "item size", required = true, dataType = "int")
    private int size;

    @ApiModelProperty(notes = "item size", required = true, dataType = "Ingredient")
    private List<Ingredient> ingredients;

    public OrderItem(String name, List<Ingredient> ingredients, int size) {
        this.name = name;
        this.ingredients = ingredients;
        this.size = size;
    }

    public OrderItem() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    private double size2price(int size) {
        int cupPrice = 0;
        switch (size) {
            case 1:
                cupPrice = 2;
                break;
            case 2:
                cupPrice = 4;
                break;
            case 3:
                cupPrice = 6;
                break;
            default:
                throw new RuntimeException(InfoConstant.CUP_SIZE_ERROR);
        }
        return cupPrice;
    }

    /**
     * The price of each orderItem consists of three parts,
     * the price of cup, the price of ingredients, the price of drink.
     *
     * @param currency the currency
     * @return the price
     */
    double cost(String currency) {
        double drinkPrice;
        assert ingredients != null : InfoConstant.INGREDIENTS_NULL;
        double ingredietnTotalPrice = ingredients.stream().map(ingredient -> {
            assert ingredient != null : InfoConstant.INGREDIENT_NULL;
            return MenuServiceImpl.getInstance().getPrice(currency, ingredient.getName()) * ingredient.getNumber();
        })
                .mapToDouble(Double::doubleValue)
                .sum();
        drinkPrice = MenuServiceImpl.getInstance().getPrice(currency, this.name) + size2price(getSize()) + ingredietnTotalPrice;
        return drinkPrice;
    }
}
