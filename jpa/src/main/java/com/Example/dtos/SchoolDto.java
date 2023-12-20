package com.Example.dtos;

import com.Example.Enteties.School;
import com.Example.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

public class SchoolDto {
    
    public static void getSchoolHighscore() {
       InTransactionMethod.inTransaction((entityManager) -> {
            String queryString = """
                            SELECT u FROM School u
                        """;
            var query = entityManager.createQuery(queryString, School.class);
            List<School> listOfSchools = query.getResultList();
            listOfSchools.forEach(school -> ScoreDto.compareScoreResult(school.getId(), school.getName()));
        });
    }
    public static School getSchool(int schoolId) {
            AtomicReference<School> subject = new AtomicReference<>(null);

           InTransactionMethod.inTransaction(entityManager -> {
                subject.set(entityManager.find(School.class, schoolId));
            });

            return subject.get();


    }


}
