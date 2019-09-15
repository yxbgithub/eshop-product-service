package com.yxb.eshop.product.service;

import com.yxb.eshop.product.common.ProductPropertyRequest;
import com.yxb.eshop.product.model.ProductProperty;

public interface ProductPropertyService {
	
	public void add(ProductPropertyRequest request, String operationType);
	
	public void update(ProductPropertyRequest request, String operationType);
	
	public void delete(Long id, String operationType);
	
	public ProductProperty findById(Long id);

    ProductProperty findByProductId(Long productId);
}
