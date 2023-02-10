package entities;

import utilities.ByCryptPasswordHashing;
import utilities.EmailValidator;

import java.io.EOFException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.EmptyStackException;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Timestamp createdOn;
    private Timestamp lastLogin;

    public User(String firstName, String lastName, String email, String password, Timestamp createdOn, Timestamp lastLogin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.createdOn = createdOn;
        this.lastLogin = lastLogin;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) throws Exception {
        if(firstName==null||firstName.length()<1){
            throw new Exception("The input firstName is empty");
        }
        this.firstName = firstName.trim();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) throws Exception {
        if(lastName==null||lastName.length()<1){
            throw new Exception("The input lastName is empty");
        }
        this.lastName = lastName.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws Exception {
        if(email==null||email.length()<1){
            throw new EOFException();
        }
        boolean isValid = EmailValidator.patternMatches(email);
        if(isValid){
            this.email = email.trim();
        } else {
            throw new Exception("A Genuine email was not provided!");
        }

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws Exception {
        if(password==null||password.length()<1){
            throw new Exception("The input Password is empty");
        }
        ByCryptPasswordHashing tool = new ByCryptPasswordHashing();
        this.password = tool.hashPassword(password);
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        //todo: convert to timestamp in order to save to db
        this.createdOn = Timestamp.valueOf(createdOn);
    }

    public Timestamp getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = Timestamp.valueOf(lastLogin);
    }
}
