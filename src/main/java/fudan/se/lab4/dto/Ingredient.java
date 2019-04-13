package fudan.se.lab4.dto;

import java.io.Serializable;

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
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
