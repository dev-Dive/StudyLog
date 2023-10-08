package com.devdive.backend.acceptance;

import com.devdive.backend.auth.application.service.jwt.JwtProvider;
import com.devdive.backend.hello.adaptor.in.web.HelloSecurityConfig;
import com.devdive.backend.security.core.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;

import static io.restassured.RestAssured.given;

@Import({HelloSecurityConfig.class})
public class SecurityAcceptanceTest extends AcceptanceTest {


    @Autowired
    JwtProvider mailJwtProvider;
    @Autowired
    JwtProvider accessJwtProvider;
    @Autowired
    UserDataRepository userDataRepository;

    @BeforeEach
    @Transactional
    void init() {
        userDataRepository.save(new LoadMemberPort.UserData(1L, "email@email.com"));
    }

    @DisplayName("사용자는 로그인 인증 링크로 로그인을 할 수 있다.")
    @Test
    void loginTest() throws JSONException {
        // given
        JSONObject request = new JSONObject();
        request.put("token", mailJwtProvider.createJwtToken("email@email.com"));

        // when, then
        given()
                .log().all()
                .body(request.toString())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/api/v1/auth/mail/login")
                .then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.OK.value());
    }

    @DisplayName("사용자는 access토큰을 이용하여 권한을 검증 할 수 있다.")
    @Test
    void test() {
        // given
        String jwtToken = accessJwtProvider.createJwtToken("email@email.com");
        AuthenticationCache cache = InMemoryAuthenticationCache.getInstance();
        cache.removeAll();

        cache.addAuthentication("email@email.com", new Authentication() {
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
                return new UserDetails() {
                    @Override
                    public String getUsername() {
                        return "email@email.com";
                    }

                    @Override
                    public Long getId() {
                        return 1L;
                    }
                };
            }

            @Override
            public String getAuthorities() {
                return "ADMIN";
            }
        });

        // when, then
        given()
                .log().all()
                .when()
                .header(HttpHeaders.AUTHORIZATION, "bearer " + jwtToken)
                .get("/hello/admin")
                .then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.OK.value());
    }

    @DisplayName("사용자는 access토큰을 이용하여 인증 할 수 있다.")
    @Test
    void givenSendAccessToken_thenAuthentication() {
        // given
        String jwtToken = accessJwtProvider.createJwtToken("email@email.com");
        AuthenticationCache cache = InMemoryAuthenticationCache.getInstance();
        cache.removeAll();

        cache.addAuthentication("email@email.com", new Authentication() {
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
                return new UserDetails() {
                    @Override
                    public String getUsername() {
                        return "email@email.com";
                    }

                    @Override
                    public Long getId() {
                        return 1L;
                    }
                };
            }

            @Override
            public String getAuthorities() {
                return "MEMBER";
            }
        });

        // when, then
        given()
                .log().all()
                .when()
                .header(HttpHeaders.AUTHORIZATION, "bearer " + jwtToken)
                .get("/hello")
                .then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.OK.value());
    }
}
