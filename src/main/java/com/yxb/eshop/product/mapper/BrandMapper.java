package com.yxb.eshop.product.mapper;

import com.yxb.eshop.product.common.BrandRequest;
import com.yxb.eshop.product.model.Brand;
import org.apache.ibatis.annotations.*;

@Mapper
public interface BrandMapper {
	
	@Insert("INSERT INTO brand(name,description) VALUES(#{name},#{description})")  
	public void add(BrandRequest request);
	
	@Update("UPDATE brand SET name=#{name},description=#{description} WHERE id=#{id}")  
	public void update(BrandRequest request);
	
	@Delete("DELETE FROM brand WHERE id=#{id}")  
	public void delete(Long id);
	
	@Select("SELECT * FROM brand WHERE id=#{id}")  
	public Brand findById(Long id);
	
}
