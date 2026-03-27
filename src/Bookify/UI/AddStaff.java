package Bookify.UI;
import Bookify.DAO.StaffDAO;
import Bookify.controllers.Scenemanager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class AddStaff {
    private static Scene scene;
    private static Scenemanager manager;

    public AddStaff(Scenemanager manager)
    {
        this.manager=manager;
    }
    public Scene loadPage(){
    VBox root = new VBox();
    root.setSpacing(15);                     // space between children
    root.setAlignment(Pos.CENTER);           // center everything
    root.setPadding(new Insets(40));
    root.setStyle("-fx-background-color: #0F172A;");       // space from window edges

Image icon = new Image(SignUpPage.class.getResourceAsStream("/images/Main_logo.png"));   
        
    HBox title = new HBox();
    title.setSpacing(15);                     // space between children
    title.setAlignment(Pos.CENTER);

    Text text = new Text();
    text.setText("Add Employee");
    text.setFont(Font.font("Bold",50));
    text.setFill(Color.WHITESMOKE);

    ImageView imageview = new ImageView(icon);
    imageview.setScaleX(0.6);
    imageview.setScaleY(0.6);

    title.getChildren().addAll( text,imageview);
//-------------------------------------------------------
VBox.setMargin(title, new Insets(0, 0, 40, 0));

// form 
    VBox form = new VBox();
    form.setSpacing(15);                     // space between children
    form.setAlignment(Pos.CENTER);           // center everything
    form.setPadding(new Insets(40));
    form.setStyle("-fx-background-color: #1E293B;");       // !!!!!!!!!!!!

    GridPane grid = new GridPane();
    grid.setHgap(15);      // horizontal space between columns
    grid.setVgap(15);      // vertical space between rows
    grid.setAlignment(Pos.CENTER);
//username

    Text usernameText = new Text();
    usernameText.setText("Username");
    usernameText.setFont(Font.font("Bold",30));
    usernameText.setFill(Color.WHITESMOKE);


    TextField usernameField = new TextField();
    usernameField.setPromptText("Username");
    usernameField.setMaxWidth(300);

// password

    Text passText = new Text();
    passText.setText("Password");
    passText.setFont(Font.font("Bold",30));
    passText.setFill(Color.WHITESMOKE);

    PasswordField passField = new PasswordField();
    passField.setPromptText("Password");
    passField.setMaxWidth(300);

// confirm password

    Text cpassText = new Text();
    cpassText.setText("Confirm Password");
    cpassText.setFont(Font.font("Bold",30));
    cpassText.setFill(Color.WHITESMOKE);

    PasswordField cpassField = new PasswordField();
    cpassField.setPromptText("Password");
    cpassField.setMaxWidth(300);

// Full_name

    Text nameText = new Text();
    nameText.setText("Full Name");
    nameText.setFont(Font.font("Bold",30));
    nameText.setFill(Color.WHITESMOKE);

    TextField nameField = new TextField();
    nameField.setPromptText("Full Name");
    nameField.setMaxWidth(300);

//email

    Text mailText = new Text();
    mailText.setText("Email");
    mailText.setFont(Font.font("Bold",30));
    mailText.setFill(Color.WHITESMOKE);

    TextField mailField = new TextField();
    mailField.setPromptText("example@mail.com");
    mailField.setMaxWidth(300);

//phone

Text numText = new Text();
    numText.setText("Phone Number");
    numText.setFont(Font.font("Bold",30));
    numText.setFill(Color.WHITESMOKE);

TextField numField = new TextField();
numField.setPromptText("Numbers only");

numField.setTextFormatter(new TextFormatter<>(change -> {
    if (change.getControlNewText().matches("\\d*")) {
        return change;
    }
    return null;
}));

Text city = new Text();
    city.setText("City");
    city.setFont(Font.font("Bold",30));
    city.setFill(Color.WHITESMOKE);

    TextField cityField = new TextField();
    cityField.setPromptText("ex. Cairo");
    cityField.setMaxWidth(300);

Text country = new Text();
    country.setText("Email");
    country.setFont(Font.font("Bold",30));
    country.setFill(Color.WHITESMOKE);

    TextField countryField = new TextField();
    countryField.setPromptText("ex. Egypt");
    countryField.setMaxWidth(300);

Text roleText = new Text("Choose Role");
roleText.setFont(Font.font(null, FontWeight.BOLD, 30));
roleText.setFill(Color.WHITESMOKE);


    RadioButton adminRB = new RadioButton("Admin");
RadioButton employeeRB = new RadioButton("Employee");

adminRB.setTextFill(Color.WHITESMOKE);
employeeRB.setTextFill(Color.WHITESMOKE);

adminRB.setFont(Font.font(null, FontWeight.BOLD, 14));
employeeRB.setFont(Font.font(null, FontWeight.BOLD, 14));


ToggleGroup roleGroup = new ToggleGroup();

adminRB.setToggleGroup(roleGroup);
employeeRB.setToggleGroup(roleGroup);
employeeRB.setSelected(true);


HBox roleBox = new HBox(20, employeeRB, adminRB);
roleBox.setAlignment(Pos.CENTER_LEFT);

RadioButton selected = (RadioButton) roleGroup.getSelectedToggle();





// btn

Button signUpBtn = new Button("ADD");

signUpBtn.setPrefWidth(200);
signUpBtn.setTextFill(Color.WHITE);
signUpBtn.setFont(Font.font("Arial", 16));
signUpBtn.setStyle("-fx-background-color: #e9b312;");
signUpBtn.setOnAction(e -> {

    String pass = passField.getText();

    if (!pass.equals(cpassField.getText())) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Alert");
        alert.setHeaderText(null);
        alert.setContentText("Passwords are not equal");
        alert.showAndWait();
        return;
    }

    // get selected role at CLICK TIME (IMPORTANT)
    RadioButton thing = (RadioButton) roleGroup.getSelectedToggle();
    String  rule = thing.getText().toUpperCase(); // ADMIN or EMPLOYEE

    boolean ok = StaffDAO.AddNewUser(
        usernameField.getText(),   // username
        passField.getText(),       // password
        nameField.getText(),       // full name
        mailField.getText(),       // email
        numField.getText(),        // phone
        rule                        // role (ADMIN / EMPLOYEE)
    );

    if (ok) {
        manager.showstore();
    } else {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Alert");
        alert.setHeaderText(null);
        alert.setContentText("Something went wrong, please change username");
        alert.showAndWait();
    }
});





    grid.add(usernameText,0,0);
    grid.add(usernameField,1,0);
    grid.add(passText,0,1);
    grid.add(passField,1,1);
    grid.add(cpassText,0,2);
    grid.add(cpassField,1,2);
    grid.add(nameText,0,3);
    grid.add(nameField,1,3);
    grid.add(mailText,0,4);
    grid.add(mailField,1,4);
    grid.add(numText,0,5);
    grid.add(numField,1,5);
    grid.add(roleText,0,6);
    grid.add(roleBox,1,6);



    form.getChildren().addAll(grid,signUpBtn);
    root.getChildren().addAll(title,form);
    scene = new Scene(root,Color.BLACK);
    return scene;

    }
    public static Scene getScene()
    {
        return scene;
    }
}
