package com.yxb.eshop.product.web.controller;

import com.yxb.eshop.product.common.ProductSpecificationRequest;
import com.yxb.eshop.product.model.ProductSpecification;
import com.yxb.eshop.product.service.ProductSpecificationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/product-specification")
@Api
public class ProductSpecificationController {

	@Autowired
	private ProductSpecificationService productSpecificationService;

	@PostMapping
	@ApiOperation("添加")
	public String add(@RequestBody @Valid ProductSpecificationRequest request, String operationType) {
		try {
			productSpecificationService.add(request,operationType);
		} catch (Exception e) {
			e.printStackTrace(); 
			return "error";
		}
		return "success";
	}
	
	@PutMapping
	@ApiOperation("更新")
	public String update(@RequestBody @Valid ProductSpecificationRequest request, String operationType) {
		try {
			productSpecificationService.update(request,operationType);
		} catch (Exception e) {
			e.printStackTrace(); 
			return "error";
		}
		return "success";
	}
	
	@DeleteMapping("/{productSpecificationId}")
	@ApiOperation("删除")
	public String delete(@PathVariable("productSpecificationId") Long id, String operationType) {
		try {
			productSpecificationService.delete(id,operationType);
		} catch (Exception e) {
			e.printStackTrace(); 
			return "error";
		}
		return "success";
	}
	
	@GetMapping("/{productSpecificationId}")
	@ApiOperation("获取")
	public ProductSpecification findById(@PathVariable("productSpecificationId") Long id){
		try {
			return productSpecificationService.findById(id);
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		return new ProductSpecification();
	}

	@GetMapping("/{productId}/product")
	public ProductSpecification findByProductId(@PathVariable("productId") Long productId){
		try {
			return productSpecificationService.findByProductId(productId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ProductSpecification();
	}
	
}
