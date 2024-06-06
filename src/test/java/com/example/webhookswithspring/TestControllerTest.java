package com.example.webhookswithspring;

import com.example.webhookswithspring.contraller.testController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@WebMvcTest(testController.class)
public class TestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void test() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/test"))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertEquals("HI, API AVAILABLE!", content);
    }
}
