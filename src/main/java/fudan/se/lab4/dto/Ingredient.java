package fudan.se.lab4.dto;

import fudan.se.lab4.constant.InfoConstant;

import java.io.Serializable;

/**
 * Each class you implement should represent an ingredient;
 * The ingredients include milk, chocolate, cream and Sugar;
 * The classes should extend the parent class in package dto named Ingredient;
 * The constructor of the concrete classes should well define the name, price and other attributes of the ingredient.
 * Note:
 * The dto package defines the classes prototypes that will be used to transmit data between the front end and the back end.
 * Therefore, any concrete classes should created in this section should not lie in the dto package
 */
public class Ingredient implements Serializable {
    private static final long serialVersionUID = 7600387145905184435L;
    private String name;
    private int number;

    public Ingredient(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public Ingredient() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        assert number >= 0 : InfoConstant.INGREDIENT_NUMBER_ERROR;
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
