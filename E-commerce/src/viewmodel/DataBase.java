package viewmodel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataBase {
    private String dataBaseName;
    private String dataBaseUser;
    private String dataBasePassword;
    public static final int PORT_NUMBER = 3306; 

    public DataBase(String dataBaseName, String dataBaseUser, String dataBasePassword) {
        this.dataBaseName = dataBaseName;
        this.dataBaseUser = dataBaseUser;
        this.dataBasePassword = dataBasePassword;
    }
    
    private String createURL(){
        return "jdbc:mysql://localhost:" + PORT_NUMBER + "/" + dataBaseName;    
    }
    
    public void connect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(createURL(),dataBaseUser,dataBasePassword);
            System.out.println("Connection Success");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
}
