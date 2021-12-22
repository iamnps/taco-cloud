package com.nps.tacocloud.web.api;

import com.nps.tacocloud.domain.Ingredient;
import org.springframework.hateoas.ResourceSupport;

public class IngredientResource extends ResourceSupport {

    private String name;

    private String type;

    public IngredientResource(Ingredient ingredient){
        this.name = ingredient.getName();
        this.type = ingredient.getType();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
