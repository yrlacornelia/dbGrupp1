package com.Example;

import com.Example.Enteties.User;
import com.Example.dtos.UserDto;
import jakarta.persistence.EntityManager;

import java.util.Scanner;

public class Menu {

    public static Scanner sc = new Scanner(System.in);

    public static void secondMenu() {
        System.out.println("Välj ett alternativ:");
        while (true) {
            System.out.println("1. Logga in");
            System.out.println("2. Gå till adminsidan");
            String userSelection = sc.nextLine();
            if (userSelection.equals("1")) {
                loginMenu();
            }else if (userSelection.equals("2")) {
                adminMenu();
            } else {
                System.out.println("Välj 1 eller 2");
            }
        }
    }

    public static void adminMenu() {
        while (true) {
            EntityManager em = JPAUtil.getEntityManager();
            System.out.println("Välj ett alternativ:");
            System.out.println("1: Lägg till student");
            System.out.println("2: Visa student");
            System.out.println("3: Ta bort student");
            System.out.println("4: Visa alla studenter");
            System.out.println("5: Uppdatera en student");
            System.out.println("6: Gå tillbaka till föregående meny");
            String userSelection = sc.nextLine();
            if (userSelection.equals("1"))
                UserDto.addNewStudent();
            if (userSelection.equals("2"))
                UserDto.showStudent();
            if (userSelection.equals("3"))
                UserDto.removeStudent();
            if (userSelection.equals("4"))
                UserDto.getAllStudents();
            if (userSelection.equals("5"))
                UserDto.updateStudent();
            if (userSelection.equals("6"))
                Menu.secondMenu();
            em.close();
        }
    }

    public static void loginMenu(){
        EntityManager em = JPAUtil.getEntityManager();
        System.out.println("Ange ditt studentid:");
        Integer userId = Integer.valueOf(sc.nextLine());
        User user = UserDto.getStudent(userId);
        if (user != null) {
            System.out.println("Inloggad som: " + user.getName());
        } else {
            System.out.println("Inloggningen misslyckades");
        }
    }

    public static void thirdMenu(User user){
        System.out.println(user.getName());
        System.out.println("1. Se ditt highscore");
        System.out.println("2. Öva på glosor");
        String userSelection = sc.nextLine();
        if(userSelection.equals("1"))
            highscoreMenu();
        if(userSelection.equals("2"))
            studyMenu();
    }

    private static void highscoreMenu() {
        System.out.println("highscore:");
    }

    private static void studyMenu() {
        System.out.println("study");
    }
}


