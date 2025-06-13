package com.microservice.payment_service.controller;

import com.microservice.payment_service.service.PaymentService;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/payment")
@Slf4j(topic = "PAYMENT-CONTROLLER")
public class PaymentController {

    private  PaymentService paymentService;

    private PaymentController (PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/charge")
    public String charge(@RequestBody Map<String, Object> data) throws StripeException {

        String token = (String) data.get("token");
        double amount = Double.parseDouble(data.get("amount").toString());

        // Call Stripe service to charge the card
        Charge charge = paymentService.charge(token, amount);

        return charge.getStatus(); // Return the payment status
    }

//    @PostMapping("/payment-intent")
//    public ResponseEntity<String> createPaymentIntent(@RequestBody PaymentInfoRequest paymentInfoRequest)
//            throws StripeException {
//
//        PaymentIntent paymentIntent = paymentService.createPaymentIntent(paymentInfoRequest);
//        String paymentStr = paymentIntent.toJson();
//
//        return new ResponseEntity<>(paymentStr, HttpStatus.OK);
//    }

    @PostMapping("/payment-intent-2")
    public ResponseEntity<String> createPaymentIntent2(@RequestBody PaymentInfoRequest request)
            throws StripeException {

        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(request.getAmount())
                .setCurrency(request.getCurrency())
//                .setPaymentMethod("card")
                .build();
        PaymentIntent paymentIntent = PaymentIntent.create(params);
        if (paymentIntent != null) {
            return new ResponseEntity<>(paymentIntent.getClientSecret(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/payment-complete")
    public ResponseEntity<String> stripePaymentComplete(@RequestHeader(value="Authorization") String token)
            throws Exception {
//        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
//        if (userEmail == null) {
//            throw new Exception("User email is missing");
//        }
        return paymentService.stripePayment("orderId");
    }
}
