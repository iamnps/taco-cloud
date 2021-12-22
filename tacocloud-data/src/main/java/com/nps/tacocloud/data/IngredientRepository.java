package com.nps.tacocloud.data;

import com.nps.tacocloud.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by peishen.nie on 2020/7/15.
 */
public interface IngredientRepository extends CrudRepository<Ingredient, String> {

}
