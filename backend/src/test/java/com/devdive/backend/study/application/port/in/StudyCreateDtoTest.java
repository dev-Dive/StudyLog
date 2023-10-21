package com.devdive.backend.study.application.port.in;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
class StudyCreateDtoTest {

    @Test
    public void test(){
        StudyCreateApplicationDto dto = StudyCreateApplicationDto.builder()
                .memberId(1L)
                .name("qwe")
                .description("dsf")
                .build();

        assertThat(dto.getMemberId()).isEqualTo(1L);
        assertThat(dto.getName()).isEqualTo("qwe");
        assertThat(dto.getDescription()).isEqualTo("dsf");

    }

    @Test
    public void fail(){
        assertThrows(ConstraintViolationException.class, () -> {
            StudyCreateApplicationDto.builder()
                    .memberId(1L)
                    .name(null)
                    .description("dsf")
                    .build();
        });
    }

}
