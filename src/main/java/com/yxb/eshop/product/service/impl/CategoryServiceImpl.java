package com.yxb.eshop.product.service.impl;

import com.yxb.eshop.product.common.CategoryRequest;
import com.yxb.eshop.product.mapper.CategoryMapper;
import com.yxb.eshop.product.model.Category;
import com.yxb.eshop.product.rabbitmq.RabbitMQSender;
import com.yxb.eshop.product.rabbitmq.RabbitQueue;
import com.yxb.eshop.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private RabbitMQSender rabbitMQSender;

    public void add(CategoryRequest request, String operationType) {
        categoryMapper.add(request);

        String queue = null;

        if (operationType == null || "".equals(operationType)) {
            queue = RabbitQueue.DATA_CHANGE_QUEUE;
        } else if ("refresh".equals(operationType)) {
            queue = RabbitQueue.REFRESH_DATA_CHANGE_QUEUE;
        } else if ("high".equals(operationType)) {
            queue = RabbitQueue.HIGH_PRIORITY_DATA_CHANGE_QUEUE;
        }

        rabbitMQSender.send(queue, "{\"event_type\": \"add\", \"data_type\": \"category\", \"id\": " + request.getId() + "}");
    }

    public void update(CategoryRequest request, String operationType) {
        categoryMapper.update(request);

        String queue = null;

        if (operationType == null || "".equals(operationType)) {
            queue = RabbitQueue.DATA_CHANGE_QUEUE;
        } else if ("refresh".equals(operationType)) {
            queue = RabbitQueue.REFRESH_DATA_CHANGE_QUEUE;
        } else if ("high".equals(operationType)) {
            queue = RabbitQueue.HIGH_PRIORITY_DATA_CHANGE_QUEUE;
        }

        rabbitMQSender.send(queue, "{\"event_type\": \"update\", \"data_type\": \"category\", \"id\": " + request.getId() + "}");
    }

    public void delete(Long id, String operationType) {
        categoryMapper.delete(id);

        String queue = null;

        if (operationType == null || "".equals(operationType)) {
            queue = RabbitQueue.DATA_CHANGE_QUEUE;
        } else if ("refresh".equals(operationType)) {
            queue = RabbitQueue.REFRESH_DATA_CHANGE_QUEUE;
        } else if ("high".equals(operationType)) {
            queue = RabbitQueue.HIGH_PRIORITY_DATA_CHANGE_QUEUE;
        }

        rabbitMQSender.send(queue, "{\"event_type\": \"delete\", \"data_type\": \"category\", \"id\": " + id + "}");
    }

    public Category findById(Long id) {
        return categoryMapper.findById(id);
    }

}
