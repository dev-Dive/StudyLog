package com.devdive.backend.acceptance;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RecentPostAcceptanceTest extends AcceptanceTest {

    @Test
    @DisplayName("가장 최신의 글 목록을 일정 개수만큼 가지고 올 수 있다.")
    void givenCursorAndLimit_whenRequest_thenReturnRecentPosts() {
        Long cursor = Long.MAX_VALUE;
        int limit = 5;

        given()
                .log().all()
        .when()
                .queryParam("cursor", cursor)
                .queryParam("limit", limit)
                .get("/api/v1/posts")
        .then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body("data.size()", equalTo(limit));
    }

    @Test
    @DisplayName("최신글 목록 응답의 페이징 정보로 다음 커서와 이전 커서의 위치를 알 수 있다.")
    void givenPostsResponse_whenRequest_thenNextPostsAndPreviousPosts() {
        Long cursor = Long.MAX_VALUE;
        int limit = 5;

        given()
                .log().all()
        .when()
                .queryParam("cursor", cursor)
                .queryParam("limit", limit)
                .get("/api/v1/posts")
        .then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body("paging", equalTo(limit));
    }

}
