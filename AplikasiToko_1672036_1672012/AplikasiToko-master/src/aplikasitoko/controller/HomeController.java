/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasitoko.controller;

import aplikasitoko.lib.Session;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.layout.Pane;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * FXML Controller class
 *
 * @author 1672036, 1672012
 */
public class HomeController extends BaseController {

    /**
     * Initializes the controller class.
     */
    private boolean panel_kiri = true;

    @FXML
    private Pane tab_kiri;

    @FXML
    private BorderPane center;

    @FXML
    private BorderPane home_pane;

    @FXML
    Label pengguna;

    @FXML
    private Button btn_barang, btn_customer, btn_supplier, btn_kasir, btn_laporan, btn_pengaturan;

    private void btnSetDefaultStyle(Button btn) {
        btn.textFillProperty().setValue(Color.BLACK);
        btn.setStyle("-fx-background-color: transparent;");
    }

    private void btnSetSelected(Button btn) {
        btn.textFillProperty().setValue(Color.WHITE);
        btn.setStyle("-fx-background-color: #39ace5;");
    }

    private void resetAllStyle() {
        btnSetDefaultStyle(btn_barang);
        btnSetDefaultStyle(btn_customer);
        btnSetDefaultStyle(btn_supplier);
        btnSetDefaultStyle(btn_kasir);
        btnSetDefaultStyle(btn_laporan);
        btnSetDefaultStyle(btn_pengaturan);
    }

    @FXML
    private void menu_clicked(MouseEvent event) {
        if (panel_kiri) {
            home_pane.setLeft(tab_kiri);
            panel_kiri = false;
        } else {
            home_pane.setLeft(null);
            panel_kiri = true;
        }
    }

    @FXML
    private void btn_barang_action(ActionEvent event) throws IOException {
        setView(center, "Barang.fxml");
        resetAllStyle();
        btnSetSelected(btn_barang);
    }

    @FXML
    private void btn_customer_action(ActionEvent event) throws IOException {
        setView(center, "Customer.fxml");
        resetAllStyle();
        btnSetSelected(btn_customer);
    }

    @FXML
    private void btn_supplier_action(ActionEvent event) throws IOException {
        setView(center, "Supplier.fxml");
        resetAllStyle();
        btnSetSelected(btn_supplier);
    }

    @FXML
    private void btn_kasir_action(ActionEvent event) throws IOException {
        setView(center, "Kasir.fxml");
        resetAllStyle();
        btnSetSelected(btn_kasir);
    }

    @FXML
    private void btn_laporan_action(ActionEvent event) throws IOException {
        setView(center, "Laporan.fxml");
        resetAllStyle();
        btnSetSelected(btn_laporan);
    }

    @FXML
    private void btn_pengaturan_action(ActionEvent event) throws IOException {
        setView(center, "Pengaturan.fxml");
        resetAllStyle();
        btnSetSelected(btn_pengaturan);
    }

    @FXML
    private void logout_action(ActionEvent event) throws IOException {
        Session.sesi = null;
        ((Node) event.getSource()).getScene().getWindow().hide();
        newWindow("Login.fxml", "Login Aplikasi Toko");
    }

    public void login_as_kasir() {
        btn_customer.setDisable(false);
        btn_kasir.setDisable(false);
        btn_laporan.setDisable(false);
        btn_pengaturan.setDisable(false);
    }

    public void login_as_gudang() {
        btn_barang.setDisable(false);
        btn_supplier.setDisable(false);
        btn_pengaturan.setDisable(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        home_pane.setLeft(null);

        btn_barang.setDisable(true);
        btn_customer.setDisable(true);
        btn_supplier.setDisable(true);
        btn_kasir.setDisable(true);
        btn_laporan.setDisable(true);
        btn_pengaturan.setDisable(true);

        if ("1".equals(Session.sesi)) {
            login_as_kasir();
            try {
                pengguna.setText("Selamat Datang, Kasir!");
                btnSetSelected(btn_kasir);
                setView(center, "Kasir.fxml");
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if ("2".equals(Session.sesi)) {
            login_as_gudang();
            try {
                pengguna.setText("Selamat Datang, Gudang!");
                btnSetSelected(btn_barang);
                setView(center, "Barang.fxml");
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (Session.sesi == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Harus Login!");
            alert.setHeaderText("Login diperlukan.");
            alert.setContentText("Untuk mengakses aplikasi ini Anda harus login sebgai kasir atau gudang.");
            alert.showAndWait();
            Platform.exit();
        }
    }

}
