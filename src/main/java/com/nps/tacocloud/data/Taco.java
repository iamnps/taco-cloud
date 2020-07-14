package com.nps.tacocloud.data;

import lombok.Data;

import java.util.List;

/**
 * Created by peishen.nie on 2020/7/13.
 */

@Data
public class Taco {

    private String name;
    private List<String> ingredients;
}
