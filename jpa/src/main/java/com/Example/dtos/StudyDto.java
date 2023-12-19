package com.Example.dtos;

import com.Example.Enteties.Score;
import com.Example.Enteties.StudyQuestion;
import com.Example.Enteties.Subject;
import com.Example.Enteties.User;
import com.Example.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

/**
 * DTO for {@link com.Example.Enteties.StudyQuestion}
 */
public class StudyDto {
    static Scanner sc = new Scanner(System.in);
    private Integer id;

    // lägg till så man kan skriva både stor och lite bokstav utan att det blir fel
    public static void getQuestions(User user, int subjectId) {
        List<StudyQuestion> studyQuestions = new ArrayList<>();
        inTransaction((entityManager) -> {
            String queryString = """
                        
                    SELECT u FROM StudyQuestion u where u.subject.id = :subjectId
                        """;
                var query = entityManager.createQuery(queryString, StudyQuestion
                        .class);
                query.setParameter("subjectId", subjectId);
                studyQuestions.
                        addAll(query.getResultList());
                studyQuestions.forEach(question -> System.out.println("question: " + question.getQuestion() +  " Answer " + question.
                        getCorrectAnswer()));
            });
        int score = 0;
            for (StudyQuestion question : studyQuestions){
                System.out.println(question.
                        getQuestion());
                String answer = sc.nextLine();
                if(answer.equals(question.getCorrectAnswer()))
                    score +=1;
            }

        System.out.println(" Du fick " + score + " poäng");
        Subject subject = SubjectDto.getSubject(subjectId);
        int finalScore = score;
        inTransaction((entityManager) -> {
                entityManager.persist(new Score(user, subject, finalScore));
            });
        }




    static void inTransaction(Consumer<EntityManager> work) {
        try (EntityManager entityManager = JPAUtil.getEntityManager()) {
            EntityTransaction transaction = entityManager.getTransaction();
            try {
                transaction.begin();
                work.accept(entityManager);
                transaction.commit();
            } catch (Exception e) {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
                throw e;
            }
        }
    }
}