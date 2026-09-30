/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manageusers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import util.DBConnection;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class ManageuserController implements Initializable {
   
    @FXML 
    private TableView<User> userTable;
    @FXML
    private TableColumn<User, Integer> colUid;
    @FXML
    private TableColumn<User, String> colFullname;
    @FXML
    private TableColumn<User, String> colUsername;
    @FXML
    private TableColumn<User, String> colEmail;
    @FXML
    private TableColumn<User, Integer> colAge;
    @FXML
    private TableColumn<User, String> colCollege;
    @FXML
    private TableColumn<User, String> colYearofStudy;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        colUid.setCellValueFactory(new PropertyValueFactory<>("uid"));
        colFullname.setCellValueFactory(new PropertyValueFactory<>("fullname"));
        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("emailid"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colCollege.setCellValueFactory(new PropertyValueFactory<>("college"));
        colYearofStudy.setCellValueFactory(new PropertyValueFactory<>("year"));

        loadUsers();
    }

    private void loadUsers() {
    try {
        Connection con = DBConnection.getConnection();
        String sql = "SELECT uid, fullname, username, emailid, age, college, year_of_study FROM user_details";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            userTable.getItems().add(
                new User(
                    rs.getInt("uid"),
                    rs.getString("fullname"),
                    rs.getString("username"),
                    rs.getString("emailid"),
                    rs.getInt("age"),
                    rs.getString("college"),
                    rs.getString("year_of_study")
                )
            );
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    
}
