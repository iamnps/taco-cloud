package com.nps.tacocloud.dao;

import com.nps.tacocloud.data.Taco;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by peishen.nie on 2020/7/15.
 */
@Repository
public interface TacoRepository{

    Taco save(Taco design);
}
