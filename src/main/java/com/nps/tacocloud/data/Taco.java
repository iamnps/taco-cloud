package com.nps.tacocloud.data;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;


/**
 * Created by peishen.nie on 2020/7/13.
 */

@Data
public class Taco {


    private Integer id;

    private Date crtAt;

    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;
    @Size(min = 1, message = "You must choose at least 1 ingredient")
    private List<String> ingredients;
}
