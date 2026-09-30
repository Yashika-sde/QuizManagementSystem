/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LeaderBoard;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

/**
 * FXML Controller class
 *
 * @author acer
 */
public class LeaderboardController implements Initializable {
    @FXML
    private Label l1;
    @FXML
    private Label l5;
    @FXML
    private Label l4;
    @FXML
    private Label l3;
    @FXML
    private Label l2;
    @FXML
    private Label l6;
    @FXML
    private Label l7;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadLeaderboard();
    }    

    @FXML
    private void Back(ActionEvent event) throws IOException 
    {
        Stage st = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Adminpage/Adminpage.fxml"));
        Scene sc = new Scene(root);
        st.setScene(sc);
        st.showAndWait();
    }
    
    private void loadLeaderboard() {

    Label[] labels = {
        l1, l2, l3, l4, l5, l6, l7
    };

    try {
        Connection con = DBConnection.getConnection();

        String sql = "SELECT username, score, total " +
                     "FROM scores " +
                     "ORDER BY score DESC, dateval ASC " +
                     "LIMIT 7";

        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        int i = 0;

        while (rs.next() && i < labels.length) {

            String user = rs.getString("username");
            int score = rs.getInt("score");
            int total = rs.getInt("total");

            labels[i].setText(
                (i + 1) + ". " + user + "   —   " + score + "/" + total
            );

            i++;
        }

        // Clear unused labels if less than 7 records
        while (i < labels.length) {
            labels[i].setText((i + 1) + ". ---");
            i++;
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}

}
