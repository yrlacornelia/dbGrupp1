package com.Example;

import java.util.Scanner;

public class Main {
    //todo
     // 4. Om vi har mycket tid: Jämföra skolornas resultat
    // 5. Timestamp + snygga till + bara highscore
    // 6. Om vi har mycket tid: skapa frågor
    // 8. efter glosor kommer man tillbaka till login de vill vi inte
    // 9. tillbaka knappar där det behövs
    // 10. bara visa elever och uppdatera elever som går i rätt skola
    // 11. transaction så man kan hämta
    // 12. Städa ex byta if till switch

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        //Första meny


        Menu.firstMenu();

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

