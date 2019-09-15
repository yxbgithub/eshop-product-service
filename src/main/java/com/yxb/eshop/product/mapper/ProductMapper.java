package com.yxb.eshop.product.mapper;

import com.yxb.eshop.product.common.ProductRequest;
import com.yxb.eshop.product.model.Product;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ProductMapper {
	
	@Insert("INSERT INTO product(name,category_id,brand_id) VALUES(#{name},#{categoryId},#{brandId})")  
	public void add(ProductRequest request);
	
	@Update("UPDATE product SET name=#{name},category_id=#{categoryId},brand_id=#{brandId} WHERE id=#{id}")  
	public void update(ProductRequest request);
	
	@Delete("DELETE FROM product WHERE id=#{id}")  
	public void delete(Long id);
	
	@Select("SELECT * FROM product WHERE id=#{id}")  
	@Results({
		@Result(column = "category_id", property = "categoryId"),
		@Result(column = "brand_id", property = "brandId")  
	})
	public Product findById(Long id);
	
}
