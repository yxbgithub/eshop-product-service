package com.yxb.eshop.product.service;

import com.yxb.eshop.product.common.CategoryRequest;
import com.yxb.eshop.product.model.Category;

public interface CategoryService {

    public void add(CategoryRequest categoryRequest, String operationType);

    public void update(CategoryRequest categoryRequest, String operationType);

    public void delete(Long id, String operationType);

    public Category findById(Long id);

}
