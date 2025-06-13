package com.microservice.order_service.service;

import com.google.zxing.WriterException;
import com.microservice.order_service.controller.request.PlaceOrderRequest;

import java.awt.image.BufferedImage;

public interface OrderService {

    String addOrder(PlaceOrderRequest orderRequest);

    BufferedImage generateQRCodeImage(String qrcode) throws WriterException;

    BufferedImage generateBarCodeImage(String barCode) throws WriterException;

    String checkoutOrder(String orderId);
}
