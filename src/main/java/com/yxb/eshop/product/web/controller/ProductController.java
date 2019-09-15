package com.yxb.eshop.product.web.controller;

import com.yxb.eshop.product.common.ProductRequest;
import com.yxb.eshop.product.model.Product;
import com.yxb.eshop.product.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/product")
@Api
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    @ApiOperation("添加")
    public String add(@RequestBody @Valid ProductRequest request, String operationType) {
        try {
            productService.add(request,operationType);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    @PutMapping
    @ApiOperation("更新")
    public String update(@RequestBody @Valid ProductRequest request, String operationType) {
        try {
            productService.update(request,operationType);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    @DeleteMapping("/{productId}")
    @ApiOperation("删除")
    public String delete(@PathVariable("productId") Long productId, String operationType) {
        try {
            productService.delete(productId,operationType);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    @GetMapping("/{productId}")
    @ApiOperation("获取")
    public Product findById(@PathVariable("productId") Long productId) {
        try {
            return productService.findById(productId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Product();
    }

}
