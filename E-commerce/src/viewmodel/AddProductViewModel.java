package viewmodel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.ProductItem;


public class AddProductViewModel {
    
    private DataBase database;
     private Connection con;
    
    public AddProductViewModel() throws SQLException
    {
        this.database = database;
        this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "root");
    }
   
    public boolean validatethenAdd(String id,String name,String price)
    {
   
        //1- check id and price are numbers.
        
        try
        {
            
            int idValue = Integer.parseInt(id);
            double priceValue = Double.parseDouble(price);
            
            //2- check id is unique.
            
            if(repeatedId(idValue))
            {
                JOptionPane.showMessageDialog(null, "You entered a product id that already exists.","Unique Id",JOptionPane.ERROR_MESSAGE);
                return false;
            }
            return database.writeRow(idValue, name, priceValue);
            
        }
        catch(NumberFormatException e)
        {
            JOptionPane.showMessageDialog(null, "Id should be an integer number or price should be decimal value.","Numbers Validation",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
       
    }
    
    
    public boolean repeatedId(int id)
    {
        ProductItem[] products = database.readAllData();
        for(ProductItem p: products)
        {
            if(p.getId() == id)
                return true;
        }
        
        return false;
    }
    
}
