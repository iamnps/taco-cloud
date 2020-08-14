package com.nps.tacocloud.dao;

import com.nps.tacocloud.data.Ingredient;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by peishen.nie on 2020/7/22.
 */
public interface IngredientARepository extends CrudRepository<Ingredient, String> {
}
