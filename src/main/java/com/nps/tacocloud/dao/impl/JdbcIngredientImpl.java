package com.nps.tacocloud.dao.impl;

import com.nps.tacocloud.dao.IngredientRepository;
import com.nps.tacocloud.data.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by peishen.nie on 2020/7/15.
 */
@Repository
public class JdbcIngredientImpl implements IngredientRepository {

    private JdbcTemplate jdbc;


    @Autowired
    public JdbcIngredientImpl(JdbcTemplate jdbcTemplate){
        this.jdbc = jdbcTemplate;
    }


    @Override
    public Iterable<Ingredient> findAll() {
        return jdbc.query("select id, name, type from Ingredient", this::mapRowToIngredient);
    }

    @Override
    public Ingredient findOne(String id) {
        return jdbc.queryForObject("select id, name, type from Ingredient where id=?", this::mapRowToIngredient, id);
    }

    private Ingredient mapRowToIngredient(ResultSet rs, int rowNum) throws SQLException{
        return new Ingredient(rs.getString("id"), rs.getString("name"), Ingredient.Type.valueOf(rs.getString("type")));
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        jdbc.update("INSERT INTO Ingredient (id, name, type) VALUES (?, ?, ?)",
                ingredient.getId(), ingredient.getName(), ingredient.getType().toString());
        return ingredient;
    }
}
