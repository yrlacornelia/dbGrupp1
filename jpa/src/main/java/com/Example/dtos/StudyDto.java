package com.Example.dtos;

import com.Example.Enteties.Score;
import com.Example.Enteties.StudyQuestion;
import com.Example.Enteties.Subject;
import com.Example.Enteties.User;
import com.Example.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

/**
 * DTO for {@link com.Example.Enteties.StudyQuestion}
 */
public class StudyDto {
    static Scanner sc = new Scanner(System.in);

    public static void getQuestions(User user, int subjectId) {
        List<StudyQuestion> studyQuestions = new ArrayList<>();
        InTransactionMethod.inTransaction((entityManager) -> {
            String queryString = """
                        
                    SELECT u FROM StudyQuestion u where u.subject.id = :subjectId
                        """;
                var query = entityManager.createQuery(queryString, StudyQuestion
                        .class);
                query.setParameter("subjectId", subjectId);
                studyQuestions.addAll(query.getResultList());
            });
        int score = 0;
            for (StudyQuestion question : studyQuestions){
                System.out.println(question.
                        getQuestion());
                String answer = sc.nextLine();
                if(answer.equalsIgnoreCase(question.getCorrectAnswer()))
                    score +=1;
            }

        System.out.println(" Du fick " + score + " poäng");
        Subject subject = SubjectDto.getSubject(subjectId);
        int finalScore = score;
        InTransactionMethod.inTransaction((entityManager) -> {
                entityManager.persist(new Score(user, subject, finalScore));
            });
        }


}