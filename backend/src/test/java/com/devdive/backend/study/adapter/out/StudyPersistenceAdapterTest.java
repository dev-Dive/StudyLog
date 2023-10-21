package com.devdive.backend.study.adapter.out;

import com.devdive.backend.persistance.entities.MemberJpaEntity;
import com.devdive.backend.persistance.repository.MemberRepository;
import com.devdive.backend.study.adapter.out.persistence.StudyPersistenceAdapter;
import com.devdive.backend.persistance.repository.StudyMemberRepository;
import com.devdive.backend.persistance.repository.StudyRepository;
import com.devdive.backend.study.application.port.in.StudyCreateApplicationDto;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@DataJpaTest
@ActiveProfiles("test")
class StudyPersistenceAdapterTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    StudyRepository studyRepository;

    @Autowired
    StudyMemberRepository studyMemberRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("게시글 및 매핑 테이블 생성 테스트")
    public void postMemberMappingTest() {
        // given
        MemberJpaEntity member1 = new MemberJpaEntity();
        memberRepository.save(member1);

        StudyCreateApplicationDto dto1 = new StudyCreateApplicationDto(
                member1.getId(),
                "name1",
                "desc1"
        );
        StudyCreateApplicationDto dto2 = new StudyCreateApplicationDto(
                member1.getId(),
                "name2",
                "desc2"
        );

        StudyPersistenceAdapter adapter = new StudyPersistenceAdapter(memberRepository,
                studyRepository, studyMemberRepository);

        // when
        adapter.createStudy(dto1);
        adapter.createStudy(dto2);

        // then
        assertThat(studyRepository.count()).isEqualTo(2);
    }
}
