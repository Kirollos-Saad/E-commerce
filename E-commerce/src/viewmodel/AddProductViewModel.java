package viewmodel;

import javax.swing.JOptionPane;
import model.Node;
import model.ProductItem;

public class AddProductViewModel {

    private DataBase database;

    public AddProductViewModel(DataBase database) {
        this.database = database;

    }

    public boolean validateThenAdd(Node parent, String id, String name, String price) {

        //1- check id and price are numbers.
        try {

            if (name.isBlank() || price.isBlank() || id.isBlank()) {
                JOptionPane.showMessageDialog((javax.swing.JFrame) parent, "Empty field(s)!");
            }

            int idValue = Integer.parseInt(id);
            double priceValue = Double.parseDouble(price);

            //2- check id is unique.
            if (repeatedId(idValue)) {
                JOptionPane.showMessageDialog((javax.swing.JFrame) parent, "You entered a product id that already exists.", "Unique Id", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            if (priceValue < 0) {
                JOptionPane.showMessageDialog((javax.swing.JFrame) parent, "Price Can't be negative", "Negative Price", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            if (idValue < 0) {
                JOptionPane.showMessageDialog((javax.swing.JFrame) parent, "Id Can't be negative", "Negative ID", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            if (database.writeRow(idValue, name, priceValue)) {
                JOptionPane.showMessageDialog((javax.swing.JFrame) parent, "Successfully Added");
                return true;
            }

            return false;

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog((javax.swing.JFrame) parent, "Id should be an integer number or price should be decimal value.", "Numbers Validation", JOptionPane.ERROR_MESSAGE);
            return false;
        }

    }

    public boolean repeatedId(int id) {
        ProductItem[] products = database.readAllData();
        for (ProductItem p : products) {
            if (p.getId() == id) {
                return true;
            }
        }

        return false;
    }

}
