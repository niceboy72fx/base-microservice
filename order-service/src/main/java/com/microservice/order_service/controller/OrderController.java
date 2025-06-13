package com.microservice.order_service.controller;

import com.microservice.order_service.controller.request.PlaceOrderRequest;
import com.microservice.order_service.service.OrderService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.awt.image.BufferedImage;

@Validated
@RestController
@RequestMapping("/order")
@Slf4j(topic = "ORDER-CONTROLLER")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @PostMapping("/placeOrder")
    public ResponseEntity<String> placeOrder(@Valid @RequestBody PlaceOrderRequest request) {
        return ResponseEntity.ok(orderService.addOrder(request));
    }

    @PostMapping(path = "/qrcode", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> generateQRCodeImage(@RequestParam String qrCode) throws Exception {
        return ResponseEntity.ok(orderService.generateQRCodeImage(qrCode));
    }

    @PostMapping(path = "/bar-code", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> generateBarcode(@RequestParam String barcode) throws Exception {
        return ResponseEntity.ok(orderService.generateBarCodeImage(barcode));
    }

    @PostMapping("/checkout/orderId")
    public ResponseEntity<String> checkoutOrder(@PathVariable String orderId) {
        return ResponseEntity.ok(orderService.checkoutOrder(orderId));
    }
}
