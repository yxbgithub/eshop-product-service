package com.yxb.eshop.product.mapper;

import com.yxb.eshop.product.common.ProductPropertyRequest;
import com.yxb.eshop.product.model.ProductProperty;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ProductPropertyMapper {
	
	@Insert("INSERT INTO product_property(name,value,product_id) VALUES(#{name},#{value},#{productId})")  
	public void add(ProductPropertyRequest request);
	
	@Update("UPDATE product_property SET name=#{name},value=#{value},product_id=#{productId} WHERE id=#{id}")  
	public void update(ProductPropertyRequest request);
	
	@Delete("DELETE FROM product_property WHERE id=#{id}")  
	public void delete(Long id);
	
	@Select("SELECT * FROM product_property WHERE id=#{id}")  
	public ProductProperty findById(Long id);

	@Select("SELECT * FROM product_property WHERE product_id=#{productId}")
	public ProductProperty findByProductId(Long productId);
	
}
