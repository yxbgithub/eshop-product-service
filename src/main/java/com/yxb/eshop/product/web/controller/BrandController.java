package com.yxb.eshop.product.web.controller;

import com.yxb.eshop.product.common.BrandRequest;
import com.yxb.eshop.product.model.Brand;
import com.yxb.eshop.product.service.BrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/brand")
@Api("品牌类接口")
public class BrandController {

	@Autowired
	private BrandService brandService;
	
	@PostMapping
	@ApiOperation("添加商品")
	public String add(@RequestBody @Valid BrandRequest request,@RequestParam String operationType) {
		try {
			brandService.add(request,operationType);
		} catch (Exception e) {
			e.printStackTrace(); 
			return "error";
		}
		return "success";
	}
	
	@PutMapping
	@ApiOperation("更新商品")
	public String update(@RequestBody @Valid BrandRequest request, String operationType) {
		try {
			brandService.update(request,operationType);
		} catch (Exception e) {
			e.printStackTrace(); 
			return "error";
		}
		return "success";
	}
	
	@DeleteMapping("/{brandId}")
	@ApiOperation("删除商品")
	public String delete(@PathVariable("brandId") Long brandId, String operationType) {
		try {
			brandService.delete(brandId,operationType);
		} catch (Exception e) {
			e.printStackTrace(); 
			return "error";
		}
		return "success";
	}
	
	@GetMapping("/{brandId}")
	@ApiOperation("获取商品")
	public Brand findById(@PathVariable("brandId") Long brandId){
		try {
			return brandService.findById(brandId);
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		return new Brand();
	}
	
}
