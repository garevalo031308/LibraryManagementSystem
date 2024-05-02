package Main.User;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Transaction {
    private final StringProperty title;
    private final StringProperty author;
    private final StringProperty dateCheckedOut;
    private final StringProperty dateDue;
    private final StringProperty transactionID;
    private final StringProperty bookID;
    private final StringProperty status;

    public Transaction(String title, String author, String dateCheckedOut, String dateDue, StringProperty bookID, StringProperty transactionID, StringProperty status){
        this.title = new SimpleStringProperty(title);
        this.author = new SimpleStringProperty(author);
        this.dateCheckedOut = new SimpleStringProperty(dateCheckedOut);
        this.dateDue = new SimpleStringProperty(dateDue);
        this.bookID = bookID;
        this.transactionID = transactionID;
        this.status = status;
    }

    public Transaction(String title, String author, String dateCheckedOut, String dateDue, String status, String bookID) {
        this.title = new SimpleStringProperty(title);
        this.author = new SimpleStringProperty(author);
        this.dateCheckedOut = new SimpleStringProperty(dateCheckedOut);
        this.dateDue = new SimpleStringProperty(dateDue);
        this.transactionID = new SimpleStringProperty("0");
        this.bookID = new SimpleStringProperty(bookID);
        this.status = new SimpleStringProperty(status);
    }

    public StringProperty bookIDProperty() {
        return bookID;
    }

    public StringProperty transactionIDProperty() {
        return transactionID;
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public String getAuthor() {
        return author.get();
    }

    public StringProperty authorProperty() {
        return author;
    }


    public StringProperty dateDueProperty() {
        return dateDue;
    }


    public StringProperty dateCheckedOutProperty() {
        return dateCheckedOut;
    }


    public StringProperty statusProperty() {
        return status;
    }
}
