package com.devdive.backend.acceptance;

import com.devdive.backend.auth.application.service.jwt.JwtProvider;
import com.devdive.backend.persistance.entities.MemberJpaEntity;
import com.devdive.backend.persistance.repository.MemberRepository;
import com.devdive.backend.security.authentication.Authentication;
import com.devdive.backend.security.authentication.domain.User;
import com.devdive.backend.security.core.AuthenticationCache;
import com.devdive.backend.security.core.cache.InMemoryAuthenticationCache;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PostAcceptanceTest extends AcceptanceTest {

    @Autowired
    JwtProvider accessJwtProvider;

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("스터디원은 스터디글을 작성 후 저장할 수 있다")
    public void member_allows_to_write_And_save_Post() throws JSONException {
        // given
        memberRepository.save(new MemberJpaEntity());
        String mail = "rhwlgns@gmail.com";

        AuthenticationCache cache = InMemoryAuthenticationCache.getInstance();
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
        payLoad.put("studyId", 1L);
        payLoad.put("thumbnailUrl", "http://");
        payLoad.put("title", "title");
        payLoad.put("subtitle", "subtitle");
        payLoad.put("content", "content");
        payLoad.put("tags", List.of("tag1", "tag2"));

        String accessToken = accessJwtProvider.createJwtToken(mail);
        // when, then
        given()
                .log().all()
                .header(HttpHeaders.AUTHORIZATION, "bearer" + " " + accessToken)
                .body(payLoad.toString())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        .when()
                .post("/api/v1/posts")
        .then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.OK.value());
    }
}
