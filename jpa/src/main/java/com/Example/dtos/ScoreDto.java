package com.Example.dtos;

import com.Example.Enteties.Score;
import com.Example.Enteties.User;
import com.Example.JPAUtil;
import com.Example.Menu;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.function.Consumer;

public class ScoreDto {

    public static void setScore(){


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

    public static void getEnglishHighscores(User user) {
        inTransaction((entityManager) -> {
            int userId = user.getId();
            int subjectId = 1;
            String queryString = """
                        SELECT u FROM Score u where u.user.id = :userId and u.subject.id = :subjectId
                        """;
            var query = entityManager.createQuery(queryString, Score.class);
            query.setParameter("subjectId", subjectId);
            query.setParameter("userId", userId);
            List<Score> listOfScores = query.getResultList();
            listOfScores.forEach(user1 -> System.out.println("Score: " + user1.getPoints()));
            if(listOfScores.isEmpty()){
                System.out.println("Inga poäng registrerade\n");
            }


        });
    }

    public static void getGeographyHighscore(User user) {
    }

    public static void getMathHighscore(User user) {
    }
}