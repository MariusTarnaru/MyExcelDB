package MyExcelDB;

import java.io.BufferedReader;
import java.sql.*;

public class QuerySQL {

    public static void main(String[] args) {


        String csvFile = "D:\\JAVA\\InteliJ\\MyExcelDB\\src\\InventarDB2019.csv";
        BufferedReader br = null;
        String line;
        String csvSplitBy = ";";


        Connection connection = ServiceDB.getConnection();
        String sql = "INSERT INTO `product` (`product_cod`, `category_name`, `product_name`, `quantity`, `UM`) VALUES ('h5', 'mecanic', 'Rulment 6901 ZZ', '37', 'buc');";
        //String sql = "INSERT INTO product" + " VALUES(100,'electric', 'Sensor pnp', 4, 'buc','no');";
        //String sql = "DELETE FROM product WHERE product_id = 100;";


        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            if (connection != null) {
                System.out.println("S-a creat statementul !" + preparedStatement);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


