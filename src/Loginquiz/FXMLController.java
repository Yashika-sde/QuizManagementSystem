/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Loginquiz;

import Userdashboard.UserDashboardController;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.DBConnection;
import util.Session;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class FXMLController implements Initializable {
    @FXML
    private TextField username;
    @FXML
    private TextField pass;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void login_btn(ActionEvent event) throws SQLException, IOException
    {
        String user_name = username.getText();
        String pass_key = pass.getText();
        if (user_name.equals("admin") && pass_key.equals("admin123")) 
        {
            Stage st = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/Adminpage/Adminpage.fxml"));
            Scene sc = new Scene(root);
            st.setScene(sc);
            st.showAndWait();
        }
        if(user_name.isEmpty()||pass_key.isEmpty())
        {
            showAlert("Enter username and password!!");
        }
        try
        {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT * FROM user_details WHERE username=? AND pass=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user_name);
            ps.setString(2, pass_key);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Userdashboard/UserDashboard.fxml"));
                Parent root = loader.load();
                UserDashboardController controller = loader.getController();
                controller.setUsername(user_name);
                Stage stage = (Stage) username.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
                Session.username = user_name;
            }
            else
            {
                showAlert("Invalid Credentials");
            }
            con.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    private void register_btn(ActionEvent event) throws IOException
    {
            Stage st = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/quizmanagement/Registerpage.fxml"));
            Scene sc = new Scene(root);
            st.setScene(sc);
            st.showAndWait();
    }
    
     private void showAlert(String msg)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
    
}
