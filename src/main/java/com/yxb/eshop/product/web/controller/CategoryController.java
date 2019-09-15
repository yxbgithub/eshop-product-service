package com.yxb.eshop.product.web.controller;

import com.yxb.eshop.product.common.CategoryRequest;
import com.yxb.eshop.product.model.Category;
import com.yxb.eshop.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public String add(@RequestBody @Valid CategoryRequest request, String operationType) {
        try {
            categoryService.add(request,operationType);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    @PutMapping
    public String update(@RequestBody @Valid CategoryRequest request, String operationType) {
        try {
            categoryService.update(request,operationType);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    @DeleteMapping("/{categoryId}")
    public String delete(@PathVariable("categoryId") Long categoryId, String operationType) {
        try {
            categoryService.delete(categoryId,operationType);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    @GetMapping("/{categoryId}")
    public Category findById(@PathVariable("categoryId") Long categoryId) {
        try {
            return categoryService.findById(categoryId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Category();
    }

}
