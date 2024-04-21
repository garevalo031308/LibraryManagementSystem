package Main.Daniel;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import javafx.stage.Stage;
import javafx.stage.Modality;
import java.sql.Statement;
import com.pos.pos.Admin.CustomerFolder.CreateCustomerPopup;
import com.pos.pos.Pages.StartPage;

public class CreateAssociatePopup extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }// end main



    public void start(Stage stage)
    {
        Stage popup = new Stage();
        Group root = new Group();
        Scene scene = new Scene(root, 327, 445);

        popup.initModality(Modality.APPLICATION_MODAL);
        popup.initOwner(stage);

        Rectangle titlerect = new Rectangle();
        titlerect.setFill(Paint.valueOf("#6b6b6b"));
        titlerect.setHeight(60);
        titlerect.setWidth(327);

        Label title = new Label("Create an Associate");
        title.setPrefSize(262,17);
        title.setLayoutX(33);
        title.setLayoutY(9);
        title.setFont(Font.font(29));

        Label idLabel = new Label("ID:");
        idLabel.setLayoutX(25);
        idLabel.setLayoutY(75);
        idLabel.setFont(Font.font(18));

        TextField idField = new TextField();
        idField.setLayoutX(168);
        idField.setLayoutY(75);
        idField.setDisable(true);

        idField.setText(createID());

        Label firstLabel = new Label("First Name");
        firstLabel.setLayoutX(23);
        firstLabel.setLayoutY(110);
        firstLabel.setFont(Font.font(18));

        TextField firstField = new TextField();
        firstField.setLayoutX(168);
        firstField.setLayoutY(110);

        Label lastLabel = new Label("Last Name:");
        lastLabel.setLayoutX(24);
        lastLabel.setLayoutY(147);
        lastLabel.setFont(Font.font(18));

        TextField lastField = new TextField();
        lastField.setLayoutX(168);
        lastField.setLayoutY(148);

        Label emailLabel = new Label("Email:");
        emailLabel.setLayoutX(23);
        emailLabel.setLayoutY(182);
        emailLabel.setFont(Font.font(18));

        TextField emailField = new TextField();
        emailField.setLayoutX(168);
        emailField.setLayoutY(183);

        Label phoneLabel = new Label("Phone Number:");
        phoneLabel.setLayoutX(23);
        phoneLabel.setLayoutY(215);
        phoneLabel.setFont(Font.font(18));

        TextField phoneField = new TextField();
        phoneField.setLayoutX(168);
        phoneField.setLayoutY(216);

        Label passLabel = new Label("Password:");
        passLabel.setLayoutX(23);
        passLabel.setLayoutY(245);
        passLabel.setFont(Font.font(18));

        TextField passField = new TextField();
        passField.setLayoutX(168);
        passField.setLayoutY(246);

        ToggleGroup position = new ToggleGroup();

        RadioButton cashier = new RadioButton("Cashier - 1");
        cashier.setLayoutX(29);
        cashier.setLayoutY(298);
        cashier.setId("1");

        RadioButton tech = new RadioButton("Tech - 2");
        tech.setLayoutX(29);
        tech.setLayoutY(332);
        tech.setId("2");

        RadioButton print = new RadioButton("Print - 3");
        print.setLayoutX(29);
        print.setLayoutY(366);
        print.setId("3");

        RadioButton sales = new RadioButton("Sales Manager - 4");
        sales.setLayoutX(171);
        sales.setLayoutY(298);
        sales.setId("4");

        RadioButton techsup = new RadioButton("Tech Sup - 5");
        techsup.setLayoutX(171);
        techsup.setLayoutY(332);
        techsup.setId("5");

        RadioButton printsup = new RadioButton("Print Sup - 6");
        printsup.setLayoutX(171);
        printsup.setLayoutY(366);
        printsup.setId("6");

        position.getToggles().addAll(cashier, tech, print, sales, techsup, printsup);
        position.selectToggle(cashier);

        Button create = new Button("Create Associate");
        create.setLayoutX(106);
        create.setLayoutY(406);

        root.getChildren().addAll(idLabel, firstLabel, lastLabel, emailLabel, phoneLabel, passLabel);
        root.getChildren().addAll(idField, firstField, lastField, emailField, phoneField, passField);
        root.getChildren().addAll(cashier,tech,print,sales,techsup,printsup,create,titlerect, title);

        popup.setScene(scene);
        popup.setTitle("Create An Associate");
        popup.show();

        create.setOnAction(e->{
            String pass = passField.getText();
            String first = firstField.getText();
            String last = lastField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();
            String posID = getPositionID(position);
            String pos = getPositionName(position);
            String rewardsID = CreateCustomerPopup.createRewardsID();
            if (!pass.isEmpty() && !first.isEmpty() && !last.isEmpty() && !email.isEmpty() && !phone.isEmpty()) {
                CreateCustomerPopup.createCustomerDB("3308866056", "Associate", first, last, email, phone);
                createAssociateDB("2011176", "3308866056", email, first, last, pass, phone, pos, posID);
                popup.close();
            }

        });


    }//end stage
    private static void createAssociateDB(String ID, String rewards, String email, String first, String last, String pass, String phone, String position, String positionID){
        String query = "INSERT INTO Associates (EmployeeID, RewardsID, Password, FirstName, LastName, Email, Phone, Position, PositionID) " +
                "VALUES ('" + ID + "','" + rewards + "','" + pass + "','" + first + "','" + last + "','" + email + "','" + phone + "','" + position + "','" + positionID + "');";

        try{
            Statement s = StartPage.connection.createStatement();
            s.execute(query);
            System.out.println("Associate created");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    private static String createID(){
        StringBuilder ID = new StringBuilder();
        for (int i = 0; i < 7; i++) {
            Random rand = new Random();
            ID.append(rand.nextInt(0, 9));
        }
        if(!checkForDuplicateID(ID.toString())){
            System.out.println("No duplicate ID found");
            return ID.toString();
        } else {
            System.out.println("Duplicate ID found");
            return createID();
        }
    }
    private static boolean checkForDuplicateID(String ID){
        String query = "SELECT * FROM Associates";
        try{
            Statement s = StartPage.connection.createStatement();
            ResultSet rs = s.executeQuery(query);
            while(rs.next()){
                String DBID = rs.getString(1);
                System.out.println(DBID);
                if(DBID.equals(ID)){
                    return true;
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }


    private static String getPositionID(ToggleGroup tg){
        RadioButton rb = (RadioButton) tg.getSelectedToggle();
        return rb.getId();
    }

    private static String getPositionName(ToggleGroup tg){
        RadioButton rb = (RadioButton) tg.getSelectedToggle();
        String ID = rb.getId();
        switch (ID) {
            case "1" -> {
                return "Cashier";
            }
            case "2" -> {
                return "Tech";
            }
            case "3" -> {
                return "Print";
            }
            case "4" -> {
                return "Sales Manager";
            }
            case "5" -> {
                return "Tech Sup";
            }
            case "6" -> {
                return "Print Sup";
            }
        }
        return ID;
    }
}


}//end class
