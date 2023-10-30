package com.devdive.backend.study.adapter.out;

import com.devdive.backend.persistance.entities.MemberJpaEntity;
import com.devdive.backend.persistance.entities.StudyJpaEntity;
import com.devdive.backend.persistance.repository.MemberRepository;
import com.devdive.backend.persistance.repository.StudyMemberRepository;
import com.devdive.backend.persistance.repository.StudyRepository;
import com.devdive.backend.study.adapter.out.persistence.UpdateStudyPersistenceAdapter;
import com.devdive.backend.study.application.port.in.StudyCreateApplicationDto;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class ReadUpdateStudyPersistenceAdapterTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    StudyRepository studyRepository;

    @Autowired
    StudyMemberRepository studyMemberRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("스터디 및 매핑 테이블 생성 테스트")
    public void studyMemberMappingTest() {
        // given
        MemberJpaEntity member1 = new MemberJpaEntity();
        MemberJpaEntity member2 = new MemberJpaEntity();
        memberRepository.save(member1);
        memberRepository.save(member2);

        StudyCreateApplicationDto dto1 = new StudyCreateApplicationDto(
                member1.getId(),
                "name1",
                "desc1",
                null);
        StudyCreateApplicationDto dto2 = new StudyCreateApplicationDto(
                member1.getId(),
                "name2",
                "desc2",
                null);
        StudyCreateApplicationDto dto3 = new StudyCreateApplicationDto(
                member2.getId(),
                "name2",
                "desc2",
                null);

        UpdateStudyPersistenceAdapter updateAdapter = new UpdateStudyPersistenceAdapter(memberRepository,
                studyRepository, studyMemberRepository);
        updateAdapter.createStudy(dto1);
        updateAdapter.createStudy(dto2);
        updateAdapter.createStudy(dto3);

        // when
        List<StudyJpaEntity> studyJpaEntities = studyMemberRepository.readStudies(member1.getId());

        // then
        assertThat(studyJpaEntities.size()).isEqualTo(2);
    }

}
