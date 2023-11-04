package com.devdive.backend.study2.adapter.out;

import com.devdive.backend.persistance.entities.MemberJpaEntity;
import com.devdive.backend.persistance.entities.StudyJpaEntity;
import com.devdive.backend.persistance.repository.MemberRepository;
import com.devdive.backend.persistance.repository.StudyMemberRepository;
import com.devdive.backend.persistance.repository.StudyRepository;
import com.devdive.backend.study2.domain.Study;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({StudyPersistenceJpaAdapter.class, StudyMapper.class})
class StudyPersistenceJpaAdapterTest {

    @Autowired
    private StudyPersistenceJpaAdapter studyPersistenceJpaAdapter;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private StudyMemberRepository studyMemberRepository;

    @Autowired
    private StudyRepository studyRepository;

    @Test
    @Transactional
    void save() {
        long ownerId = 1L;
        String name = "테스트 제목";
        String description = "테스트 서브 제목";
        Study study = Study.init(ownerId, name, description);
        MemberJpaEntity memberJpaEntity = memberRepository.save(new MemberJpaEntity(1L, "test", "test@mail.com", ""));

        studyPersistenceJpaAdapter.save(study);

        Optional<StudyJpaEntity> optionalStudyJpaEntity = studyRepository.findById(study.getId().getValue());
        assertThat(optionalStudyJpaEntity).isNotEmpty();

        StudyJpaEntity studyJpaEntity = optionalStudyJpaEntity.get();
        assertThat(studyJpaEntity.getDescription()).isEqualTo(description);
        assertThat(studyJpaEntity.getName()).isEqualTo(name);
        assertThat(studyMemberRepository.readStudies(memberJpaEntity.getId()))
                .isNotEmpty()
                .hasSize(1);
    }
}
