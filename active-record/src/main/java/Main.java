import jakarta.persistence.*;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.DB;

import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

public class Main {

    public static void main(String[] args) {
        try (DB db = Base.open("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/test", "root", "root")) {
            //https://javalite.io/activejdbc
            var countries = Country.findAll();
            countries.forEach(System.out::println);
        }
    }
}
