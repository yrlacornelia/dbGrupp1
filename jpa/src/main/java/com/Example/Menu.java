package com.Example;
import com.Example.Enteties.User;
import com.Example.dtos.SchoolDto;
import com.Example.dtos.ScoreDto;
import com.Example.dtos.StudyDto;
import com.Example.dtos.UserDto;
import jakarta.persistence.EntityManager;
import java.util.Scanner;

public class Menu {

    public static Scanner sc = new Scanner(System.in);

    public static void chooseOrAddSchoolMenu(){
        while (true){
            System.out.println("1: Välja skola");
            System.out.println("2: Lägg till skola");
            String input = sc.nextLine();
            switch (input) {
                case "1" -> chooseSchool();
                case "2" -> SchoolDto.createSchool();
                default -> System.out.print("Välj mellan alternativ 1 eller 2");
            }
        }
    }

    public static void chooseSchool(){
        while (true) {
            System.out.println("Välj skola:");
            SchoolDto.getAllSchools();
            System.out.println("0: Gå tillbaka");
            String input = sc.nextLine();
            int schoolId;
            schoolId = Integer.parseInt(input);
            if(schoolId == 0)
                chooseOrAddSchoolMenu();
            else if(SchoolDto.getSchool(schoolId) != null)
                loginOrAdminMenu(schoolId);
            else
                System.out.println("Välj skola med giltigt id");
        }
    }

    public static void loginOrAdminMenu(int schoolId) {
        System.out.println("Välj ett alternativ:");
        while (true) {
            System.out.println("1. Logga in");
            System.out.println("2. Gå till adminsidan");
            System.out.println("3. Gå tillbaka");
            String userSelection = sc.nextLine();
            switch(userSelection){
                case "1" -> loginMenu(schoolId);
                case "2" -> adminMenu(schoolId);
                case "3" -> Menu.chooseSchool();
                default -> System.out.println("Välj 1 eller 2");

            }
        }
    }

    public static void adminMenu(int schoolId) {
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
            switch (userSelection) {
                case "1" -> UserDto.addNewStudent(schoolId);
                case "2" -> UserDto.showStudent(schoolId);
                case "3" -> UserDto.removeStudent(schoolId);
                case "4" -> UserDto.getAllStudents(schoolId);
                case "5" -> UserDto.updateStudent(schoolId);
                case "6" -> Menu.loginOrAdminMenu(schoolId);
                default -> System.out.println("Välj ett giltigt alternativ:");
            }
            em.close();
        }
    }

    public static void loginMenu(int schoolId){
        System.out.println("Ange ditt studentid:");
        Integer userId = Integer.valueOf(sc.nextLine());
        User user = UserDto.getStudent(userId, schoolId);
        if (user != null) {
            System.out.println("Inloggad som: " + user.getName());
            studentPracticeMenu(user);
        } else {
            System.out.println("Inloggningen misslyckades");
        }
    }

    public static void studentPracticeMenu(User user) {
        while (true) {
        System.out.println("1. Se ditt highscore");
        System.out.println("2. Välj ämne att öva på");
        System.out.println("3: Jämför skolornas resultat");
        System.out.println("4. Logga ut");
        String userSelection = sc.nextLine();
        switch (userSelection) {
            case "1" -> highScoreMenu(user);
            case "2" -> chooseSubjectMenu(user);
            case "3" -> SchoolDto.getSchoolHighscore();
            case "4" -> chooseSchool();
            default -> System.out.println("Välj ett giltigt alternativ:");
            }
        }
    }

    public static void chooseSubjectMenu(User user){
        while (true) {
            System.out.println("1: Engelska");
            System.out.println("2: Geografi");
            System.out.println("3: Matte");
            System.out.println("4. Gå tillbaka");
            String userInput = sc.nextLine();
            int subjectId = Integer.parseInt(userInput);
            switch (subjectId){
                case 1,2,3 ->  StudyDto.getQuestions(user, subjectId);
                case 4 -> studentPracticeMenu(user);
                default -> System.out.println("Välj ett värde mellan 1-4");
            }
        }
    }

    public static void highScoreMenu(User user) {
        while (true) {
            System.out.println("Välj ett ämne:");
            System.out.println("1: Visa Highscore i Engelska");
            System.out.println("2: Visa Highscore i Geografi");
            System.out.println("3: Visa Highscore i Matematik");
            System.out.println("4: Gå tillbaka");
            String userInput = sc.nextLine();
            switch (userInput) {
                case "1", "2", "3" -> ScoreDto.getHighscore(user, Integer.parseInt(userInput));
                case "4" -> studentPracticeMenu(user);
                default -> System.out.println("Välj ett giltigt alternativ:");
            }
        }
    }
}
