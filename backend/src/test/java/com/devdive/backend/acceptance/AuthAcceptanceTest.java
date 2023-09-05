package com.devdive.backend.acceptance;

import jakarta.mail.internet.MimeMessage;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;

import static io.restassured.RestAssured.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;


class AuthAcceptanceTest extends AcceptanceTest {

    @SpyBean
    JavaMailSender javaMailSender;

    @DisplayName("사용자는 인증 링크를 이메일로 받을 수 있다.")
    @Test
    void test() throws JSONException {
        // given
        JSONObject request = new JSONObject();
        request.put("mail","rhwlgns4386@naver.com");
        doNothing().when(javaMailSender).send(any(MimeMessage.class));

        // when, then
        given()
                .log().all()
                .body(request.toString())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        .when()
                .post("/api/v1/auth/sendMail")
        .then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.OK.value());
    }
}
