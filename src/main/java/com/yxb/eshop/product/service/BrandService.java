package com.yxb.eshop.product.service;

import com.yxb.eshop.product.common.BrandRequest;
import com.yxb.eshop.product.model.Brand;

public interface BrandService {

    public void add(BrandRequest request, String operationType);

    public void update(BrandRequest request, String operationType);

    public void delete(Long id, String operationType);

    public Brand findById(Long id);

}
