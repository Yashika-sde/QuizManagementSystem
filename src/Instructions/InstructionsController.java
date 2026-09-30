/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instructions;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class InstructionsController implements Initializable {
    @FXML
    private Label instructs;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
    }    

    @FXML
    private void Back(ActionEvent event) throws IOException 
    {
        Stage st = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Userdashboard/UserDashboard.fxml"));
        Scene sc = new Scene(root);
        st.setScene(sc);
        st.showAndWait();
    }

    @FXML
    private void Start_Test(ActionEvent event) throws IOException 
    {
        Stage st = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/QuizPages/quizPage.fxml"));
        Scene sc = new Scene(root);
        st.setScene(sc);
        st.showAndWait();
    }
    
}
