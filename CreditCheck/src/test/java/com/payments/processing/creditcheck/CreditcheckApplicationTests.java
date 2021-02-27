package com.payments.processing.creditcheck;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.payments.processing.creditcheck.controller.CreditCheckController;
import com.payments.processing.creditcheck.domain.CreditCheckRequest;
import com.payments.processing.creditcheck.service.CreditCheck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CreditCheckController.class)
class CreditcheckApplicationTests {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @MockBean
    private CreditCheck creditCheck;
    private static ObjectMapper MAPPER = new ObjectMapper();

    @BeforeEach
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilter(new ExceptionTranslationFilter(new BasicAuthenticationEntryPoint())).build();
    }

    @Test
    public void testCreditCheck() throws Exception {
        when(creditCheck.checkCredit(anyString(),anyDouble())).thenReturn("SUCCESS");
        mockMvc.perform(post("/check").content(MAPPER.writeValueAsString(getCreditCheckRequest()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    private CreditCheckRequest getCreditCheckRequest() {
        CreditCheckRequest request = new CreditCheckRequest();

        return request;
    }

}
