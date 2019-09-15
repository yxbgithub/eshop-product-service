package com.yxb.eshop.product.mapper;

import com.yxb.eshop.product.common.ProductIntroRequest;
import com.yxb.eshop.product.model.ProductIntro;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ProductIntroMapper {
	
	@Insert("INSERT INTO product_intro(content,product_id) VALUES(#{content},#{productId})")  
	public void add(ProductIntroRequest request);
	
	@Update("UPDATE product_intro SET content=#{content},product_id=#{productId} WHERE id=#{id}")  
	public void update(ProductIntroRequest request);
	
	@Delete("DELETE FROM product_intro WHERE id=#{id}")  
	public void delete(Long id);
	
	@Select("SELECT * FROM product_intro WHERE id=#{id}")  
	public ProductIntro findById(Long id);
	
}
