import io.github.cdimascio.dotenv.Dotenv;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        String url = dotenv.get("CONNECTION_STRING");

        if (url == null) {
            System.out.println("CONNECTION_STRING not found in environment variables.");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        try (Connection connection = DriverManager.getConnection(url)) {
            System.out.print("Enter search term: ");
            String name = scanner.nextLine();

            // Validate user input
            if (name == null || name.isEmpty()) {
                System.out.println("Invalid input.");
                return;
            }

            String query = "SELECT * FROM country WHERE country_name=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, name);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    // Process the result set
                    List<Country> countries = new ArrayList<>();
                    while (resultSet.next()) {
                        int id = resultSet.getInt("country_id");
                        String country_name = resultSet.getString("country_name");
                        String language_code = resultSet.getString("language_code");
                        Country country = new Country(id, country_name, language_code);
                        countries.add(country);
                    }
                    countries.forEach(System.out::println);
                }
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while connecting to the database.");
            e.printStackTrace();
        }
    }
}
