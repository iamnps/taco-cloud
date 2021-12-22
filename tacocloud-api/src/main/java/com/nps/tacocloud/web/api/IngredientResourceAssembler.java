package com.nps.tacocloud.web.api;

import com.nps.tacocloud.domain.Ingredient;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class IngredientResourceAssembler extends ResourceAssemblerSupport<Ingredient, IngredientResource> {

    public IngredientResourceAssembler() {
        super(DesignTacoController.class, IngredientResource.class);
    }

    @Override
    public IngredientResource toResource(Ingredient ingredient) {
        return createResourceWithId(ingredient.getId(), ingredient);
    }

    @Override
    public IngredientResource instantiateResource(Ingredient ingredient){
        return new IngredientResource(ingredient);
    }
}
