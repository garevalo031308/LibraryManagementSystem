package Main;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Transaction {
    private final StringProperty title;
    private final StringProperty author;
    private final StringProperty dateCheckedOut;
    private final StringProperty dateDue;

    private final StringProperty status;

    public Transaction(String title, String author, String dateCheckedOut, String dateDue, StringProperty status){
        this.title = new SimpleStringProperty(title);
        this.author = new SimpleStringProperty(author);
        this.dateCheckedOut = new SimpleStringProperty(dateCheckedOut);
        this.dateDue = new SimpleStringProperty(dateDue);
        this.status = status;
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

    public String getDateDue() {
        return dateDue.get();
    }

    public StringProperty dateDueProperty() {
        return dateDue;
    }

    public String getDateCheckedOut() {
        return dateCheckedOut.get();
    }

    public StringProperty dateCheckedOutProperty() {
        return dateCheckedOut;
    }

    public String getStatus() {
        return status.get();
    }

    public StringProperty statusProperty() {
        return status;
    }
}
