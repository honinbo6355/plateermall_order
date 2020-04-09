package com.plateer.controller;

import com.plateer.OrderRestTestApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderRestTestApplication.class)
@AutoConfigureMockMvc
//@WebMvcTest(controllers = OrderController.class)
public class OrderControllerTest {

    @Autowired
    MockMvc mockMvc;
//    @Autowired
//    OrderController orderController;

    @Test
    public void test() throws Exception {
//        mockMvc.perform(get("/test"))
//                .andExpect(status().isOk());
        mockMvc.perform(get("http://localhost:9999/api/order/test"))
                .andExpect(status().isOk());
    }
}
