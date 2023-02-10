package dao;

//todo: You can use one method to do the login -- the function accepts email and
// password as parameters and maybe evem returns the User
//todo: the Connection object is in the UserDao's constructor
// so that when one calls it in the servlet one has a conn object--
// not gettind and closing connections in the DAO layer -- am going directly to the st and ps objects

import db.DBConnect;
import dto.UserDTO;
import entities.User;
import jakarta.servlet.RequestDispatcher;
import org.postgresql.util.PSQLException;
import utilities.ByCryptPasswordHashing;

import java.sql.*;

public class UserDAO {
    private static Connection conn;
    private static PreparedStatement ps;
    private static ResultSet rs;
    private RequestDispatcher rd = null;
    private final static String SAVE_USER = "INSERT INTO users (first_name, last_name, email, password, created_on) values(?,?,?,?,?)";
private final static String FIND_USER = "SELECT user_id ,first_name, last_name, email, password, created_on, last_login FROM users where email=?";
   private final static String COMPARE_PASS = "SELECT first_name, password FROM users where email=?";
    public UserDAO(Connection conn, PreparedStatement ps, ResultSet rs) {
        UserDAO.conn = conn;
        UserDAO.ps = ps;
        UserDAO.rs = rs;
        //helps if the methods and variables of the class ae not static ones hence the class can be
        // instantiated in the servlet and methods called -- place connection object in the constructor
    }
    public UserDAO(){

    }
    public static boolean registerUser(UserDTO userDTO) throws PSQLException{
        boolean registered = false;
        int rowCount = 0;

        try{
            conn = DBConnect.getConnection();
            if(conn!=null){
                ps = conn.prepareStatement(SAVE_USER);
            }

            if(ps!=null){
                ps.setString(1, userDTO.getFirstName());
                ps.setString(2, userDTO.getLastName());
                ps.setString(3, userDTO.getEmail());
                ps.setString(4, userDTO.getPassword());
                ps.setTimestamp(5, userDTO.getCreatedOn());
                    rowCount = ps.executeUpdate();
            }
                if (rowCount>0) {
                    registered = true;
                }
                if (rowCount<1){
                    throw new SQLException("transaction did not go through, empty response");
                }
        }catch (SQLException s){
            s.printStackTrace();
        }finally {

        }
        return registered;
    }
    public static boolean findUser(String userEmail){
        boolean isFound = false;
        int rowCount;
        try{
            if(conn==null) {
                conn = DBConnect.getConnection();
            }
            if(conn!=null){
                ps = conn.prepareStatement(FIND_USER);
            }
            if(ps!=null){
                ps.setString(1, userEmail);
                rs = ps.executeQuery();
            }
            if(rs!=null){
                if(rs.next()){
                    System.out.println(rs.getString(1) + " " + rs.getString(2));
                    isFound = true;
                }
            }
        }catch (SQLException s){
            s.printStackTrace();
        }finally {

        }
        return isFound;
    }
    public static boolean comparePasswords(String email, String password){
        String pass_db = null;
        boolean match = false;
        ByCryptPasswordHashing tool = null;
        try{
            if(conn==null){
                conn = DBConnect.getConnection();
            }
            if(conn!=null){
                ps = conn.prepareStatement(COMPARE_PASS);
            }
            if(ps!=null){
                ps.setString(1, email);
                rs = ps.executeQuery();
            }
            if(rs!=null){
                if(rs.next()){
                    tool = new ByCryptPasswordHashing();
                    match = tool.checkPassword(password, rs.getString(2));
                }
            }
        }catch (SQLException s){
            s.printStackTrace();
        }finally {

        }
        return match;
    }
    public static User returnUser(String email){
        User user = null;
        try {
            if (conn != null) {
                conn = DBConnect.getConnection();
            }
            if (conn != null) {
                ps = conn.prepareStatement(FIND_USER);
            }
            if (ps != null) {
                ps.setString(1, email);
                rs = ps.executeQuery();
            }
            if(rs.next()){
                user = new User(rs.getString(2), rs.getString(3),
                        rs.getString(4), null, null, null );
            }
        }catch (SQLException a){
            a.printStackTrace();
        }
        return user;
    }
}
