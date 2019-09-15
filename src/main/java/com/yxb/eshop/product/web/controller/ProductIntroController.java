package com.yxb.eshop.product.web.controller;

import com.yxb.eshop.product.common.ProductIntroRequest;
import com.yxb.eshop.product.model.ProductIntro;
import com.yxb.eshop.product.service.ProductIntroService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/product-intro")
@Api
public class ProductIntroController {

	@Autowired
	private ProductIntroService productIntroService;

	@PostMapping
	@ApiOperation("添加")
	public String add(@RequestBody @Valid ProductIntroRequest request, String operationType) {
		try {
			productIntroService.add(request,operationType);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "success";
	}

	@PutMapping
	@ApiOperation("更新")
	public String update(@RequestBody @Valid ProductIntroRequest request, String operationType) {
		try {
			productIntroService.update(request,operationType);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "success";
	}

	@DeleteMapping("{productIntroId}")
	@ApiOperation("删除")
	public String delete(@PathVariable("productIntroId") Long id, String operationType) {
		try {
			productIntroService.delete(id,operationType);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "success";
	}

	@GetMapping("/{productIntroId}")
	@ApiOperation("获取")
	public ProductIntro findById(@PathVariable("productIntroId") Long id){
		try {
			return productIntroService.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ProductIntro();
	}

}
