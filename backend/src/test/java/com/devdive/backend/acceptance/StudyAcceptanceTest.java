package com.devdive.backend.acceptance;

import com.devdive.backend.persistance.entities.MemberJpaEntity;
import com.devdive.backend.persistance.repository.MemberRepository;
import com.devdive.backend.study.application.dto.StudyCreateDto;
import com.devdive.backend.study.application.port.in.StudyUseCase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;

class StudyAcceptanceTest extends AcceptanceTest {

    @Autowired
    StudyUseCase useCase;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    @Transactional
    public void init(){
        memberRepository.save(new MemberJpaEntity(1L,"mail","mail@naver.com",""));
    }

    @Test
    @DisplayName("회원은 스터디를 생성할 수 있다.")
    void test() throws JsonProcessingException {
        // given
        ObjectMapper mapper = new ObjectMapper();
        StudyCreateDto dto = new StudyCreateDto(1L, "name1", "desc1");

        // when, then
        given()
                .log().all()
                .body(mapper.writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        .when()
                .post("/api/v1/studies")
        .then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.OK.value());
    }
}
