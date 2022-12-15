package viewmodel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import model.ProductItem;

public class DataBase {

    private String dataBaseName;
    private String dataBaseUser;
    private String dataBasePassword;
    public static final int PORT_NUMBER = 3306;
    private Connection connection;

    public DataBase(String dataBaseName, String dataBaseUser, String dataBasePassword) {
        this.dataBaseName = dataBaseName;
        this.dataBaseUser = dataBaseUser;
        this.dataBasePassword = dataBasePassword;
    }

    private String createURL() {
        return "jdbc:mysql://localhost:" + PORT_NUMBER + "/" + dataBaseName;
    }

    private void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(createURL(), dataBaseUser, dataBasePassword);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean writeRow(int id, String name, double price) {

        connect();
        String InsertQuery = "INSERT INTO products(id, name, price) VALUES (?,?,?)";
        PreparedStatement prepStatement;
        try {
            prepStatement = connection.prepareStatement(InsertQuery);
            prepStatement.setInt(1, id);
            prepStatement.setString(2, name);
            prepStatement.setDouble(3, price);
            prepStatement.executeUpdate();
            prepStatement.close();
            return true;
        } catch (SQLException ex) {
            System.out.println("Writing Error: Row not added");
            return false;
        }

    }

    public ProductItem[] readAllData() {
        connect();
        try {
            //Counting Number of Rows in data
            Statement countStatement = connection.createStatement();
            ResultSet countResultSet = countStatement.executeQuery("select count(*) from products");
            countResultSet.next();
            int count = countResultSet.getInt(1);
            countResultSet.close();
            ProductItem[] productData = new ProductItem[count];
            
            
            //Getting data
            Statement readStatement = connection.createStatement();
            ResultSet readResultSet = readStatement.executeQuery("SELECT * FROM products");
            int i = 0;
            while (readResultSet.next()) {
                int productId = readResultSet.getInt("id");
                String productName = readResultSet.getString("name");
                double productPrice = readResultSet.getFloat("price");                
                productData[i++] = new ProductItem(productId, productName, productPrice);                
            }
            readResultSet.close();
            return productData;

        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

}
