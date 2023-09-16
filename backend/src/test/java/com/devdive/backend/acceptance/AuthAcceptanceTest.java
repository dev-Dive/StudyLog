package com.devdive.backend.acceptance;

import com.devdive.backend.auth.application.service.jwt.JwtProvider;
import io.restassured.http.ContentType;
import jakarta.mail.internet.MimeMessage;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.hasKey;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;


class AuthAcceptanceTest extends AcceptanceTest {

    @SpyBean
    JavaMailSender javaMailSender;

    @Value("${jwt.mail.secret}")
    String secret;

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
                .post("/api/v1/auth/mail/sendMail")
        .then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("사용자는 회원가입 인증 링크로 회원가입을 할 수  있다.")
    void givenRegisterLink_whenRequest_thenRegister() throws JSONException {
        // given
        String email = "test@test.com";
        JwtProvider jwtProvider = new JwtProvider(secret, 1);
        String jwt = jwtProvider.createJwtToken(email);
        JSONObject requestBody = new JSONObject();
        requestBody.put("token", jwt);

        // when, then
        given()
                .log().all()
                .basePath("/api/v1/auth/mail/register")
                .body(requestBody.toString())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
        .when()
                .post()
        .then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON)
                .body("$", hasKey("accessToken"))
                .body("$", hasKey("refreshToken"));
    }

    @Test
    @DisplayName("사용자는 로그인 인증 링크로 로그인을 할 수 있다.")
    void givenLoginLink_whenRequest_thenLogin() throws JSONException {
        // given
        String email = "test@test.com";
        JwtProvider jwtProvider = new JwtProvider(secret, 1);
        String jwt = jwtProvider.createJwtToken(email);
        JSONObject requestBody = new JSONObject();
        requestBody.put("token", jwt);

        // when, then
        given()
                .log().all()
                .basePath("/api/v1/auth/mail/login")
                .body(requestBody.toString())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
        .when()
                .post()
        .then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON)
                .body("$", hasKey("accessToken"))
                .body("$", hasKey("refreshToken"));
    }

    @Test
    @DisplayName("사용자는 만료된 링크로 인증 시 401응답을 받는다.")
    void givenExpiredLink_whenRequest_then401() throws JSONException {
        // given
        String email = "test@test.com";
        JwtProvider jwtProvider = new JwtProvider(secret, 0);
        String jwt = jwtProvider.createJwtToken(email);
        JSONObject requestBody = new JSONObject();
        requestBody.put("token", jwt);

        // when, then
        given()
                .log().all()
                .basePath("/api/v1/auth/mail/register")
                .body(requestBody.toString())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
        .when()
                .post()
        .then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.UNAUTHORIZED.value());
    }

    @Test
    @DisplayName("사용자는 위조된 토큰으로 인증 요청 시 401응답을 받는다.")
    void givenForgedLink_whenRequest_then401() throws JSONException {
        // given
        String email = "test@test.com";
        JwtProvider jwtProvider = new JwtProvider(secret, 1);
        String jwt = jwtProvider.createJwtToken(email) + "1";
        JSONObject requestBody = new JSONObject();
        requestBody.put("token", jwt);

        // when, then
        given()
                .log().all()
                .basePath("/api/v1/auth/mail/register")
                .body(requestBody.toString())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
        .when()
                .post()
        .then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.UNAUTHORIZED.value());
    }
}
