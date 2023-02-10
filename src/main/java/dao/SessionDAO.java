package dao;

import db.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SessionDAO {
    private static Connection conn = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;
    private static final String SAVE_SESSION_ID = "INSERT into user_session (user_email, user_session_id) values (?,?)";
    private static final String USER_IN_SESSION = "SELECT user_email, user_session_id FROM user_session WHERE user_email=?";
    private static final String DELETE_USER = "DELETE FROM user_session WHERE user_email=?";


    public SessionDAO(Connection conn) {
        SessionDAO.conn = conn;
    }


    public static boolean save_user_session(String email, String session_id) throws RuntimeException{
        int rowCount = 0;
        boolean isSaved = false;
        try{
//            if(conn==null){
//                conn = DBConnect.getConnection();
//            }
            if(conn!=null){
                ps = conn.prepareStatement(SAVE_SESSION_ID);
            }
            if(ps!=null){
                ps.setString(1, email);
                ps.setString(2, session_id);
                rowCount =  ps.executeUpdate();
            }
            if(rowCount>0){
                isSaved = true;
            }
            if(rowCount<1){
                throw new RuntimeException("session id not saved");
            }
        }catch (SQLException s) {
            s.printStackTrace();
        }
        return isSaved;
    }
    public static boolean check_whether_session_exists(String email){
        boolean is_user_available = false;
        boolean is_row_deleted = false;
        int rowCounter = 0;
        try {
            if(conn==null){
                conn = DBConnect.getConnection();
            }
            if (conn != null) {
                ps = conn.prepareStatement(USER_IN_SESSION);
            }
            if (ps != null) {
                ps.setString(1, email);
                rs = ps.executeQuery();
            }
            if (rs != null) {
                if (rs.next()) {
                    is_user_available = true;
                }
            }
            if(is_user_available){
                ps = conn.prepareStatement(DELETE_USER);
                if(ps!=null){
                    ps.setString(1, email);
                    rowCounter = ps.executeUpdate();
                }
                if(rowCounter>0){
                    is_row_deleted = true;
                }
            }
        }catch (SQLException s){
            s.printStackTrace();
        }
        return is_user_available;
    }
}
