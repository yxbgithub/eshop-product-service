package com.yxb.eshop.product.service.impl;

import com.yxb.eshop.product.common.ProductPropertyRequest;
import com.yxb.eshop.product.mapper.ProductPropertyMapper;
import com.yxb.eshop.product.model.ProductProperty;
import com.yxb.eshop.product.rabbitmq.RabbitMQSender;
import com.yxb.eshop.product.rabbitmq.RabbitQueue;
import com.yxb.eshop.product.service.ProductPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductPropertyServiceImpl implements ProductPropertyService {

    @Autowired
    private ProductPropertyMapper productPropertyMapper;
    @Autowired
    private RabbitMQSender rabbitMQSender;

    public void add(ProductPropertyRequest request, String operationType) {
        productPropertyMapper.add(request);

        String queue = null;

        if (operationType == null || "".equals(operationType)) {
            queue = RabbitQueue.DATA_CHANGE_QUEUE;
        } else if ("refresh".equals(operationType)) {
            queue = RabbitQueue.REFRESH_DATA_CHANGE_QUEUE;
        } else if ("high".equals(operationType)) {
            queue = RabbitQueue.HIGH_PRIORITY_DATA_CHANGE_QUEUE;
        }

        rabbitMQSender.send(queue, "{\"event_type\": \"add\", \"data_type\": \"product_property\", \"id\": " + request.getId() + ", \"product_id\": " + request.getProductId() + "}");
    }

    public void update(ProductPropertyRequest request, String operationType) {
        productPropertyMapper.update(request);

        String queue = null;

        if (operationType == null || "".equals(operationType)) {
            queue = RabbitQueue.DATA_CHANGE_QUEUE;
        } else if ("refresh".equals(operationType)) {
            queue = RabbitQueue.REFRESH_DATA_CHANGE_QUEUE;
        } else if ("high".equals(operationType)) {
            queue = RabbitQueue.HIGH_PRIORITY_DATA_CHANGE_QUEUE;
        }

        rabbitMQSender.send(queue, "{\"event_type\": \"update\", \"data_type\": \"product_property\", \"id\": " + request.getId() + ", \"product_id\": " + request.getProductId() + "}");
    }

    public void delete(Long id, String operationType) {
        ProductProperty productProperty = findById(id);
        productPropertyMapper.delete(id);

        String queue = null;

        if (operationType == null || "".equals(operationType)) {
            queue = RabbitQueue.DATA_CHANGE_QUEUE;
        } else if ("refresh".equals(operationType)) {
            queue = RabbitQueue.REFRESH_DATA_CHANGE_QUEUE;
        } else if ("high".equals(operationType)) {
            queue = RabbitQueue.HIGH_PRIORITY_DATA_CHANGE_QUEUE;
        }

        rabbitMQSender.send(queue, "{\"event_type\": \"delete\", \"data_type\": \"product_property\", \"id\": " + id + ", \"product_id\": " + productProperty.getProductId() + "}");
    }

    public ProductProperty findById(Long id) {
        return productPropertyMapper.findById(id);
    }

    @Override
    public ProductProperty findByProductId(Long productId) {
        return productPropertyMapper.findByProductId(productId);
    }

}
