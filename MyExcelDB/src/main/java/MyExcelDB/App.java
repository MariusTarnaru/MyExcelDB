package MyExcelDB;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class App {
    public static void main(String[] args) {

        String csvFile = "D:\\JAVA\\InteliJ\\MyExcelDB\\src\\MagazieDB2019.csv";
        BufferedReader br = null;
        String line;
        String csvSplitBy = ";";

        try {
            br = new BufferedReader(new FileReader(csvFile));
                    // line = br.readLine(); //first line not read
            while ((line = br.readLine()) != null) {
                // use comma at separator
                String[] myParams = line.split(csvSplitBy);
                System.out.println(myParams[0] + " " +  myParams[1] +" "+ myParams[2] + " " + myParams[3] + " " + myParams[4] + " " + myParams[5] + " " + myParams[6]);
                String codMagazie = myParams[0];
                String numeCodMagazie = myParams[1];
                String denumireProdus = myParams[2];
                String categorie = myParams[3];
                String codProdus = myParams[4];
                String unitateDeMasura = myParams[5];
                double cantitate = Double.parseDouble(myParams[6]);
                //String notes = myParams[7];

                Connection connection = ServiceDB.getConnection();

                String sql = "INSERT INTO `product` (`product_cod`, `category_name`, `product_name`, " +
                        "`quantity`,`UM`) VALUES (?, ?, ?, ?, ?);";

                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, codProdus);
                preparedStatement.setString(2, categorie);
                preparedStatement.setString(3, denumireProdus);
                preparedStatement.setInt(4, (int) cantitate);
                preparedStatement.setString(5, unitateDeMasura);
                // preparedStatement.setString(6, notes);
                //preparedStatement.executeUpdate();

                String sql2 = "INSERT INTO `store` (`store_cod`,`store_name`,`product_id`) VALUES (?, ?, ?);";
                preparedStatement.setString(1, codMagazie);
                preparedStatement.setString(2, numeCodMagazie);
                preparedStatement.executeUpdate(sql2);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}