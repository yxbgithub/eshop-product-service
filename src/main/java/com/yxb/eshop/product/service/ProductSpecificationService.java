package com.yxb.eshop.product.service;

import com.yxb.eshop.product.common.ProductSpecificationRequest;
import com.yxb.eshop.product.model.ProductSpecification;

public interface ProductSpecificationService {
	
	public void add(ProductSpecificationRequest request, String operationType);
	
	public void update(ProductSpecificationRequest request, String operationType);
	
	public void delete(Long id, String operationType);
	
	public ProductSpecification findById(Long id);

    ProductSpecification findByProductId(Long productId);
}
