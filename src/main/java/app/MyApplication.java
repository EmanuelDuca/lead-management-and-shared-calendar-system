package app;

import app.model.Model;
import app.model.ModelManager;
import app.view.ViewHandler;
import app.viewmodel.ViewModelFactory;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MyApplication extends Application
{
  public static void main(String[] args)
  {
    launch();
  }

  @Override public void start(Stage primaryStage) throws Exception
  {
    primaryStage.setTitle("Calendar");
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("calendar.fxml"));
    Scene scene= new Scene(loader.load());
    primaryStage.setResizable(false);
    primaryStage.initStyle(StageStyle.UNDECORATED);
    primaryStage.setScene(scene);
    primaryStage.show();

    Model model = new ModelManager();
    ViewModelFactory viewModelFactory = new ViewModelFactory(model);
    ViewHandler viewHandler = new ViewHandler(viewModelFactory);
    viewHandler.start(primaryStage);
  }
}
