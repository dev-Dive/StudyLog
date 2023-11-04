package com.devdive.backend.acceptance;

import com.devdive.backend.persistance.entities.MemberJpaEntity;
import com.devdive.backend.persistance.repository.MemberRepository;
import com.devdive.backend.auth.application.service.jwt.JwtProvider;
import com.devdive.backend.persistance.repository.StudyMemberRepository;
import com.devdive.backend.security.authentication.Authentication;
import com.devdive.backend.security.authentication.domain.User;
import com.devdive.backend.security.core.AuthenticationCache;
import com.devdive.backend.study.application.port.in.UpdateStudyUseCase;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

class StudyAcceptanceTest extends AcceptanceTest {

    @Autowired
    UpdateStudyUseCase useCase;

    @Autowired
    JwtProvider accessJwtProvider;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    StudyMemberRepository studyMemberRepository;

    @Autowired
    AuthenticationCache cache;


    @Test
    @DisplayName("회원은 스터디를 생성할 수 있다.")
    void updateStudyTest() throws JSONException {
        // given
        memberRepository.save(new MemberJpaEntity());
        String mail = "rhwlgns@gmail.com";

        cache.removeAll();

        cache.addAuthentication(mail, new Authentication() {

            private User user = new User(mail, 1L);

            @Override
            public Object getCredentials() {
                return null;
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return user;
            }

            @Override
            public String getAuthorities() {
                return "MEMBER";
            }
        });

        JSONObject payLoad = new JSONObject();
        payLoad.put("name", mail);
        payLoad.put("description", mail);

        String accessToken = accessJwtProvider.createJwtToken(mail);
        // when, then
        given()
                .log().all()
                .header(HttpHeaders.AUTHORIZATION, "bearer" + " " + accessToken)
                .body(payLoad.toString())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        .when()
                .post("/api/v1/studies")
        .then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("회원은 스터디를 조회할 수 있다.")
    void readStudyTest() {
        // given
        memberRepository.save(new MemberJpaEntity());
        String mail = "rhwlgns@gmail.com";

        cache.removeAll();

        cache.addAuthentication(mail, new Authentication() {

            private User user = new User(mail, 1L);

            @Override
            public Object getCredentials() {
                return null;
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return user;
            }

            @Override
            public String getAuthorities() {
                return "MEMBER";
            }
        });

        String accessToken = accessJwtProvider.createJwtToken(mail);
        // when, then
        given()
                .log().all()
                .header(HttpHeaders.AUTHORIZATION, "bearer" + " " + accessToken)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        .when()
                .get("/api/v1/studies")
        .then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .assertThat()
                .body("size()", equalTo(0));
    }
}
