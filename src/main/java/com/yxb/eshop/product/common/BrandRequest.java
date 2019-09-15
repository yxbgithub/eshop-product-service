package com.yxb.eshop.product.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("品牌实体类")
public class BrandRequest {
    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("品牌名称")
    private String name;
    @ApiModelProperty("品牌描述")
    private String description;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
