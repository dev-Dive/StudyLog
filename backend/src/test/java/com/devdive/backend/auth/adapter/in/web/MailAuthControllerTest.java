package com.devdive.backend.auth.adapter.in.web;

import com.devdive.backend.auth.application.port.in.SendMailUseCase;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(MailAuthController.class)
public class MailAuthControllerTest {

    @MockBean
    private SendMailUseCase sendMailUseCase;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("이메일 전송")
    public void sendEmail() throws Exception {
        String email = "rhwlgns@rheodms.com";

        doNothing().when(sendMailUseCase).sendMail(anyString());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("mail", email);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/sendMail")
                .content(jsonObject.toString())
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("잘못된 메일 포멧으로 메일 전송 실패")
    public void sendEmailFault() throws Exception {
        String mail = "rkdtjdqja";

        doNothing().when(sendMailUseCase).sendMail(anyString());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("mail", mail);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/sendMail")
                .content(jsonObject.toString())
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isBadRequest());
    }
}
