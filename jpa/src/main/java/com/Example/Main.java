package com.Example;

import com.Example.Enteties.User;
import com.Example.dtos.UserDto;
import jakarta.persistence.*;

import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        //Första meny
        System.out.println("Välj vilken skola du går på");

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
