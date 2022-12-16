package viewmodel;

import javax.swing.JOptionPane;
import model.ProductItem;


public class AddProductViewModel {
    
    private DataBase database;
    
    public AddProductViewModel()
    {
        this.database = database;
    }
    
    
    
    public boolean validate(String id,String name,String price)
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
            
            
        }
        catch(NumberFormatException e)
        {
            JOptionPane.showMessageDialog(null, "Id should be an integer number or price should be decimal value.","Numbers Validation",JOptionPane.ERROR_MESSAGE);
            return false;
        }
 
        return true;
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
