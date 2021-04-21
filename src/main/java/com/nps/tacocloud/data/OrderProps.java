package com.nps.tacocloud.data;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Created by peishen.nie on 2020/8/18.
 */

@Component
@ConfigurationProperties(prefix = "taco.orders")
@Data
@Validated
public class OrderProps {

    @Max(value = 25, message = "must be between 5 and 25")
    @Min(value = 5, message = "must be between 5 and 25")
    private int pageSize = 20;
}