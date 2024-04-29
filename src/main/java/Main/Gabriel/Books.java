package Main.Gabriel;

public class Books {
    String id;
    String title;
    String author;
    String genre;
    String type;
    String date;
    String publisher;
    String description;
    String image;
    Boolean borrowed;

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getBorrowed() {
        return borrowed;
    }

    public void setBorrowed(Boolean borrowed) {
        this.borrowed = borrowed;
    }

    @Override
    public String toString(){
        return "ID: " + id + "\nTitle: " + title + "\nAuthor: " + author + "\nGenre: " + genre + "\nType: " + type + "\nDate: " + date + "\nPublisher: " + publisher + "\nDescription: " + description + "\nImageURL: " + image + "\nBorrowed: " + borrowed;
    }
}
