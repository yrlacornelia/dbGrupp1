package com.Example.dtos;

import com.Example.Enteties.School;
import com.Example.Enteties.User;
import com.Example.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

public class SchoolDto {
    public static Scanner sc = new Scanner(System.in);

    public static void getAllSchools(){
        InTransactionMethod.inTransaction((entityManager) -> {
            String queryString = """
                            SELECT u FROM School u
                        """;
            var query = entityManager.createQuery(queryString, School.class);
            List<School> listOfSchools = query.getResultList();
            listOfSchools.forEach(school -> System.out.println(school.getId() + ": " + school.getName()));
        });
    }
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
            AtomicReference<School> school = new AtomicReference<>(null);
           InTransactionMethod.inTransaction(entityManager -> {
                school.set(entityManager.find(School.class, schoolId));
            });
            return school.get();
    }

    public static void createSchool(){
        System.out.println("Välj namn på ny skola:");
        String schoolName = sc.nextLine();
        InTransactionMethod.inTransaction((entityManager) -> {
            entityManager.persist(new School(schoolName));
        });
    }
}
