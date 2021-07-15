package com.nps.tacocloud.dao.impl;

import com.nps.tacocloud.dao.TacoRepository;
import com.nps.tacocloud.data.Ingredient;
import com.nps.tacocloud.data.Order;
import com.nps.tacocloud.data.Taco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.sql.*;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by peishen.nie on 2020/7/15.
 */
@Repository
public class JdbcTacoImpl implements TacoRepository {

    private JdbcTemplate jdbc;

    @Autowired
    public JdbcTacoImpl(JdbcTemplate jdbcTemplate){
        this.jdbc = jdbcTemplate;
    }

    @Autowired
    private JdbcIngredientImpl ingredientImpl;

    @Override
    public Taco save(Taco design) {
        long tacoId = saveTacoInfo(design);
        for (String str : design.getIngredients()){
            Ingredient ingredient = ingredientImpl.findOne(str);
            saveIngredientToTaco(ingredient, tacoId);
        }

        return design;
    }

    private long saveTacoInfo(Taco taco){
        taco.setCrtAt(new Date());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement psst = connection.prepareStatement("insert into Taco (name, crtAt) values (?, ?)", new String[] { "id" });
                psst.setString(1, taco.getName());
                psst.setTimestamp(2, new Timestamp(taco.getCrtAt().getTime()));
                return psst;
            }
        };
        jdbc.update(psc, keyHolder);

        return keyHolder.getKey().longValue();
    }

    private void saveIngredientToTaco(Ingredient ingredient, long tacoId){
        jdbc.update("INSERT into taco_ingredients (taco, ingredient) VALUES (?, ?)", tacoId, ingredient.getId());
    }
}
