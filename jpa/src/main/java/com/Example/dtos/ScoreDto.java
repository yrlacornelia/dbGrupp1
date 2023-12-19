package com.Example.dtos;

import com.Example.Enteties.School;
import com.Example.Enteties.Score;
import com.Example.Enteties.User;
import com.Example.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.function.Consumer;

public class ScoreDto {


   public static void compareScoreResult(){
       inTransaction((entityManager) -> {
           String quertyStringIths = """
                        
                   SELECT u FROM Score u where user.school.id = 1
                        """;
           var query = entityManager.createQuery(quertyStringIths, Score.class);
           List<Score> listOfUsersFromITHS = query.getResultList();
           listOfUsersFromITHS.forEach(user1 -> System.out.println("Score: " + user1.getPoints()));
           float ithsScore = 0;
           for(Score i : listOfUsersFromITHS){
               ithsScore += i.getPoints();
           }
           float ithsAverage = ithsScore/listOfUsersFromITHS.size();


           System.out.printf("Genomsnitt för iths = " + "%.1f\n", ithsAverage);
       

           if(listOfUsersFromITHS.isEmpty()){
               System.out.println("Inga poäng registrerade\n");
           }
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

    public static void getHighscore(User user, int subjectId) {
        inTransaction((entityManager) -> {
            int userId = user.getId();
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

}
