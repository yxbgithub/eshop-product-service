package com.yxb.eshop.product.service.impl;

import com.yxb.eshop.product.common.ProductSpecificationRequest;
import com.yxb.eshop.product.mapper.ProductSpecificationMapper;
import com.yxb.eshop.product.model.ProductSpecification;
import com.yxb.eshop.product.rabbitmq.RabbitMQSender;
import com.yxb.eshop.product.rabbitmq.RabbitQueue;
import com.yxb.eshop.product.service.ProductSpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductSpecificationServiceImpl implements ProductSpecificationService {

    @Autowired
    private ProductSpecificationMapper productSpecificationMapper;
    @Autowired
    private RabbitMQSender rabbitMQSender;

    public void add(ProductSpecificationRequest request, String operationType) {
        productSpecificationMapper.add(request);

        String queue = null;

        if (operationType == null || "".equals(operationType)) {
            queue = RabbitQueue.DATA_CHANGE_QUEUE;
        } else if ("refresh".equals(operationType)) {
            queue = RabbitQueue.REFRESH_DATA_CHANGE_QUEUE;
        } else if ("high".equals(operationType)) {
            queue = RabbitQueue.HIGH_PRIORITY_DATA_CHANGE_QUEUE;
        }

        rabbitMQSender.send(queue, "{\"event_type\": \"add\", \"data_type\": \"product_specification\", \"id\": " + request.getId() + ", \"product_id\": " + request.getProductId() + "}");
    }

    public void update(ProductSpecificationRequest request, String operationType) {
        productSpecificationMapper.update(request);

        String queue = null;

        if (operationType == null || "".equals(operationType)) {
            queue = RabbitQueue.DATA_CHANGE_QUEUE;
        } else if ("refresh".equals(operationType)) {
            queue = RabbitQueue.REFRESH_DATA_CHANGE_QUEUE;
        } else if ("high".equals(operationType)) {
            queue = RabbitQueue.HIGH_PRIORITY_DATA_CHANGE_QUEUE;
        }

        rabbitMQSender.send(queue, "{\"event_type\": \"update\", \"data_type\": \"product_specification\", \"id\": " + request.getId() + ", \"product_id\": " + request.getProductId() + "}");
    }

    public void delete(Long id, String operationType) {
        ProductSpecification productSpecification = findById(id);
        productSpecificationMapper.delete(id);

        String queue = null;

        if (operationType == null || "".equals(operationType)) {
            queue = RabbitQueue.DATA_CHANGE_QUEUE;
        } else if ("refresh".equals(operationType)) {
            queue = RabbitQueue.REFRESH_DATA_CHANGE_QUEUE;
        } else if ("high".equals(operationType)) {
            queue = RabbitQueue.HIGH_PRIORITY_DATA_CHANGE_QUEUE;
        }

        rabbitMQSender.send(queue, "{\"event_type\": \"delete\", \"data_type\": \"product_specification\", \"id\": " + id + ", \"product_id\": " + productSpecification.getProductId() + "}");
    }

    public ProductSpecification findById(Long id) {

        return productSpecificationMapper.findById(id);
    }

    @Override
    public ProductSpecification findByProductId(Long productId) {
        return productSpecificationMapper.findByProductId(productId);
    }

}
