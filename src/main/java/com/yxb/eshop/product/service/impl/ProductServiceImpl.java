package com.yxb.eshop.product.service.impl;

import com.yxb.eshop.product.common.ProductRequest;
import com.yxb.eshop.product.mapper.ProductMapper;
import com.yxb.eshop.product.model.Product;
import com.yxb.eshop.product.rabbitmq.RabbitMQSender;
import com.yxb.eshop.product.rabbitmq.RabbitQueue;
import com.yxb.eshop.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private RabbitMQSender rabbitMQSender;
	
	public void add(ProductRequest request, String operationType) {
		productMapper.add (request);

		String queue = null;

		if (operationType == null || "".equals(operationType)) {
			queue = RabbitQueue.DATA_CHANGE_QUEUE;
		} else if ("refresh".equals(operationType)) {
			queue = RabbitQueue.REFRESH_DATA_CHANGE_QUEUE;
		} else if ("high".equals(operationType)) {
			queue = RabbitQueue.HIGH_PRIORITY_DATA_CHANGE_QUEUE;
		}

		rabbitMQSender.send(queue, "{\"event_type\": \"add\", \"data_type\": \"product\", \"id\": " + request.getId() + "}");
	}

	public void update(ProductRequest request, String operationType) {
		productMapper.update(request);

		String queue = null;

		if (operationType == null || "".equals(operationType)) {
			queue = RabbitQueue.DATA_CHANGE_QUEUE;
		} else if ("refresh".equals(operationType)) {
			queue = RabbitQueue.REFRESH_DATA_CHANGE_QUEUE;
		} else if ("high".equals(operationType)) {
			queue = RabbitQueue.HIGH_PRIORITY_DATA_CHANGE_QUEUE;
		}

		rabbitMQSender.send(queue, "{\"event_type\": \"update\", \"data_type\": \"product\", \"id\": " + request.getId() + "}");
	}

	public void delete(Long id, String operationType) {
		productMapper.delete(id);

		String queue = null;

		if (operationType == null || "".equals(operationType)) {
			queue = RabbitQueue.DATA_CHANGE_QUEUE;
		} else if ("refresh".equals(operationType)) {
			queue = RabbitQueue.REFRESH_DATA_CHANGE_QUEUE;
		} else if ("high".equals(operationType)) {
			queue = RabbitQueue.HIGH_PRIORITY_DATA_CHANGE_QUEUE;
		}

		rabbitMQSender.send(queue, "{\"event_type\": \"delete\", \"data_type\": \"product\", \"id\": " + id + "}");
	}

	public Product findById(Long id) {
		return productMapper.findById(id);
	}

}
