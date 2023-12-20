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


   public static void compareScoreResult(int id, String schoolName){
       InTransactionMethod.inTransaction((entityManager) -> {
           String queryStringIths = """
                        
                    SELECT AVG(u.points) FROM Score u where user.school.id = :id
                        """;
           var query = entityManager.createQuery(queryStringIths, Double.class);
           query.setParameter("id", id);
           List<Double> listOfUsersFromITHS = query.getResultList();
           listOfUsersFromITHS.forEach(average -> System.out.println(schoolName + " Medelvärde: " +  average));
       });
   }



    public static void getHighscore(User user, int subjectId) {
        InTransactionMethod.inTransaction((entityManager) -> {
            int userId = user.getId();
            String queryString = """
                        SELECT u FROM Score u where u.user.id = :userId and u.subject.id = :subjectId
                        """;
            var query = entityManager.createQuery(queryString, Score.class);
            query.setParameter("subjectId", subjectId);
            query.setParameter("userId", userId);
            List<Score> listOfScores = query.getResultList();
            listOfScores.forEach(score -> System.out.println("Poäng: " + score.getPoints() + " Datum: " + score.getCreatedAt()));
            if(listOfScores.isEmpty()){
                System.out.println("Inga poäng registrerade\n");
            }
        });
    }
}
