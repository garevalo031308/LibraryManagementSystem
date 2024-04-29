package Main.Database;

import Main.Gabriel.Books;
import Main.HomePage;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


// This is separate to the creating media page
public class CreateMedia {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the title of the media: ");
        String title = scanner.nextLine();

        System.out.println("Enter the author of the media: ");
        String author = scanner.nextLine();

        System.out.println("Enter the main genre of the media: 1 - Fiction, 2 - Nonfiction");
        String genre = scanner.nextLine();


        if (genre.equals("1")){
            System.out.println("Enter the subgenre of the media: 1 - Science Fiction, 2 - Fantasy, 3 - Mystery, 4 - Horror, 5 - Drama, 6 - Mythology, 0 - for none");
            genre = scanner.nextLine();
            genre = switch (genre) {
                case "1" -> "Science Fiction";
                case "2" -> "Fantasy";
                case "3" -> "Mystery";
                case "4" -> "Horror";
                case "5" -> "Drama";
                case "6" -> "Mythology";
                case "0" -> "Fiction";
                default -> "None";
            };
        } else{
            genre = "Non-Fiction";
        }


        System.out.println("Enter the type of media: 1 - Book, 2 - E-Book");
        String type = scanner.nextLine();
        type = switch (type) {
            case "1" -> "Book";
            case "2" -> "E-Book";
            default -> "None";
        };

        System.out.println("Enter date of publication as MM/DD/YYYY, if only month and year is given then do day as 01: ");
        String date = scanner.nextLine();

        System.out.println("Enter the publisher of the media: ");
        String publisher = scanner.nextLine();

        System.out.println("Enter the description of the media: ");
        // Allow user to input a paragraph
        StringBuilder description = new StringBuilder();

        System.out.println("Enter -1 to stop entering lines, 0 to reset: ");
        String line = null;
        while(scanner.hasNext()){
            line = scanner.nextLine();
            if (line.equals("-1")){
                break;
            } else if (line.equals("0")){
                description = new StringBuilder();
                continue;
            }
            description.append(line).append("\n");
        }

        System.out.println("Enter image path of the media: "); // put image in resources folder, media covers, right click image, copy path/reference, select path from source root, then copy and paste it
        String image = scanner.nextLine(); // Do not need to add the / before hand, I already have the code doing that

        createMediaEntry(title, author, genre, type, date, publisher, description.toString(), image);
    }

    private static String createRandomID(){
        // This will create a random ID for the media
        StringBuilder ID = new StringBuilder();
        for(int i = 0; i < 7; i++){
            Random rand = new Random();
            ID.append(rand.nextInt(0,9));
        }
        ArrayList<Books> books = bookDatabase();
        for (Books book : books) {
            if (book.getID().contentEquals(ID.toString())) {
                createRandomID();
            }
        }
        return ID.toString();
    }

    private static void createMediaEntry(String title, String author, String genre, String type, String date, String publisher, String description, String image){
        try (Connection connection = HomePage.getConnection()) {
            String sql = "INSERT INTO Books (id, title, author, genre, type, date, publisher, description, image, borrowed) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, createRandomID());
            statement.setString(2, title);
            statement.setString(3, author);
            statement.setString(4, genre);
            statement.setString(5, type);
            statement.setString(6, date);
            statement.setString(7, publisher);
            statement.setString(8, description);
            statement.setString(9, "/" + image);
            statement.setBoolean(10, false);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<Books> bookDatabase() {
        ArrayList<Books> books = new ArrayList<>();

        try (Connection connection = HomePage.getConnection()) {
            String sql = "SELECT * FROM Books";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Books book = new Books();
                book.setID(String.valueOf(resultSet.getInt("id")));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setGenre(resultSet.getString("genre"));
                book.setType(resultSet.getString("type"));
                book.setDate(resultSet.getString("date"));
                book.setPublisher(resultSet.getString("publisher"));
                book.setDescription(resultSet.getString("description"));
                book.setImage(resultSet.getString("image"));
                book.setBorrowed(resultSet.getBoolean("borrowed"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }

}
