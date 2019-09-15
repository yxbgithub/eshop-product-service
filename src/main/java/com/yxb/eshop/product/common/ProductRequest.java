package com.yxb.eshop.product.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("商品类")
public class ProductRequest {
    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("种类id")
    private Long categoryId;
    @ApiModelProperty("品牌id")
    private Long brandId;

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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }
}
