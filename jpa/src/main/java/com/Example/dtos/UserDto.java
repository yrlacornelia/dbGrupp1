package com.Example.dtos;

import com.Example.Enteties.User;
import com.Example.JPAUtil;
import com.Example.Menu;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

public class UserDto {

    static Scanner sc = new Scanner(System.in);
    private static Integer id;
    private String name;



    public static void addNewStudent() {
        System.out.println("Välj namn på ny användare:");
        String userName = sc.nextLine();
        inTransaction((entityManager) -> {
            entityManager.persist(new User(userName));
        });
    }

    public static void showStudent(){
        System.out.println("Vill du visa student baserat på id eller namn?");
        System.out.println("1: Namn");
        System.out.println("2: Id");
        String userChoice = sc.nextLine();
        if(userChoice.equals("1")){
            System.out.println("Skriv namn");
            String name = sc.nextLine();
            inTransaction((entityManager) -> {
                String queryString = """
                        SELECT u FROM User u where u.name = :name
                        """;
                var query = entityManager.createQuery(queryString, User.class);
                query.setParameter("name", name);
                List<User> user = query.getResultList();
                user.forEach(user1 -> System.out.println("Name: " + user1.getName() + " \n Id: " + user1.getId()));
            });
            // sök med via namn
        } else if (userChoice.equals("2")) {
            System.out.println("Skriv in id:");
            String userId = sc.nextLine();
            inTransaction(entityManager -> {
                User user = entityManager.find(User.class, userId);
                if (user != null){
                    System.out.println("Välkommen " + user.getName());
                } else{
                    // skapa ny användare
                    System.out.println("Vi kunde inte hitta dig");
                    System.out.println("Vänligen ange ditt namn:");
                }
            });
            // sök via id
        } else{
            System.out.println("Du måste välja alternativ 1 eller 2");
        }
    }


    public static User getStudent(Integer userId) {
        AtomicReference<User> user = new AtomicReference<>(null);
        inTransaction(entityManager -> {
            user.set(entityManager.find(User.class, userId));
        });
        return user.get();
    }


    public static void getAllStudents(){
        inTransaction((entityManager) -> {
            String queryString = """
                        SELECT u FROM User u
                        """;
            var query = entityManager.createQuery(queryString, User.class);
            List<User> user = query.getResultList();
            user.forEach(user1 -> System.out.println("Name: " + user1.getName() +  " Id: " + user1.getId()));
        });}

    public static void removeStudent(){
        getAllStudents();
        System.out.println("Ange ID som du vill ta bort");
        String id = sc.nextLine();
        inTransaction((entityManager -> {
            User user = entityManager.find(User.class, id);
            if(user != null)
                entityManager.remove(user);
            else
                System.out.println("Användare med id: " + id + " finns inte");
        }
        ));}

    public static void updateStudent(){
        getAllStudents();
        System.out.println("Ange ID som du vill uppdatera:");
        String id = sc.nextLine();
        System.out.println("Uppdatera namn:");
        String name = sc.nextLine();
        inTransaction((entityManager -> {
            User user = entityManager.find(User.class, id);
            if(user != null)
                user.setName(name);
            else
                System.out.println("Användare med id: " + id + " finns inte");
        }
        ));}


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
