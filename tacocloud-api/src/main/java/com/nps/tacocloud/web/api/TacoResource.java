package com.nps.tacocloud.web.api;

import com.nps.tacocloud.domain.Taco;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import java.util.Date;
import java.util.List;

@Relation(value = "taco", collectionRelation = "tacos")//可以改变返回的json的data名
public class TacoResource extends ResourceSupport {

    private static final IngredientResourceAssembler ingredientResourceAssembler = new IngredientResourceAssembler();

    private final String name;

    private final Date createAt;

    private final List<IngredientResource> ingredients;

    public TacoResource(Taco taco){
        this.name = taco.getName();
        this.createAt = taco.getCrtAt();
        this.ingredients = ingredientResourceAssembler.toResources(taco.getIngredients());
    }

    public String getName() {
        return name;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public List<IngredientResource> getIngredients() {
        return ingredients;
    }

}
