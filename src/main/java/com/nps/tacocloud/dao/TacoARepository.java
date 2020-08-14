package com.nps.tacocloud.dao;

import com.nps.tacocloud.data.Taco;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by peishen.nie on 2020/7/22.
 */
public interface TacoARepository extends CrudRepository<Taco, Integer> {
}
