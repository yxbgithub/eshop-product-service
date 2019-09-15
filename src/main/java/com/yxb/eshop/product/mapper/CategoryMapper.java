package com.yxb.eshop.product.mapper;

import com.yxb.eshop.product.common.CategoryRequest;
import com.yxb.eshop.product.model.Category;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CategoryMapper {
	
	@Insert("INSERT INTO category(name,description) VALUES(#{name},#{description})")  
	public void add(CategoryRequest request);
	
	@Update("UPDATE category SET name=#{name},description=#{description} WHERE id=#{id}")  
	public void update(CategoryRequest request);
	
	@Delete("DELETE FROM category WHERE id=#{id}")  
	public void delete(Long id);
	
	@Select("SELECT * FROM category WHERE id=#{id}")  
	public Category findById(Long id);
	
}
