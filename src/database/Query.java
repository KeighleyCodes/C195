package database;

import com.mysql.cj.x.protobuf.MysqlxPrepare;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Query {

    public static ObservableList<Customer> getAllCustomers() {

        ObservableList<Customer> allCustomers = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM Customers";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return allCustomers;
    }
}
