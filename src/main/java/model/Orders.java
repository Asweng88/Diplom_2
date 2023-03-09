package model;

import java.util.ArrayList;
import java.util.List;

public class Orders {

    private Object[] ingredients;



    public Orders(Object[] ingredients){
        this.ingredients=ingredients;
    }

    public Object[] getIngredient() {
        return ingredients;
    }

    public void setIngredient1(Object[] ingredient) {
        this.ingredients = ingredient;
    }

}
