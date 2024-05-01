package Main.User;

import javafx.beans.property.StringProperty;

public class Users {

    private final StringProperty id;
    private final StringProperty firstname;
    private final StringProperty lastname;
    private final StringProperty email;
    private final StringProperty password;
    private final StringProperty type;

    public Users(StringProperty id, StringProperty firstname, StringProperty lastname, StringProperty email, StringProperty password, StringProperty type) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.type = type;
    }


    public String getType() {
        return type.get();
    }

    public StringProperty typeProperty() {
        return type;
    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public String getFirstname() {
        return firstname.get();
    }

    public StringProperty firstnameProperty() {
        return firstname;
    }

    public String getLastname() {
        return lastname.get();
    }

    public StringProperty lastnameProperty() {
        return lastname;
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

}
