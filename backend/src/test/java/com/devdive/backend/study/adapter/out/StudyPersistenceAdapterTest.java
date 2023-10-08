package com.devdive.backend.study.adapter.out;

import com.devdive.backend.study.adapter.out.persistence.MemberStudyJpaEntity;
import com.devdive.backend.study.adapter.out.persistence.StudyPersistenceAdapter;
import com.devdive.backend.study.adapter.out.persistence.repository.MappingTableStudyMemberRepository;
import com.devdive.backend.study.adapter.out.persistence.repository.MemberStudyRepository;
import com.devdive.backend.study.adapter.out.persistence.repository.StudyRepository;
import com.devdive.backend.study.application.dto.StudyCreateDto;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DataJpaTest
@ActiveProfiles("test")
class StudyPersistenceAdapterTest {

    @MockBean
    MemberStudyRepository memberPostRepository;

    @Autowired
    StudyRepository studyRepository;

    @Autowired
    MappingTableStudyMemberRepository mappingTableStudyMemberRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("게시글 및 매핑 테이블 생성 테스트")
    public void postMemberMappingTest() {
        // given
        MemberStudyJpaEntity member1 = mock(MemberStudyJpaEntity.class);
        entityManager.persist(member1); // 영속화
        when(member1.getId()).thenReturn(1L);
        when(memberPostRepository.findById(1L)).thenReturn(Optional.of(member1));

        StudyCreateDto dto1 = new StudyCreateDto(
                member1.getId(),
                "name1",
                "desc1"
        );
        StudyCreateDto dto2 = new StudyCreateDto(
                member1.getId(),
                "name2",
                "desc2"
        );

        StudyPersistenceAdapter adapter = new StudyPersistenceAdapter(memberPostRepository,
                studyRepository, mappingTableStudyMemberRepository);


        // when
        adapter.createStudy(dto1);
        adapter.createStudy(dto2);

        // then
        assertThat(studyRepository.findById(1L).get().getName()).isEqualTo(dto1.getName());
        assertThat(studyRepository.findById(2L).get().getName()).isEqualTo(dto2.getName());
    }
}
