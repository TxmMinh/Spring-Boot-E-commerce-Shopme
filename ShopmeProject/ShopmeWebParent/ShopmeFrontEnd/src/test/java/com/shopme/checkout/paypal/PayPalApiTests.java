package com.shopme.checkout.paypal;

import org.junit.jupiter.api.Test;

import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

public class PayPalApiTests {
    private static final String BASE_URL = "https://api.sandbox.paypal.com";
    private static final String GET_ORDER_API = "/v2/checkout/orders/";
    private static final String CLIENT_ID = "AT5qb7WzFBoqIFH6rR5Pu2TYM6u4RGVkCN_kC-ru2UjuBQRw3O0y0XA8lDPbH5xRyByBOEW_a8ezbn1l";
    private static final String CLIENT_SECRET = "EJIibxHcaGn3sjDRQ7OSURdiz25tzNHCDsboRHY4ECac_N_pwT2wR0I8nSjQ5PKoeydV8IpulOq2SVcz";

    @Test
    public void testGetOrderDetails() {
        String orderId = "5AM96141H0248680P";
        String requestURL = BASE_URL + GET_ORDER_API + orderId;

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("Accept-Language", "en-US");
        headers.setBasicAuth(CLIENT_ID, CLIENT_SECRET);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<PayPalOrderResponse> response = restTemplate.exchange(requestURL,
                HttpMethod.GET, request, PayPalOrderResponse.class);
        PayPalOrderResponse orderResponse = response.getBody();

        System.out.println("Order ID: "  + orderResponse.getId());
        System.out.println("Validated: " + orderResponse.validate(orderId));
    }
}
