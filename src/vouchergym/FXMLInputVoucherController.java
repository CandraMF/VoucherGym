/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package vouchergym;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author candra
 */
public class FXMLInputVoucherController implements Initializable {

    @FXML
    private TextField tf_nama;
    @FXML
    private TextField tf_harga;
    @FXML
    private Button btn_edit_member;
    @FXML
    private Button btn_delete_member;
    @FXML
    private TextField tf_quota;
    @FXML
    private TextField tf_jumlah_jam_per_quota;
    @FXML
    private TextField tf_expired;
    
    boolean edit = false;
    int id = -1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void execute(Voucher data) {
        this.edit = true;
        this.id = data.getId();
        
        tf_nama.setText(String.valueOf(data.getNama()));
        tf_harga.setText(String.valueOf(data.getHarga()));
        tf_quota.setText(String.valueOf(data.getQuota()));
        tf_jumlah_jam_per_quota.setText(String.valueOf(data.getJumlah_jam_per_quota()));
        tf_expired.setText(String.valueOf(data.getExpired()));
    }
    
    @FXML
    private void onClickSimpan(ActionEvent event) {
        Voucher voucher = new Voucher();
        voucher.setNama(tf_nama.getText());
        voucher.setHarga(Integer.parseInt(tf_harga.getText()));
        voucher.setQuota(Integer.parseInt(tf_quota.getText()));
        voucher.setJumlah_jam_per_quota(Integer.parseInt(tf_jumlah_jam_per_quota.getText()));
        voucher.setExpired(Integer.parseInt(tf_expired.getText()));
        
        if(edit){
           voucher.setId(this.id);
        }
        
        FXMLHomeController.data_voucher.setData(voucher);
        
        if (edit) {
            if (FXMLHomeController.data_voucher.update()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data berhasil diubah", ButtonType.OK);
                a.showAndWait();
                onClickBatal(event);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data gagal diubah", ButtonType.OK);
                a.showAndWait();
            }
        } else {
            if (FXMLHomeController.data_voucher.insert()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data berhasil disimpan", ButtonType.OK);
                a.showAndWait();
                onClickBatal(event);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data gagal disimpan", ButtonType.OK);
                a.showAndWait();
            }
        } 
    }

    @FXML
    private void onClickBatal(ActionEvent event) {
        this.id = -1;
        tf_nama.setText("");
        tf_harga.setText("");
        tf_expired.setText("");
        tf_jumlah_jam_per_quota.setText("");
        tf_quota.setText("");
        tf_nama.requestFocus();
    }
    
}
