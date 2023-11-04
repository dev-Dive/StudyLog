package com.devdive.backend.study2.adapter.out;

import com.devdive.backend.persistance.entities.StudyJpaEntity;
import com.devdive.backend.persistance.entities.StudyMemberJpaEntity;
import com.devdive.backend.study2.domain.People;
import com.devdive.backend.study2.domain.Person;
import com.devdive.backend.study2.domain.Role;
import com.devdive.backend.study2.domain.Study;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
class StudyMapper {

    public Study mapToDomainEntity(StudyJpaEntity jpaEntity) {
        Study.StudyId studyId = mapToStudyId(jpaEntity);
        Study.StudyData studyData = mapToStudyData(jpaEntity);
        People people = mapToPeople(jpaEntity);
        return new Study(studyId, studyData, people);
    }

    private Study.StudyId mapToStudyId(StudyJpaEntity jpaEntity) {
        return new Study.StudyId(jpaEntity.getId());
    }

    private Study.StudyData mapToStudyData(StudyJpaEntity jpaEntity) {
        return new Study.StudyData(jpaEntity.getName(), jpaEntity.getDescription());
    }

    private Person mapToPerson(StudyMemberJpaEntity jpaEntity) {
        Person.PersonId id = new Person.PersonId(jpaEntity.getStudy().getId());
        Role role = Role.findByName(jpaEntity.getRole());
        return new Person(id, role);
    }

    private People mapToPeople(StudyJpaEntity jpaEntity) {
        List<Person> people = jpaEntity.getStudyMembers()
                .stream()
                .map(this::mapToPerson)
                .collect(Collectors.toList());
        return new People(people);
    }

    public StudyJpaEntity mapToStudyJpaEntity(Study entity) {
        StudyJpaEntity jpaEntity = new StudyJpaEntity();
        jpaEntity.setDescription(entity.getStudyData().getDescription());
        jpaEntity.setName(entity.getStudyData().getName());
        return jpaEntity;
    }

    public Map<Person.PersonId, Role> toRoleByIds(Study study) {
        return study.getPeople()
                .getList()
                .stream()
                .collect(Collectors.toMap(Person::getId, Person::getRole));
    }

    public List<Long> toIds(Study study) {
        return study.getPeople()
                .getList()
                .stream()
                .map(Person::getId)
                .map(Person.PersonId::getValue)
                .toList();
    }
}
