/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuizPages;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import util.DBConnection;
import util.Session;
import javafx.util.Duration;


/**
 * FXML Controller class
 *
 * @author acer
 */
public class QuizPageController implements Initializable {
    @FXML
    private Label timerlabel;
    @FXML
    private RadioButton op1;
    @FXML
    private Label question;
    @FXML
    private RadioButton op2;
    @FXML
    private RadioButton op3;
    @FXML
    private RadioButton op4;
    
    private int currentIndex = 0;
    
    private ToggleGroup optionsgroup;
    
    Map<Integer,Integer> useranswer = new HashMap<>();
    
    private List<Question> questions = new ArrayList<>();
    @FXML
    private Label qid;
    

    private Timeline timeline;
    private int timeSeconds = 600; // 10 minutes (600 sec)

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        startTimer();
        optionsgroup = new ToggleGroup();
        
        op1.setToggleGroup(optionsgroup);
        op2.setToggleGroup(optionsgroup);
        op3.setToggleGroup(optionsgroup);
        op4.setToggleGroup(optionsgroup);
        
         loadQuestionsFromDb(); 
         loadQuestions(0);      
    } 
    
    private void startTimer() {

    timeline = new Timeline(
        new KeyFrame(Duration.seconds(1), e -> {

            timeSeconds--;

            int minutes = timeSeconds / 60;
            int seconds = timeSeconds % 60;

            timerlabel.setText(
                String.format("%02d:%02d", minutes, seconds)
            );

            // ⛔ TIME FINISHED
            if (timeSeconds <= 0) {
                timeline.stop();
                autoSubmitQuiz();
            }

        })
    );

    timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.play();
}

    private void autoSubmitQuiz() 
    {

    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Time Up!");
    alert.setHeaderText(null);
    alert.setContentText("Time is up! Your quiz will be submitted automatically.");
    alert.showAndWait();

    submitQuizLogic(); // same method as Submit button
}

    private void save_Answer()
    {
        Toggle selected = optionsgroup.getSelectedToggle();
        if(selected!=null)
        {
            int selectedindex = optionsgroup.getToggles().indexOf(selected);
            useranswer.put(currentIndex, selectedindex);
        }
    }

   @FXML
    private void Next(ActionEvent event) {
        save_Answer();
        if (currentIndex < questions.size() - 1) {
            currentIndex++;
            loadQuestions(currentIndex);
        }
    }

    @FXML
    private void Prev(ActionEvent event) {
        save_Answer();
        if (currentIndex > 0) {
            currentIndex--;
            loadQuestions(currentIndex);
        }
    }

    
    private void loadQuestionsFromDb()
    {
        try
        {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM questions ORDER BY RANDOM() LIMIT 10");
            while(rs.next())
            {
                questions.add(new Question(
                rs.getInt("qid"),
                rs.getString("question"),
                new String[]{
                    rs.getString("option1"),
                    rs.getString("option2"),
                    rs.getString("option3"),
                    rs.getString("option4")
                },
                rs.getString("corranswer")
                ));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    private void loadQuestions(int index)
    {
        Question q = questions.get(index);
        qid.setText("Qno:"+ (index+1) +"/10");
        
        question.setText(q.getquestions());
        op1.setText(q.getoption()[0]);
        op2.setText(q.getoption()[1]);
        op3.setText(q.getoption()[2]);
        op4.setText(q.getoption()[3]);
        optionsgroup.selectToggle(null);
        
        if (useranswer.containsKey(index)) {
        int savedIndex = useranswer.get(index);
        optionsgroup.selectToggle(
            optionsgroup.getToggles().get(savedIndex)
        );
        }
        
    }
    @FXML
    private void Submit(ActionEvent event) 
    {
    if (timeline != null) 
    {
        timeline.stop();
    }
    submitQuizLogic();
    }
     
    private void submitQuizLogic() {

    save_Answer();
    int score = 0;

    for (int i = 0; i < questions.size(); i++) {

        if (useranswer.containsKey(i)) {

            int selectedIndex = useranswer.get(i);
            Question q = questions.get(i);

            String selectedAnswer = q.getoption()[selectedIndex];
            String correctAnswer = q.getc_options();

            if (selectedAnswer.equals(correctAnswer)) {
                score++;
            }
        }
    }

    saveScoreToDB(score, questions.size());

    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setHeaderText("Quiz Completed 🎉");
    alert.setContentText("Your Score: " + score + "/" + questions.size());
    alert.showAndWait();
}


    private void saveScoreToDB(int score, int total) 
    {
    try {
        Connection con = DBConnection.getConnection();
        Statement st = con.createStatement();

        String username = Session.username;

        String sql = "INSERT INTO scores(username, score, total) VALUES ('"
                + username + "', " + score + ", " + total + ")";

        st.executeUpdate(sql);

    } 
    catch (Exception e) 
    {
        e.printStackTrace();
    }
    }

    
}
