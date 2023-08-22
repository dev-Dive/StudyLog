package com.devdive.backend.acceptance;

import jakarta.mail.internet.MimeMessage;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;

import static io.restassured.RestAssured.post;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;


class AuthAcceptanceTest extends AcceptanceTest {

    @MockBean
    private JavaMailSender mailSender;

    @DisplayName("사용자는 인증 링크를 이메일로 받을 수 있다.")
    @Test
    void test() throws JSONException {
        // given
        JSONObject request = new JSONObject();
        request.put("email","rhwlgns4386@qkqh.com");
        doNothing().when(mailSender).send(any(MimeMessage.class));

        // when, then
        post("/sendMail", request.toString())
                .then()
                .assertThat().statusCode(HttpStatus.OK.value());
        verify(mailSender, times(1)).send(any(MimeMessage.class));
    }
}
