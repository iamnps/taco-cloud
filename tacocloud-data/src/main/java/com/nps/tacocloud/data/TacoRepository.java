package com.nps.tacocloud.data;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.nps.tacocloud.domain.Taco;

/**
 * Created by peishen.nie on 2020/7/15.
 */
public interface TacoRepository extends PagingAndSortingRepository<Taco, Long> {


    
}
