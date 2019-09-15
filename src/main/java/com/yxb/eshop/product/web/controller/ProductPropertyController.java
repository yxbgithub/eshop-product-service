package com.yxb.eshop.product.web.controller;

import com.yxb.eshop.product.common.ProductPropertyRequest;
import com.yxb.eshop.product.model.ProductProperty;
import com.yxb.eshop.product.service.ProductPropertyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/product-property")
@Api
public class ProductPropertyController {

	@Autowired
	private ProductPropertyService productPropertyService;
	
	@PostMapping
	@ApiOperation("添加")
	public String add(@RequestBody @Valid ProductPropertyRequest request, String operationType) {
		try {
			productPropertyService.add(request,operationType);
		} catch (Exception e) {
			e.printStackTrace(); 
			return "error";
		}
		return "success";
	}
	
	@PutMapping
	@ApiOperation("更新")
	public String update(@RequestBody @Valid ProductPropertyRequest request, String operationType) {
		try {
			productPropertyService.update(request,operationType);
		} catch (Exception e) {
			e.printStackTrace(); 
			return "error";
		}
		return "success";
	}
	
	@DeleteMapping("/{productPropertyId}")
	@ApiOperation("删除")
	public String delete(@PathVariable("productPropertyId") Long id, String operationType) {
		try {
			productPropertyService.delete(id,operationType);
		} catch (Exception e) {
			e.printStackTrace(); 
			return "error";
		}
		return "success";
	}

	@GetMapping("/{productPropertyId}")
	@ApiOperation("获取")
	public ProductProperty findById(@PathVariable("productPropertyId") Long id){
		try {
			return productPropertyService.findById(id);
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		return new ProductProperty();
	}

	@GetMapping("/{productId}/product")
	public ProductProperty findByProductId(@PathVariable("productId") Long productId){
		try {
			return productPropertyService.findByProductId(productId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ProductProperty();
	}
	
}
