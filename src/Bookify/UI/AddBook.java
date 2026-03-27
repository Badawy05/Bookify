package Bookify.UI;
import Bookify.DAO.BookDAO;
import Bookify.DAO.CustomerDAO;
import Bookify.controllers.Scenemanager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
public class AddBook {


    private static Scene scene;
    private static Scenemanager manager;

    public AddBook(Scenemanager manager)
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
    text.setText("Add a Book");
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
    usernameText.setText("Title");
    usernameText.setFont(Font.font("Bold",30));
    usernameText.setFill(Color.WHITESMOKE);


    TextField usernameField = new TextField();
    usernameField.setPromptText("Title");
    usernameField.setMaxWidth(300);


// Full_name

    Text nameText = new Text();
    nameText.setText("Author ");
    nameText.setFont(Font.font("Bold",30));
    nameText.setFill(Color.WHITESMOKE);

    TextField nameField = new TextField();
    nameField.setPromptText("Author");
    nameField.setMaxWidth(300);

//email

    Text mailText = new Text();
    mailText.setText("Genre");
    mailText.setFont(Font.font("Bold",30));
    mailText.setFill(Color.WHITESMOKE);

    TextField mailField = new TextField();
    mailField.setPromptText("Genre");
    mailField.setMaxWidth(300);

//phone

Text numText = new Text();
    numText.setText("Published Year");
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

Text pricetext = new Text();
    pricetext.setText("Price");
    pricetext.setFont(Font.font("Bold",30));
    pricetext.setFill(Color.WHITESMOKE);

TextField priceField = new TextField();
priceField.setPromptText("Numbers only");

priceField.setTextFormatter(new TextFormatter<>(change -> {
    if (change.getControlNewText().matches("\\d*")) {
        return change;
    }
    return null;
}));

Text stocktext = new Text();
    stocktext.setText("Stock");
    stocktext.setFont(Font.font("Bold",30));
    stocktext.setFill(Color.WHITESMOKE);

TextField stockField = new TextField();
stockField.setPromptText("Numbers only");

stockField.setTextFormatter(new TextFormatter<>(change -> {
    if (change.getControlNewText().matches("\\d*")) {
        return change;
    }
    return null;
}));



 Text typetxt = new Text();
    typetxt.setText("Type ");
    typetxt.setFont(Font.font("Bold",30));
    typetxt.setFill(Color.WHITESMOKE);

    TextField typeField = new TextField();
    typeField.setPromptText("Ebook or printed book");
    typeField.setMaxWidth(300);





     Text imagename = new Text();
    imagename.setText("Image Name in Folder ");
    imagename.setFont(Font.font("Bold",30));
    imagename.setFill(Color.WHITESMOKE);

    TextField imgpath = new TextField();
    imgpath.setPromptText("name");
    imgpath.setMaxWidth(300);


    Text RateText = new Text();
    RateText.setText("Rate Text");
    RateText.setFont(Font.font("Bold",30));
    RateText.setFill(Color.WHITESMOKE);

TextField RateField = new TextField();
stockField.setPromptText("Numbers only");

stockField.setTextFormatter(new TextFormatter<>(change -> {
    if (change.getControlNewText().matches("\\d*")) {
        return change;
    }
    return null;
}));

// btn

Button signUpBtn = new Button("Add");

signUpBtn.setPrefWidth(200);
signUpBtn.setTextFill(Color.WHITE);
signUpBtn.setFont(Font.font("Arial", 16));
signUpBtn.setStyle("-fx-background-color: #e9b312;");
signUpBtn.setOnAction(e->{

    
           if( BookDAO.addBook(usernameField.getText(),
            nameField.getText(),mailField.getText(),
            Integer.valueOf(numField.getText()), Float.valueOf(priceField.getText()),Integer.valueOf(stockField.getText()),typeField.getText(),imgpath.getText(),Integer.valueOf(RateField.getText())))
            {
                manager.showstore();
            }
            else
                {  Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Alert");
                alert.setHeaderText(null);
                alert.setContentText("Something Wrong please change username ");
                alert.showAndWait();
                return;
                }
            

});
Button back = new Button("Back");

back.setPrefWidth(200);
back.setTextFill(Color.WHITESMOKE);
back.setFont(Font.font("Arial", 16));
back.setStyle("-fx-background-color: #fc0e0eff;");
back.setOnAction(e->{

    manager.abook();
});




    grid.add(usernameText,0,0);
    grid.add(usernameField,1,0);
    grid.add(nameText,0,1);
    grid.add(nameField,1,1);
    grid.add(mailText,0,2);
    grid.add(mailField,1,2);
    grid.add(numText,0,3);
    grid.add(numField,1,3);
    grid.add(pricetext,0,4);
    grid.add(priceField,1,4);
     grid.add(stocktext,0,5);
    grid.add(stockField,1,5);
     grid.add(typetxt,0,6);
    grid.add(typeField,1,6);
     grid.add(imagename,0,7);
    grid.add(imgpath,1,7);
     grid.add(RateText,0,8);
    grid.add(RateField,1,8);
    grid.add(back,0,9);




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

