package Bookify.Model;
import Bookify.controllers.Scenemanager;
import javafx.application.Application;
import javafx.stage.Stage;



public class Main extends Application 
{

    @Override
    public void start(Stage stage) 
    {
        Scenemanager c = new Scenemanager(stage);
        //c.showlogin();
        c.showlogin();
        stage.show();
    }

    public static void main(String[] args) 
    {
        launch();
    }
}
