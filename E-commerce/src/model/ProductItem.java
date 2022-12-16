
package model;

public class ProductItem {
   private String name;
   private int id;
   private double price;

    public ProductItem(int id, String name, double price) {
        this.name = name;
        this.id = id;
        this.price = price;
    }
    
    @Override
    public String toString(){        
        return id + "," + name + "," + price;
        
    }
    
   
          
}
