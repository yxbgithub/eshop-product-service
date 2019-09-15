package com.yxb.eshop.product.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ProductPropertyRequest {
    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("value")
    private String value;
    @ApiModelProperty("商品id")
    private Long productId;

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
