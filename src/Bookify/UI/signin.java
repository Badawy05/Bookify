package Bookify.UI;

import Bookify.DAO.CustomerDAO;
import Bookify.DAO.StaffDAO;
import Bookify.controllers.Scenemanager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class signin {
     final private Scenemanager manager;
    
    public signin(Scenemanager manager)
    {
        this.manager=manager;
    }
    
    
    public Scene getscene() {
       //username label 
       Label userlabel = new Label("Username");
        userlabel.setStyle("-fx-text-fill: black; -fx-font-size: 25px;-fx-font-weight: bold;");
        //username field
        TextField username=new TextField();
        username.setPromptText("Username:");
        username.setStyle("-fx-background-radius: 20; -fx-border-radius: 20; -fx-padding: 0 15 0 15;");
        username.setPrefHeight(40);
        username.setMaxWidth(400);  
        //password label 
        Label passlabel = new Label("Password:");
        passlabel.setStyle("-fx-text-fill: black; -fx-font-size: 25px;;-fx-font-weight: bold;");
        
        //password field
        PasswordField password=new PasswordField();
        password.setPromptText("Password");
        password.setStyle("-fx-background-radius: 20; -fx-border-radius: 20; -fx-padding: 0 15 0 15;");        
        password.setPrefHeight(40);
        password.setMaxWidth(400);
        //login button
        Button login=new Button("Login");
        login.setStyle("-fx-background-color:#e9b312;-fx-font-size: 20px; -fx-font-weight: bold;-fx-background-radius: 25;");
        login.setPrefWidth(300);
        login.setOnAction(e -> {

    String user = username.getText().trim();   // read NOW
    String pass = password.getText();           // read NOW

    if (CustomerDAO.loginCheck(user, pass)) {
        manager.setCurrentUserFullName(CustomerDAO.getFullname(user));
        manager.setCurrentID(user);
        manager.showstore();
    }
    else if (StaffDAO.loginCheck(user, pass)) {
        // staff page
        // if admin  
        if(StaffDAO.checkAdmin(user))
        {
            manager.admin();
        }
        else
            {
                manager.abook();
            }
        // employee 
        
    }
    else {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Login Failed");
        alert.setHeaderText(null);
        alert.setContentText("Wrong username or password.");
        alert.showAndWait();
    }
});

        
        //back button
        Button back=new Button("Back");
        back.setStyle("-fx-background-color:#e9b312;-fx-font-size: 20px; -fx-font-weight: bold;-fx-background-radius: 25;");
        back.setPrefWidth(300);
        back.setOnAction(e->manager.showlogin());
        //margins
        VBox.setMargin(back, new Insets(-55, 0, 0, 0));
        VBox.setMargin(userlabel, new Insets(0, 0, -50, -325));
        VBox.setMargin(passlabel, new Insets(0, 0, -50, -325));
     
        //background box 
        Image whitebck=new Image("/images/whitebck.jpeg");
        ImageView whitebck2=new ImageView(whitebck);        
        VBox content =new VBox(70,userlabel,username,passlabel,password,login,back);
        
        StackPane content2=new StackPane(whitebck2,content);
        content2.setStyle("-fx-background-color: #0f172A;");
        //centeralize contents
        content.setAlignment(Pos.CENTER);
        content2.setAlignment(Pos.CENTER);
        //scene
        Scene main1=new Scene(content2,Color.BLACK);
       //stage 
     return main1;
    }

}
