/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package vouchergym;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author candra
 */
public class FXMLAddVoucherMemberController implements Initializable {

    private Member member = new Member();
    ObservableList<Voucher> dataVoucher;

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
    
    @FXML
    private Button btn_beli;
    @FXML
    private TextField tf_expirasi;
    @FXML
    private TextField tf_jam;
    @FXML
    private TextField tf_quota;
    @FXML
    private Text txt_total;
    @FXML
    private TextField tf_diskon;
    @FXML
    private TextField tf_harga;
    @FXML
    private ComboBox<Voucher> cb_voucher;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dataVoucher = FXMLHomeController.data_voucher.load("");
        
        cb_voucher.setItems(dataVoucher);
        cb_voucher.setConverter(new StringConverter<Voucher>() {
            @Override
            public String toString(Voucher voucher) {
                if (voucher != null) {
                    return voucher.getNama();
                }
                return null;
            }

            @Override
            public Voucher fromString(String string) {
                for (Voucher voucher : cb_voucher.getItems()) {
                    if (voucher.getNama().equals(string)) {
                        return voucher;
                    }
                }
                return null;
            }
        });
    }    
    
    void display()
    {
        int diskon = 0;
        int harga = cb_voucher.getValue().getHarga();
        
        if(member.getJenis_member() == 2) {
            diskon = (int) (harga * .25);
        }
        
        int total = harga - diskon;
        
        tf_quota.setText(String.valueOf(cb_voucher.getValue().getQuota()));
        tf_jam.setText(String.valueOf(cb_voucher.getValue().getJumlah_jam_per_quota()));
        tf_expirasi.setText(String.valueOf(cb_voucher.getValue().getExpired()));
        tf_harga.setText(String.valueOf(harga));
        tf_diskon.setText(String.valueOf(diskon));
        
        txt_total.setText("Rp. " + String.valueOf(total));
    }

    @FXML
    private void onChangeVoucher(ActionEvent event) {
        display();
    }

    @FXML
    private void onClickBeli(ActionEvent event) {
        if (cb_voucher.getValue() != null) {
            VoucherMember voucherMember = new VoucherMember();
            voucherMember.setMember(member);
            voucherMember.setVoucher(cb_voucher.getValue());
            
            FXMLHomeController.data_voucher_member.setData(voucherMember);
            
            if (FXMLHomeController.data_voucher_member.insertByIdMember(member.getId())) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data berhasil disimpan", ButtonType.OK);
                a.showAndWait();
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data gagal disimpan", ButtonType.OK);
                a.showAndWait();
            }
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Mohon Pilih Voucher", ButtonType.OK);
                a.showAndWait();
        }
    }
    
}
