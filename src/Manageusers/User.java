/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manageusers;

public class User {

    private int uid;
    private String fullname;
    private String username;
    private String emailid;
    private int age;
    private String college;
    private String year;

    public User(int uid, String fullname, String username,
                String emailid, int age, String college, String year) {
        this.uid = uid;
        this.fullname = fullname;
        this.username = username;
        this.emailid = emailid;
        this.age = age;
        this.college = college;
        this.year = year;
    }

    public int getUid() 
    { 
        return uid; 
    }
    public String getFullname() 
    {
        return fullname;
    }
    public String getUsername() 
    {
        return username; 
    }
    public String getEmailid() 
    {
        return emailid;
    }
    public int getAge() 
    {
        return age;
    }
    public String getCollege() 
    {
        return college; 
    }
    public String getYear()
    {
        return year;
    }
}
