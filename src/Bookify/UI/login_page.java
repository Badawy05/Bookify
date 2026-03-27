package Bookify.UI;

import Bookify.controllers.Scenemanager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


public class login_page  {

    private final Scenemanager manager;
    
    public login_page(Scenemanager manager)
    {
        this.manager=manager;
    }
    
    public Scene getscene() {
       //login button
        Button login=new Button("Login");
       login.setPrefSize(300,50);
       login.setStyle("-fx-background-color:#e9b312;-fx-font-size: 20px; -fx-font-weight: bold;-fx-background-radius: 25;");
       login.setOnAction(e -> manager.showsignin());
       //signup button
       Button signup=new Button("Signup");
       signup.setPrefSize(300,50);
       signup.setStyle("-fx-background-color:#e9b312;-fx-font-size: 20px; -fx-font-weight: bold;-fx-background-radius: 25;");
        signup.setOnAction(e -> manager.showSignUp());

       //exit button
       Button exit = new Button("Exit");
       exit.setPrefSize(300,50);
       exit.setStyle("-fx-background-color:#d1684a;-fx-font-size: 20px; -fx-font-weight: bold;-fx-background-radius: 25;");
       exit.setOnAction( e->{javafx.application.Platform.exit();});
       //image
       Image welcome = new Image(getClass().getResourceAsStream("/images/start2_page.png"));       
       ImageView welcome2=new ImageView(welcome);
       //control margins and pane
       VBox.setMargin(welcome2, new Insets(0, 0, 50, 0));
       VBox mainpage= new VBox(10,welcome2,login,signup,exit);  
       //background color
        mainpage.setStyle("-fx-background-color: #0f172A;");
        //centralize components
        mainpage.setAlignment(Pos.CENTER);
        //scene 
       Scene main1=new Scene(mainpage,Color.BLACK);
       //stage
       return main1;
       
       
       
    }
    
    
}
