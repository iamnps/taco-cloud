package com.nps.tacocloud.data;

import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Created by peishen.nie on 2020/8/18.
 */

@Component
// @ConfigurationProperties(prefix = "taco.orders")
@Validated
public class OrderProps {

    @Max(value = 25, message = "must be between 5 and 25")
    @Min(value = 5, message = "must be between 5 and 25")
    public int pageSize = 20;

    public int getPageSize() {
        return 0;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    
}
