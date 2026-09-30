/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Userdashboard;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class UserDashboardController implements Initializable {
    @FXML
    private AnchorPane contentPane;
    @FXML
    private Label welcome;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void start_test(ActionEvent event) throws IOException 
    {
        
        Stage st = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/QuizPages/quizPage.fxml"));
        Scene sc = new Scene(root);
        st.setScene(sc);
        st.showAndWait();
    }

    @FXML
    private void instructions(ActionEvent event) throws IOException 
    {
        Stage st = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Instructions/Instructions.fxml"));
        Scene sc = new Scene(root);
        st.setScene(sc);
        st.showAndWait();
    }

    @FXML
    private void log_out(ActionEvent event) throws IOException
    {
        Stage st = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/MainPage/Homepage.fxml"));
        Scene sc = new Scene(root);
        st.setScene(sc);
        st.showAndWait();
    }

    @FXML
    private void my_score(ActionEvent event) throws IOException
    {
        Stage st = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Score/ScoreUser.fxml"));
        Scene sc = new Scene(root);
        st.setScene(sc);
        st.showAndWait();
    }
  

    public void setUsername(String username) 
    {
        welcome.setText("Welcome " + username + "!");
    }


}
