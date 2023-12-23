/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package vouchergym;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author candra
 */
public class FXMLVoucherController implements Initializable {

    @FXML
    private TableView<Voucher> tbl_voucher;
    @FXML
    private Button btn_tambah;
    @FXML
    private TextField tf_search;

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        showdata("");
    }

    public void showdata(String keyword) {

        ObservableList<Voucher> data = FXMLHomeController.data_voucher.load(keyword);

        if (data != null) {

            tbl_voucher.getColumns().clear();
            tbl_voucher.getItems().clear();

            TableColumn col = new TableColumn("Id");
            col.setCellValueFactory(new PropertyValueFactory<Voucher, String>("id"));
            tbl_voucher.getColumns().addAll(col);

            col = new TableColumn("Nama ");
            col.setCellValueFactory(new PropertyValueFactory<Voucher, String>("nama"));
            tbl_voucher.getColumns().addAll(col);

            col = new TableColumn("Harga");
            col.setCellValueFactory(new PropertyValueFactory<Voucher, String>("harga"));
            tbl_voucher.getColumns().addAll(col);

            col = new TableColumn("Quota");
            col.setCellValueFactory(new PropertyValueFactory<Voucher, String>("quota"));
            tbl_voucher.getColumns().addAll(col);

            col = new TableColumn("Jumlah Jam Per Quota");
            col.setCellValueFactory(new PropertyValueFactory<Voucher, String>("jumlah_jam_per_quota"));
            tbl_voucher.getColumns().addAll(col);

            col = new TableColumn("Expired (Hari)");
            col.setCellValueFactory(new PropertyValueFactory<Voucher, String>("expired"));
            tbl_voucher.getColumns().addAll(col);

            tbl_voucher.setItems(data);

        } else {

            Alert a = new Alert(Alert.AlertType.ERROR, "Data kosong", ButtonType.OK);
            a.showAndWait();
            tbl_voucher.getScene().getWindow().hide();

        }
    }

    @FXML
    private void onClickTambah(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLInputVoucher.fxml"));
            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }

        showdata("");
        tf_search.setText("");

        tbl_voucher.getSelectionModel().selectFirst();
        tbl_voucher.requestFocus();
    }

    @FXML
    private void onClickEdit(ActionEvent event) {
        Voucher voucher = new Voucher();
        voucher = tbl_voucher.getSelectionModel().getSelectedItem();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLInputVoucher.fxml"));
            Parent root = (Parent) loader.load();

            FXMLInputVoucherController fxmlInputVoucherController = (FXMLInputVoucherController) loader.getController();
            fxmlInputVoucherController.execute(voucher);

            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }

        showdata(tf_search.getText());

        tbl_voucher.getSelectionModel().selectFirst();
        tbl_voucher.requestFocus();
    }

    @FXML
    private void onClickHapus(ActionEvent event) {
        Voucher voucher = new Voucher();
        voucher = tbl_voucher.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Yakin Ingin Menghapus Data?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            if (FXMLHomeController.data_voucher.delete(voucher.getId())) {
                Alert b = new Alert(Alert.AlertType.INFORMATION, "Data berhasil dihapus", ButtonType.OK);
                b.showAndWait();
            } else {
                Alert b = new Alert(Alert.AlertType.ERROR, "Data gagal dihapus", ButtonType.OK);
                b.showAndWait();
            }

            showdata("");
            tf_search.setText("");

            tbl_voucher.getSelectionModel().selectFirst();
            tbl_voucher.requestFocus();
        }
    }

    @FXML
    private void onInputSearch(ActionEvent event) {
        showdata(tf_search.getText());
    }

}
