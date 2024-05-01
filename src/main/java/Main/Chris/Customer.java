package Main.Chris;

import javafx.beans.property.StringProperty;

public class Customer {

    private final StringProperty userID;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty email;

    public Customer(StringProperty userID, StringProperty firstName, StringProperty lastName, StringProperty email) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getUserID() {
        return userID.get();
    }

    public StringProperty userIDProperty() {
        return userID;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }


}
