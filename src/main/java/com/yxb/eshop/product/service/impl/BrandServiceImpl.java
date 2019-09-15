package com.yxb.eshop.product.service.impl;

import com.yxb.eshop.product.common.BrandRequest;
import com.yxb.eshop.product.mapper.BrandMapper;
import com.yxb.eshop.product.model.Brand;
import com.yxb.eshop.product.rabbitmq.RabbitMQSender;
import com.yxb.eshop.product.rabbitmq.RabbitQueue;
import com.yxb.eshop.product.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;
    @Autowired
    private RabbitMQSender rabbitMQSender;

    public void add(BrandRequest request, String operationType) {
        brandMapper.add(request);

        String queue = null;

        if (operationType == null || "".equals(operationType)) {
            queue = RabbitQueue.DATA_CHANGE_QUEUE;
        } else if ("refresh".equals(operationType)) {
            queue = RabbitQueue.REFRESH_DATA_CHANGE_QUEUE;
        } else if ("high".equals(operationType)) {
            queue = RabbitQueue.HIGH_PRIORITY_DATA_CHANGE_QUEUE;
        }
        rabbitMQSender.send(queue, "{\"event_type\": \"add\", \"data_type\": \"brand\", \"id\": " + request.getId() + "}");
    }

    public void update(BrandRequest request, String operationType) {
        brandMapper.update(request);

        String queue = null;

        if (operationType == null || "".equals(operationType)) {
            queue = RabbitQueue.DATA_CHANGE_QUEUE;
        } else if ("refresh".equals(operationType)) {
            queue = RabbitQueue.REFRESH_DATA_CHANGE_QUEUE;
        } else if ("high".equals(operationType)) {
            queue = RabbitQueue.HIGH_PRIORITY_DATA_CHANGE_QUEUE;
        }
        rabbitMQSender.send(queue, "{\"event_type\": \"update\", \"data_type\": \"brand\", \"id\": " + request.getId() + "}");
    }

    public void delete(Long id, String operationType) {
        brandMapper.delete(id);

        String queue = null;

        if (operationType == null || "".equals(operationType)) {
            queue = RabbitQueue.DATA_CHANGE_QUEUE;
        } else if ("refresh".equals(operationType)) {
            queue = RabbitQueue.REFRESH_DATA_CHANGE_QUEUE;
        } else if ("high".equals(operationType)) {
            queue = RabbitQueue.HIGH_PRIORITY_DATA_CHANGE_QUEUE;
        }

        rabbitMQSender.send(queue, "{\"event_type\": \"delete\", \"data_type\": \"brand\", \"id\": " + id + "}");
    }

    public Brand findById(Long id) {
        return brandMapper.findById(id);
    }

}
