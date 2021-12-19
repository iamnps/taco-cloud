package com.nps.tacocloud.domain;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;


/**
 * Created by peishen.nie on 2020/7/13.
 */

@Entity
public class Taco {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Date crtAt;
    
    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;

    @ManyToMany(targetEntity = Ingredient.class)
    @Size(min = 1, message = "You must choose at least 1 ingredient")
    private List<String> ingredients;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCrtAt() {
        return crtAt;
    }

    public void setCrtAt(Date crtAt) {
        this.crtAt = crtAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }


}
