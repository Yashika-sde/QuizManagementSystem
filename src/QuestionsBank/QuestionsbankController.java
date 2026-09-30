/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuestionsBank;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import util.DBConnection;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class QuestionsbankController implements Initializable {
    @FXML
    private TextField question;
    @FXML
    private TextField op1;
    @FXML
    private TextField op2;
    @FXML
    private TextField op3;
    @FXML
    private TextField op4;
    @FXML
    private TextField copt;
    @FXML
    private TextField qid;

    /**
     * Initializes the controller class.
     */
    
    Connection con = DBConnection.getConnection();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Edit(ActionEvent event) 
    {
        try
        {
          String sql = "UPDATE questions SET qid=?,question=?,option1=?,option2=?,option3=?,option4=?,corranswer=? WHERE qid=?"; 
          PreparedStatement ps = con.prepareStatement(sql);
          ps.setInt(1, Integer.parseInt(qid.getText()));
          ps.setString(2, question.getText());
          ps.setString(3, op1.getText());
          ps.setString(4, op2.getText());
          ps.setString(5, op3.getText());
          ps.setString(6, op4.getText());
          ps.setString(7, copt.getText());
          ps.setInt(8, Integer.parseInt(qid.getText()));
          ps.executeUpdate();
          showAlert("Questions updated Successfully!");
        }
        catch(Exception e)
        {
            showAlert("Updation Failed!");
        }
    }

    @FXML
    private void Delete(ActionEvent event) 
    {
         try
        {
            String sql = "DELETE FROM questions WHERE qid=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(qid.getText()));
            ps.executeUpdate();
            showAlert("Question deleted!");
        }
        catch(Exception e)
        {
            showAlert("Delete Failed!");
        }
    }

    @FXML
    private void Clear(ActionEvent event)
    {
        clearFields(null);
    }

    @FXML
    private void Add(ActionEvent event) throws SQLException 
    {
        try
        {
            String sql = "INSERT INTO questions(question,option1,option2,option3,option4,corranswer) VALUES(?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, question.getText());
            ps.setString(2, op1.getText());
            ps.setString(3, op2.getText());
            ps.setString(4, op3.getText());
            ps.setString(5, op4.getText());
            ps.setString(6, copt.getText());
            ps.executeUpdate();
            showAlert("Questions added Successfully!");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            showAlert("Error adding Questions!");
        }
    }

    @FXML
    private void Search(ActionEvent event) 
    {
       try
       {
           String sql = "SELECT * FROM questions WHERE qid=?";
           PreparedStatement ps = con.prepareStatement(sql);
           ps.setInt(1, Integer.parseInt(qid.getText()));
           ResultSet rs = ps.executeQuery();
           if(rs.next())
           {
               question.setText(rs.getString("question"));
               op1.setText(rs.getString("option1"));
               op2.setText(rs.getString("option2"));
               op3.setText(rs.getString("option3"));
               op4.setText(rs.getString("option4"));
               copt.setText(rs.getString("corranswer"));
           }
           else
           {
               showAlert("Questions not Found!");
           }
       }
       catch(Exception e)
       {
           showAlert("Invalid Question Id!");
       }
    }
    
     
    private void showAlert(String msg)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
    
    
   
    @FXML
    private void clearFields(ActionEvent event) {
        qid.clear();
        question.clear();
        op1.clear();
        op2.clear();
        op3.clear();
        op4.clear();
        copt.clear();
    }
}
