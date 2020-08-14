package com.nps.tacocloud.dao;

import com.nps.tacocloud.data.Ingredient;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by peishen.nie on 2020/7/15.
 */
public interface IngredientRepository{

    Iterable<Ingredient> findAll();

    Ingredient findOne(String id);

    Ingredient save(Ingredient ingredient);
}
