package com.nps.tacocloud.data;

import lombok.Data;

import javax.persistence.Entity;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by peishen.nie on 2020/7/14.
 */

@Data
@Entity
public class Order {


    private long id;
    private Date placedAt;

    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Street is required")
    private String street;
    @NotBlank(message = "City is required")
    private String city;
    @NotBlank(message = "State is required")
    private String state;
    @NotBlank(message = "Zip is required")
    private String zip;
    @Pattern(regexp = "[1-9]{25}$", message = "Must be formatted credit card number")
    private String ccNumber;
    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message = "Must be formatted MM/YY")
    private String ccExpiration;
    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String ccCvv;

    private List<Taco> tacos = new ArrayList<>();

    public void addDesign(Taco taco){
        this.tacos.add(taco);
    }

}
