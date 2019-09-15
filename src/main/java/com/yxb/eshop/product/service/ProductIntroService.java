package com.yxb.eshop.product.service;

import com.yxb.eshop.product.common.ProductIntroRequest;
import com.yxb.eshop.product.model.ProductIntro;

public interface ProductIntroService {
	
	public void add(ProductIntroRequest request, String operationType);
	
	public void update(ProductIntroRequest request, String operationType);
	
	public void delete(Long id, String operationType);
	
	public ProductIntro findById(Long id);
	
}
