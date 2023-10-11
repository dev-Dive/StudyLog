package com.devdive.backend.acceptance;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PostAcceptanceTest extends AcceptanceTest {
    @Test
    @DisplayName("스터디원은 스터디글을 작성 후 저장할 수 있다")
    public void member_allows_to_write_And_save_Post () throws JSONException {
        // given
        JSONObject request = new JSONObject();
        request.put("studyId",1L);
        request.put("thumbnailUrl","http://");
        request.put("title","title");
        request.put("subtitle","subtitle");
        request.put("content","content");
        request.put("tags", List.of("tag1", "tag2"));

        // when, then
        given()
                .log().all()
                .body(request.toString())
               .contentType(MediaType.APPLICATION_JSON_VALUE)
        .when()
               .post("/api/v1/posts")
        .then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.OK.value());
    }
}
