package viewmodel;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.ProductItem;
import model.Node;

public class ViewProductViewModel {

    private DataBase dataBase;

    public ViewProductViewModel(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public boolean setTableData(Node mainFrame, javax.swing.JTable dataTable) {
        ProductItem[] productData = dataBase.readAllData();
        if (productData == null) {
            JOptionPane.showMessageDialog((javax.swing.JFrame )mainFrame,   "No data to be viewed",
                    "No data", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        String[][] productDataString = new String[productData.length][3];
        for (int i = 0; i < productData.length; i++) {            
            productDataString[i] = productData[i].toString().split(",");
        }

        DefaultTableModel defaultTableModel = new DefaultTableModel(productDataString, new String[]{"id", "name", "price"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        dataTable.setModel(defaultTableModel);

        return true;
    }

}
