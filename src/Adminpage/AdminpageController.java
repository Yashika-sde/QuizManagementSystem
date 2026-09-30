/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adminpage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class AdminpageController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }    

    @FXML
    private void userdash(ActionEvent event) throws IOException
    {
        Stage st = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Manageusers/Manageuser.fxml"));
        Scene sc = new Scene(root);
        st.setScene(sc);
        st.showAndWait();
    }

    @FXML
    private void questionbank(ActionEvent event) throws IOException 
    {
        Stage st = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/QuestionsBank/Questionsbank.fxml"));
        Scene sc = new Scene(root);
        st.setScene(sc);
        st.showAndWait();
    }

    @FXML
    private void leaderboard(ActionEvent event) throws IOException
    {
        Stage st = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/LeaderBoard/Leaderboard.fxml"));
        Scene sc = new Scene(root);
        st.setScene(sc);
        st.showAndWait();
    }

    @FXML
    private void close(ActionEvent event) throws IOException 
    {
        Stage st = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/MainPage/Homepage.fxml"));
        Scene sc = new Scene(root);
        st.setScene(sc);
        st.showAndWait();
    }
    
}
