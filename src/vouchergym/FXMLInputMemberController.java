/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package vouchergym;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author candra
 */
public class FXMLInputMemberController implements Initializable {

    @FXML
    private TextField tf_nama;
    @FXML
    private TextField tf_no_telp;
    @FXML
    private Button btn_delete_member;
    @FXML
    private Button btn_edit_member;
    @FXML
    private ComboBox<String> cb_jenis;
    
    boolean edit = false;
    int id = -1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> jenisMember = FXCollections.observableArrayList();
        jenisMember.add("Regular");
        jenisMember.add("Premium");
        
       cb_jenis.setItems(jenisMember);
       cb_jenis.setValue("Regular");

    }    
    
    public void execute(Member data) {
        this.edit = true;
        this.id = data.getId();
        
        tf_nama.setText(String.valueOf(data.getNama()));
        tf_no_telp.setText(String.valueOf(data.getNo_telp()));
        cb_jenis.setValue(data.getJenis_member() == 1 ? "Regular" : "Premium");
    }

    @FXML
    private void onClickSimpan(ActionEvent event) {
        Member member = new Member();
        member.setNama(tf_nama.getText());
        member.setNo_telp(tf_no_telp.getText());
        member.setJenis_member("Regular".equals(cb_jenis.getValue()) ? 1 : 2 );
        
        System.out.println(tf_nama.getText());
        
        if(edit){
           member.setId(this.id);
        }
        
        FXMLHomeController.data_member.setData(member);
        
        if (edit) {
            if (FXMLHomeController.data_member.update()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data berhasil diubah", ButtonType.OK);
                a.showAndWait();
                onClickBatal(event);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data gagal diubah", ButtonType.OK);
                a.showAndWait();
            }
        } else {
            if (FXMLHomeController.data_member.insert()) {
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
        tf_no_telp.setText("");
        cb_jenis.setValue("Regular");
        tf_nama.requestFocus();
    }
    
}
