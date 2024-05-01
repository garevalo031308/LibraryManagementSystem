package Main;

import javafx.beans.property.StringProperty;

public class BooksProperty {

    private final StringProperty idProperty;
    private final StringProperty titleProperty;
    private final StringProperty authorProperty;
    private final StringProperty genreProperty;
    private final StringProperty typeProperty;
    private final StringProperty dateProperty;
    private final StringProperty publisherProperty;
    private final StringProperty descriptionProperty;
    private final StringProperty borrowedProperty;

    public BooksProperty(StringProperty idProperty, StringProperty titleProperty, StringProperty authorProperty, StringProperty genreProperty, StringProperty typeProperty, StringProperty dateProperty, StringProperty publisherProperty, StringProperty descriptionProperty, StringProperty borrowedProperty) {
        this.idProperty = idProperty;
        this.titleProperty = titleProperty;
        this.authorProperty = authorProperty;
        this.genreProperty = genreProperty;
        this.typeProperty = typeProperty;
        this.dateProperty = dateProperty;
        this.publisherProperty = publisherProperty;
        this.descriptionProperty = descriptionProperty;
        this.borrowedProperty = borrowedProperty;
    }

    public String getIdProperty() {
        return idProperty.get();
    }

    public StringProperty idPropertyProperty() {
        return idProperty;
    }

    public String getTitleProperty() {
        return titleProperty.get();
    }

    public StringProperty titlePropertyProperty() {
        return titleProperty;
    }

    public String getAuthorProperty() {
        return authorProperty.get();
    }

    public StringProperty authorPropertyProperty() {
        return authorProperty;
    }

    public String getGenreProperty() {
        return genreProperty.get();
    }

    public StringProperty genrePropertyProperty() {
        return genreProperty;
    }

    public String getTypeProperty() {
        return typeProperty.get();
    }

    public StringProperty typePropertyProperty() {
        return typeProperty;
    }

    public String getDateProperty() {
        return dateProperty.get();
    }

    public StringProperty datePropertyProperty() {
        return dateProperty;
    }

    public String getPublisherProperty() {
        return publisherProperty.get();
    }

    public StringProperty publisherPropertyProperty() {
        return publisherProperty;
    }

    public String getDescriptionProperty() {
        return descriptionProperty.get();
    }

    public StringProperty descriptionPropertyProperty() {
        return descriptionProperty;
    }

    public String getBorrowedProperty() {
        return borrowedProperty.get();
    }

    public StringProperty borrowedPropertyProperty() {
        return borrowedProperty;
    }
}
