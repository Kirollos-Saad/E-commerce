package viewmodel;

import javax.swing.table.DefaultTableModel;
import model.ProductItem;

public class ViewProductViewModel {

    private DataBase dataBase;

    public ViewProductViewModel(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public boolean setTableData(javax.swing.JTable dataTable) {
        ProductItem[] productData = dataBase.readAllData();
        if (productData == null) {
            return false;
        }
        String[][] productDataString = new String[3][productData.length];
        for (int i = 0; i < productData.length; i++) {
            productDataString[i] = productData[i].toString().split(",");
        }

        DefaultTableModel defaultTableModel = new DefaultTableModel(productDataString, new String[] {"id", "name", "price"} ){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        dataTable.setModel(defaultTableModel);

        return true;
    }

}
