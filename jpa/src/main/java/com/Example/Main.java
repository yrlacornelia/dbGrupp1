package com.Example;

import com.Example.Enteties.User;
import com.Example.dtos.UserDto;
import jakarta.persistence.*;

import java.util.Scanner;

public class Main {
    //todo
    // 2. Skoltabell + entety och dto
    // 3. Städa ex byta if till switch
    // 4. Byta mattefrågorna
    // 5. Timestamp + snygga till + bara highscore
    // 6. Om vi har mycket tid: skapa frågor
    // 7. Om vi har mycket tid: Jämföra skolornas resultat
    // 8. efter glosor kommer man tillbaka till login de vill vi inte
    // 9. tillbaka knappar där det behövs

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        //Första meny


        Menu.secondMenu();

/*        System.out.print("Enter search term: ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();*/

        // Validate user input
/*        if (name == null || name.isEmpty()) {
            System.out.println("Invalid input.");
            return;
        }*/

/*        TypedQuery<Country> query = em.createQuery("SELECT c FROM Country c WHERE c.countryName = :name", Country.class);
        query.setParameter("name", name);
        List<Country> countries = query.getResultList();
        countries.forEach(System.out::println);*/

    }
}

