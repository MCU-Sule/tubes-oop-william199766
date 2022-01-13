/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasitoko.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author 1672036, 1672012
 */
public class ProdukDeleteController extends BaseController{

    /**
     * Initializes the controller class.
     */
    @FXML
    Label kode_barang, nama_barang;
    
    @FXML
    public void hapuskan_action(ActionEvent event) throws SQLException{
        barangModel.deleteProdukData(ProdukController.kode_barang_pub);
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    public void batal_action(ActionEvent event){
        ((Node) event.getSource()).getScene().getWindow().hide();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        kode_barang.setText(ProdukController.kode_barang_pub);
        nama_barang.setText(ProdukController.nama_barang_pub);
    }    
    
}
