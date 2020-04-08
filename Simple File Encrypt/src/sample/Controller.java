package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Controller {

    @FXML
    private javafx.scene.control.Label FileLabel;
    @FXML
    private Stage primaryStage;
    @FXML
    private Stage secondaryStage;
    @FXML
    private TextField encryptedKey;


    public String showFile(Stage primaryStage) throws Exception {
        FileChooser file = new FileChooser();
        file.setTitle("Open FileLabel");
        String result = String.valueOf(file.showOpenDialog(primaryStage));
        System.out.println(result);
        if (result == null) {
            FileLabel.setText("You don't select any file ! Please select one !");
        } else {
            FileLabel.setText("You encrypt the file " + result);
        }
        return result;
    }

    public File saveEncryptedFile(Stage secondaryStage) throws Exception {
        FileChooser myFile = new FileChooser();
        myFile.setTitle("Save the file ! ");
        myFile.setInitialFileName("ENCRYPTED.");

        File file = myFile.showSaveDialog(secondaryStage);
        if (file != null) {
            myFile.setInitialDirectory(file.getParentFile());
            System.out.println("File path for your saved file is: " + file.getAbsolutePath());
        }
        return file;
    }

    public void encryptedButton(ActionEvent event) throws Exception {
        String key = encryptedKey.getText();
        while (key.length() < 16) {
            key = key.concat("0");
        }
        if (key.length() > 16) {
            System.out.println(key);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("The key for encrypt is to long  ! ");
            alert.show();
            System.out.println("The key for crypting have more than 128 bits !");
            System.out.println("Write a new one !");
        } else {
            String file = showFile(primaryStage);
            File inputFile = new File(file);
            File outFile = saveEncryptedFile(secondaryStage);
            File encryptedFile = new File(String.valueOf(outFile));
            try {
                CryptoUtils.encrypt(key, inputFile, encryptedFile);
                System.out.println(encryptedFile.getAbsolutePath());
                System.out.println(key);
                System.out.println(key.length());
            } catch (IOException | NoSuchAlgorithmException | InvalidKeyException |
                    NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException e) {
                e.printStackTrace();
            } finally {
                System.out.println("File: " + inputFile + " was succesfull encrypted AES 128 bit  !");
            }
        }
    }

    public File saveDecryptedFile(Stage secondaryStage) throws Exception {
        FileChooser myFile = new FileChooser();
        myFile.setTitle("Save the file ! ");
        myFile.setInitialFileName("DECRYPTED.");

        File file = myFile.showSaveDialog(secondaryStage);
        if (file != null) {
            myFile.setInitialDirectory(file.getParentFile());
            System.out.println("File path for your saved file is: " + file.getAbsolutePath());
        }
        return file;
    }

    public void decryptedButton(ActionEvent event) throws Exception {
        Alert message = new Alert(Alert.AlertType.WARNING);
        message.setContentText("Use correct key to decrypt otherwise the file is not decrypted and saved ! ");
        message.show();
        String key = encryptedKey.getText();
        while (key.length() < 16) {
            key = key.concat("0");
        }
        if (key.length() > 16) {
            System.out.println(key);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("The key for encrypt is to long  ! ");
            alert.show();
            System.out.println("The key for decrypting have more than 128 bits !");
            System.out.println("Write a new one !");
        } else {
            String file = showFile(primaryStage);
            File inputFile = new File(file);
            File outFile = saveDecryptedFile(secondaryStage);
            File decryptedFile = new File(String.valueOf(outFile));
            try {
                CryptoUtils.decrypt(key, inputFile, decryptedFile);
                System.out.println(decryptedFile.getAbsolutePath());
                System.out.println(key);
                System.out.println(key.length());
            } catch (IOException | NoSuchAlgorithmException | InvalidKeyException |
                    NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException e) {
                e.printStackTrace();
            } finally {
                System.out.println("File: " + inputFile + " was succesfull decrypt AES 128 bit  !");
            }
        }
    }

    public boolean saveToFile(Object data, File location) {
        FileChooser chooser = new FileChooser();
        location = chooser.showSaveDialog(new Stage());
        if (location == null) {
            return false;
        }
        saveToFile(data, location);
        return true;
    }
}