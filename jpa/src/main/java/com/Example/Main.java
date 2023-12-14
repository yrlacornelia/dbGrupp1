package com.Example;

import com.Example.Enteties.User;
import com.Example.dtos.UserDto;
import jakarta.persistence.*;

import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        // add users
            while (true) {
                EntityManager em = JPAUtil.getEntityManager();
                System.out.println("Välj ett alternativ");
                System.out.println("1: Lägg till student");
                System.out.println("2: Visa student");
                System.out.println("3: Ta bort student");
                System.out.println("4: Visa alla studenter");
                System.out.println("5: Uppdatera en student");
                String userSelection = sc.nextLine();
                if (userSelection.equals("1"))
                    UserDto.addNewStudent();
                if (userSelection.equals("2"))
                    UserDto.showStudent();
                if (userSelection.equals("3"))
                    UserDto.removeStudent();
                if(userSelection.equals("4"))
                    UserDto.getAllStudents();
                if(userSelection.equals("5"))
                    UserDto.updateStudent();
                em.close();
            }





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
