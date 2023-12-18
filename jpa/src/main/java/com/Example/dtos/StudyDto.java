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
    public static void getEnglishQuestions(User user) {

        List<StudyQuestion> studyQuestionsEnglish = new ArrayList<>();
        inTransaction((entityManager) -> {
            String queryString = """
                        
                    SELECT u FROM StudyQuestion u where u.subject.id = 1
                        """;
                var query = entityManager.createQuery(queryString, StudyQuestion
                        .class);
                studyQuestionsEnglish.
                        addAll(query.getResultList());
                studyQuestionsEnglish.forEach(question -> System.out.println("question: " + question.getQuestion() +  " Answer " + question.
                        getCorrectAnswer()));
            });
        int score = 0;
            for (StudyQuestion question : studyQuestionsEnglish){
                System.out.println(question.
                        getQuestion());
                String answer = sc.nextLine();
                if(answer.equals(question.getCorrectAnswer()))
                    score +=1;
            }

        System.out.println(" Du fick " + score + " poäng");
        Subject english = SubjectDto.getSubject(1);
        int finalScore = score;
        inTransaction((entityManager) -> {
                entityManager.persist(new Score(user, english, finalScore));
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