package supermarketsystem.Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import supermarketsystem.model.ProductModel;

/**
 * @author Klarić Dario
 */

public class ProductControl {
    
     Statement state ;
    
    public void insert(ProductModel  product)
    {
        try {
            state = ConnectionDB.openConnection().createStatement();
            state.executeUpdate("INSERT INTO `products` ( `name`, `number`, `price`, `type`, `discount`) VALUES ( '"+product.getName()+"' , "+product.getNumber()+","+product.getPrice()+",'"+product.getType()+"' ,"+product.getDiscount()+")");
             ConnectionDB.closeConnection();
        } catch (SQLException ex) {
            ConnectionDB.closeConnection();
            Logger.getLogger(ProductControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete(int id)
    {
        try {
            state = ConnectionDB.openConnection().createStatement();
            state.executeUpdate("Delete FROM `products` WHERE id = " + id);
            ConnectionDB.closeConnection();
        } catch (SQLException ex) {
            ConnectionDB.closeConnection();
            Logger.getLogger(ProductControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void update(ProductModel product)
    {
        try {
            state = ConnectionDB.openConnection().createStatement();
            state.executeUpdate("UPDATE products set  `name` = "+"'"+product.getName()+"'"+", `number` = "+product.getNumber() +", `price` = " + product.getPrice() +", `type` = "+"'"+product.getType()+"'"+", `discount` = "+product.getDiscount()+" WHERE id = "+product.getId() );
            ConnectionDB.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ProductControl.class.getName()).log(Level.SEVERE, null, ex);
            ConnectionDB.closeConnection();
        }
    }
    // return all data as observable list because table parmetar is observable
   public ObservableList<ProductModel> getAllProduct()
   {
        ObservableList<ProductModel> product =FXCollections.observableArrayList();
        try {
            state = ConnectionDB.openConnection().createStatement();
            ResultSet result =  state.executeQuery("SELECT * FROM products");
            
            while(result.next())
            {
             // if define object out while will store last row n time
             ProductModel pr = new ProductModel();   
             pr.setId(result.getInt(1));
             pr.setName(result.getString(2));
             pr.setNumber(result.getInt(3));
             pr.setPrice(result.getDouble(4));
             pr.setType(result.getString(5));
             pr.setDiscount(result.getInt(6));
             product.add(pr);            
            }
            ConnectionDB.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ProductControl.class.getName()).log(Level.SEVERE, null, ex);
            ConnectionDB.closeConnection();
        }
     
       return product;
   }
       // return data which i search about it as observable list because table parmetar is observable
   public ObservableList<ProductModel> getSearchProduct(String name)
   {
        ObservableList<ProductModel> product =FXCollections.observableArrayList();
        try {
            state = ConnectionDB.openConnection().createStatement();
            ResultSet result =  state.executeQuery("SELECT * FROM products WHERE name LIKE '%"+name+"%'");
           
            while(result.next())
            {
             // if define object out while will store last row n time
             ProductModel pr = new ProductModel();   
             pr.setId(result.getInt(1));
             pr.setName(result.getString(2));
             pr.setNumber(result.getInt(3));
             pr.setPrice(result.getDouble(4));
             pr.setType(result.getString(5));
             pr.setDiscount(result.getInt(6));
             product.add(pr);            
            }
            ConnectionDB.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ProductControl.class.getName()).log(Level.SEVERE, null, ex);
            ConnectionDB.closeConnection();
        }
     
       return product;
   }
}