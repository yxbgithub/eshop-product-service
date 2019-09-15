package com.yxb.eshop.product.service.impl;

import com.yxb.eshop.product.common.ProductIntroRequest;
import com.yxb.eshop.product.mapper.ProductIntroMapper;
import com.yxb.eshop.product.model.ProductIntro;
import com.yxb.eshop.product.rabbitmq.RabbitMQSender;
import com.yxb.eshop.product.rabbitmq.RabbitQueue;
import com.yxb.eshop.product.service.ProductIntroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductIntroServiceImpl implements ProductIntroService {

    @Autowired
    private ProductIntroMapper productIntroMapper;
    @Autowired
    private RabbitMQSender rabbitMQSender;

    public void add(ProductIntroRequest request, String operationType) {
        productIntroMapper.add(request);

        String queue = null;

        if (operationType == null || "".equals(operationType)) {
            queue = RabbitQueue.DATA_CHANGE_QUEUE;
        } else if ("refresh".equals(operationType)) {
            queue = RabbitQueue.REFRESH_DATA_CHANGE_QUEUE;
        } else if ("high".equals(operationType)) {
            queue = RabbitQueue.HIGH_PRIORITY_DATA_CHANGE_QUEUE;
        }

        rabbitMQSender.send(queue, "{\"event_type\": \"add\", \"data_type\": \"product_intro\", \"id\": " + request.getId() + "}");
    }

    public void update(ProductIntroRequest request, String operationType) {
        productIntroMapper.update(request);

        String queue = null;

        if (operationType == null || "".equals(operationType)) {
            queue = RabbitQueue.DATA_CHANGE_QUEUE;
        } else if ("refresh".equals(operationType)) {
            queue = RabbitQueue.REFRESH_DATA_CHANGE_QUEUE;
        } else if ("high".equals(operationType)) {
            queue = RabbitQueue.HIGH_PRIORITY_DATA_CHANGE_QUEUE;
        }

        rabbitMQSender.send(queue, "{\"event_type\": \"update\", \"data_type\": \"product_intro\", \"id\": " + request.getId() + "}");
    }

    public void delete(Long id, String operationType) {
        ProductIntro productIntro = findById(id);
        productIntroMapper.delete(id);

        String queue = null;

        if (operationType == null || "".equals(operationType)) {
            queue = RabbitQueue.DATA_CHANGE_QUEUE;
        } else if ("refresh".equals(operationType)) {
            queue = RabbitQueue.REFRESH_DATA_CHANGE_QUEUE;
        } else if ("high".equals(operationType)) {
            queue = RabbitQueue.HIGH_PRIORITY_DATA_CHANGE_QUEUE;
        }
        rabbitMQSender.send(queue, "{\"event_type\": \"delete\", \"data_type\": \"product_intro\", \"id\": " + id + ", \"product_id\": " + productIntro.getProductId() + "}");
    }

    public ProductIntro findById(Long id) {
        return productIntroMapper.findById(id);
    }

}
