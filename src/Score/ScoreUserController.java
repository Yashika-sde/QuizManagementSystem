/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Score;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import util.DBConnection;
import util.Session;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class ScoreUserController implements Initializable {
    private Label score;
    private Label total;
    @FXML
    private Label nameofuser;
    @FXML
    private Label scorel;
    @FXML
    private Label totall;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadScore();
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
    
     private void loadScore() {
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            nameofuser.setText(Session.username);
            String sql = "SELECT score, total FROM scores " +
                         "WHERE username='" + Session.username + "' " +
                         "ORDER BY id DESC LIMIT 1";

            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                scorel.setText("Score:"+rs.getInt("score"));
                totall.setText("Total:"+rs.getInt("total"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
