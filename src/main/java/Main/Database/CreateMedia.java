package Main.Database;

import Main.HomePage;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;
import org.bson.types.ObjectId;

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
            System.out.println("Enter the subgenre of the media: 1 - Science Fiction, 2 - Fantasy, 3 - Mystery, 4 - Horror, 5 - Drama, 6 - Mythology");
            genre = scanner.nextLine();
            genre = switch (genre) {
                case "1" -> "Science Fiction";
                case "2" -> "Fantasy";
                case "3" -> "Mystery";
                case "4" -> "Horror";
                case "5" -> "Drama";
                case "6" -> "Mythology";
                default -> "None";
            };
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
        return ID.toString();
    }

    private static void createMediaEntry(String title, String author, String genre, String type, String date, String publisher, String description, String image){
        try (MongoClient mongoClient = MongoClients.create(HomePage.connectionString)) {
            MongoDatabase db = mongoClient.getDatabase("LibraHub");
            MongoCollection<Document> collection = db.getCollection("Books");

            try {
                InsertOneResult result = collection.insertOne(new Document()
                        .append("_id", new ObjectId())
                        .append("bookid", createRandomID())
                        .append("title", title)
                        .append("author", author)
                        .append("genre", genre)
                        .append("type", type)
                        .append("date", date)
                        .append("publisher", publisher)
                        .append("description", description)
                        .append("image","/"+ image)
                        .append("borrowed", false));
                System.out.println("Inserted: " + result);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
