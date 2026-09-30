/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizmanagement;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.DBConnection;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class FXMLDocumentController implements Initializable {
    @FXML
    private TextField full_name;
    @FXML
    private TextField user_name;
    @FXML
    private ComboBox<String> year;
    @FXML
    private TextField email_id;
    @FXML
    private TextField pass_key;
    @FXML
    private TextField con_key;
    @FXML
    private TextField age;
    @FXML
    private TextField clg_name;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        year.getItems().addAll("I'year","II'year","III'year","IV'year");
    }    

    @FXML
    private void reg_but(ActionEvent event) throws SQLException, IOException 
    {
      String fullname = full_name.getText();
      String username = user_name.getText();
      String emailid = email_id.getText();
      String passkey = pass_key.getText();
      String conkey = con_key.getText();
      String age1 = age.getText();
      String clgname = clg_name.getText();
      String year1 = year.getSelectionModel().getSelectedItem();
      
      if(fullname.isEmpty()||username.isEmpty()||emailid.isEmpty()||passkey.isEmpty()||conkey.isEmpty()||age1.isEmpty()||clgname.isEmpty()||year1.isEmpty())
      {
          showAlert("All fields are needed to fill!");
          return;
      }
      if(!(conkey.equals(passkey)))
      {
          showAlert("Passwords did not matched!");
          return;
      }
          
        try
        {
           Connection con = DBConnection.getConnection();
           String sql = "INSERT INTO user_details(fullname,username,emailid,pass,age,college,year_of_study) VALUES(?,?,?,?,?,?,?)";
           PreparedStatement ps = con.prepareStatement(sql);
           ps.setString(1, fullname);
           ps.setString(2, username);
           ps.setString(3, emailid);
           ps.setString(4, passkey);
           ps.setInt(5, Integer.parseInt(age1));
           ps.setString(6, clgname);
           ps.setString(7, year1);
           ps.executeUpdate();
           showAlert("Registration Successful!");
           con.close();
        } 
        catch(Exception e)
        {
            showAlert("Username already exists");
        }

      
    }

  
    private void showAlert(String msg)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    @FXML
    private void home(ActionEvent event) throws IOException
    {
        Stage st = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/MainPage/Homepage.fxml"));
        Scene sc = new Scene(root);
        st.setScene(sc);
        st.showAndWait();
    }
}
