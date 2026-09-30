/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuizPages;

/**
 *
 * @author acer
 */
public class Question 
{
    private int qid;
    private String questions;
    private String[] options;
    private String c_options;
    Question(int qid,String questions,String[] options,String c_options)
    {
        this.qid = qid;
        this.questions = questions;
        this.options = options;
        this.c_options = c_options;
    }
    
    public int getid()
    {
        return qid;
    }
    
    public String getquestions()
    {
        return questions;
    }
    
    public String[] getoption()
    {
        return options;
    }
    
    public String getc_options()
    {
        return c_options;
    }
}
