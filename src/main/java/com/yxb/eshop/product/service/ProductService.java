package com.yxb.eshop.product.service;

import com.yxb.eshop.product.common.ProductRequest;
import com.yxb.eshop.product.model.Product;

public interface ProductService {
	
	public void add(ProductRequest request, String operationType);
	
	public void update(ProductRequest request, String operationType);
	
	public void delete(Long id, String operationType);
	
	public Product findById(Long id);
	
}
