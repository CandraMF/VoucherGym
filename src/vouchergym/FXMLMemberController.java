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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author candra
 */
public class FXMLMemberController implements Initializable {

    @FXML
    private TableView<Member> tbl_member;
    @FXML
    private Button btn_delete_member;
    @FXML
    private Button btn_edit_member;
    @FXML
    private Button btn_delete_voucher;
    @FXML
    private Button btn_tambah_member;
    @FXML
    private Button btn_tambah_voucher;
    @FXML
    private TableView<VoucherMember> tbl_voucher;
    @FXML
    private Button btn_gunakan;
    @FXML
    private TextField tf_search;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showDataMember("");
        btn_gunakan.setVisible(false);
    } 
    
    public void kasir()
    {
        btn_delete_member.setVisible(false);
        btn_delete_voucher.setVisible(false);
        btn_edit_member.setVisible(false);
        btn_tambah_member.setVisible(false);
        btn_tambah_voucher.setVisible(false);        
        btn_gunakan.setVisible(true);
    }
    
    public void simulasipembelianvoucher()
    {
        btn_delete_member.setVisible(false);
        btn_delete_voucher.setVisible(false);
        btn_edit_member.setVisible(false);
        btn_tambah_member.setVisible(false);
        btn_gunakan.setVisible(false);
    }
    

    public void showDataMember(String keyword) {
        
        ObservableList<Member> data = FXMLHomeController.data_member.load(keyword);
        
        if (data != null) {
            
            tbl_member.getColumns().clear();
            tbl_member.getItems().clear();
            
            TableColumn col = new TableColumn("Id");
            col.setCellValueFactory(new PropertyValueFactory<Member, String>("id"));
            tbl_member.getColumns().addAll(col);
            
            col = new TableColumn("Nama");
            col.setCellValueFactory(new PropertyValueFactory<Member, String>("nama"));
            tbl_member.getColumns().addAll(col);
            
            col = new TableColumn("Nomor Telepon");
            col.setCellValueFactory(new PropertyValueFactory<Member, String>("no_telp"));
            tbl_member.getColumns().addAll(col);
            
            col = new TableColumn("Tanggal Daftar");
            col.setCellValueFactory(new PropertyValueFactory<Member, String>("tanggal_daftar"));
            tbl_member.getColumns().addAll(col);
            
            col = new TableColumn("Jenis Member");
            col.setCellValueFactory(new PropertyValueFactory<Member, Integer>("jenis_member"));
            col.setCellFactory(column -> {
                return new TableCell<Member, Integer>() {
                    @Override
                    protected void updateItem(Integer item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item == null || empty) {
                            setText(null);
                        } else {
                            // Mengonversi nilai integer ke string sebelum menampilkannya
                            if (item == 1) {
                                setText("Reguler");
                            } else {
                                setText("Premium");
                            }
                        }
                    }
                };
            });

            
            tbl_member.getColumns().addAll(col);
            tbl_member.setItems(data);
            
            tbl_member.getSelectionModel().selectFirst();
            tbl_member.requestFocus();
            
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data kosong", ButtonType.OK);
            a.showAndWait();
            tbl_member.getScene().getWindow().hide();
        }
        
    }
    
    public void showDataVoucherByIdMember(int id_member) {
        
        ObservableList<VoucherMember> data = FXMLHomeController.data_voucher_member.loadByIdMember(id_member);
        
        if (data != null) {
            
            tbl_voucher.getColumns().clear();
            tbl_voucher.getItems().clear();
            
            TableColumn col = new TableColumn("Id");
            col.setCellValueFactory(new PropertyValueFactory<VoucherMember, String>("id"));
            tbl_voucher.getColumns().addAll(col);
            
            col = new TableColumn("Voucher");
            col.setCellValueFactory(new PropertyValueFactory<VoucherMember, Voucher>("voucher"));
            col.setCellFactory(column -> {
                return new TableCell<VoucherMember, Voucher>() {
                    @Override
                    protected void updateItem(Voucher item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item == null || empty) {
                            setText(null);
                        } else {
                            setText(item.getNama());
                        }
                    }
                };
            });
            tbl_voucher.getColumns().addAll(col);
            
            col = new TableColumn("Quota");
            col.setCellValueFactory(new PropertyValueFactory<VoucherMember, Voucher>("voucher"));
            col.setCellFactory(column -> {
                return new TableCell<VoucherMember, Voucher>() {
                    @Override
                    protected void updateItem(Voucher item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item == null || empty) {
                            setText(null);
                        } else {
                            setText(String.valueOf(item.getQuota()));
                        }
                    }
                };
            });
            tbl_voucher.getColumns().addAll(col);
            
            col = new TableColumn("Jumlah Penggunaan");
            col.setCellValueFactory(new PropertyValueFactory<VoucherMember, String>("jumlah_penggunaan"));
            tbl_voucher.getColumns().addAll(col);
            
            col = new TableColumn("Tanggal Pembelian");
            col.setCellValueFactory(new PropertyValueFactory<VoucherMember, String>("tanggal_pembelian"));
            tbl_voucher.getColumns().addAll(col);
            
            col = new TableColumn("Sisa Hari");
            col.setCellValueFactory(new PropertyValueFactory<VoucherMember, Voucher>("due"));
            tbl_voucher.getColumns().addAll(col);
            
            tbl_voucher.setItems(data);
            
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data kosong", ButtonType.OK);
            a.showAndWait();
            tbl_member.getScene().getWindow().hide();
        }
        
    }
    
    @FXML
    private void onClickHapusMember(ActionEvent event) {
        
        Member member = new Member();
        member = tbl_member.getSelectionModel().getSelectedItem();
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Yakin Ingin Menghapus Data?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        
        if (alert.getResult() == ButtonType.YES) {
            if (FXMLHomeController.data_member.delete(member.getId())) {
                Alert b = new Alert(Alert.AlertType.INFORMATION, "Data berhasil dihapus", ButtonType.OK);
                b.showAndWait();
            } else {
                Alert b = new Alert(Alert.AlertType.ERROR, "Data gagal dihapus", ButtonType.OK);
                b.showAndWait();
            }
            
            showDataMember("");
            tf_search.setText("");
        
            tbl_member.getSelectionModel().selectFirst();
            tbl_member.requestFocus();
        }
    }

    @FXML
    private void onClickEditMember(ActionEvent event) {
        Member member = new Member();
        member = tbl_member.getSelectionModel().getSelectedItem();
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLInputMember.fxml"));
            Parent root = (Parent) loader.load();
            
            FXMLInputMemberController fxmlInputMemberController = (FXMLInputMemberController) loader.getController();
            fxmlInputMemberController.execute(member);
            
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
        
        showDataMember(tf_search.getText());
        
        tbl_member.getSelectionModel().selectFirst();
        tbl_member.requestFocus();
    }

    @FXML
    private void onClickTambahMember(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLInputMember.fxml"));
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
         
        showDataMember("");
        tf_search.setText("");
        
        tbl_member.getSelectionModel().selectFirst();
        tbl_member.requestFocus();  
    }

    @FXML
    private void onClickTableMember(MouseEvent event) {
        showDataVoucherByIdMember(tbl_member.getSelectionModel().getSelectedItem().getId());
    }

    @FXML
    private void onClickHapusVoucher(ActionEvent event) {
        VoucherMember voucherMember = new VoucherMember();
        voucherMember = tbl_voucher.getSelectionModel().getSelectedItem();
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Yakin Ingin Menghapus Data?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        
        if (alert.getResult() == ButtonType.YES) {
            if (FXMLHomeController.data_voucher_member.delete(voucherMember.getId())) {
                Alert b = new Alert(Alert.AlertType.INFORMATION, "Data berhasil dihapus", ButtonType.OK);
                b.showAndWait();
            } else {
                Alert b = new Alert(Alert.AlertType.ERROR, "Data gagal dihapus", ButtonType.OK);
                b.showAndWait();
            }
            
            showDataVoucherByIdMember(tbl_member.getSelectionModel().getSelectedItem().getId());
        
            tbl_voucher.getSelectionModel().selectFirst();
            tbl_voucher.requestFocus();
        }
    }

    @FXML
    private void onClickTambahVoucher(ActionEvent event) {
        
        Member member = new Member();
        member = tbl_member.getSelectionModel().getSelectedItem();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLAddVoucherMember.fxml"));
            Parent root = (Parent) loader.load();
            
            FXMLAddVoucherMemberController addVoucherMemberController = (FXMLAddVoucherMemberController) loader.getController();
            addVoucherMemberController.setMember(member);

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
        
        showDataVoucherByIdMember(tbl_member.getSelectionModel().getSelectedItem().getId());
        
        tbl_voucher.getSelectionModel().selectFirst();
        tbl_voucher.requestFocus();  
    }

    @FXML
    private void onClickGunakan(ActionEvent event) {
        VoucherMember voucherMember = new VoucherMember();
        voucherMember = tbl_voucher.getSelectionModel().getSelectedItem();
        
        if (voucherMember.getDue() < 0) {
            Alert b = new Alert(Alert.AlertType.ERROR, "Voucher Sudah Kadaluarsa", ButtonType.OK);
            b.showAndWait();
        } else {
            if(voucherMember.getVoucher().getQuota() > voucherMember.getJumlah_penggunaan()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Yakin Ingin Menggunakan Voucher?", ButtonType.YES, ButtonType.NO);
                alert.showAndWait();

                if (alert.getResult() == ButtonType.YES) {
                    if (FXMLHomeController.data_penggunaan_voucher.insertByIdVoucherMember(voucherMember.getId())) {
                        Alert b = new Alert(Alert.AlertType.INFORMATION, "Voucher Berhasil Digunakan", ButtonType.OK);
                        b.showAndWait();
                    } else {
                        Alert b = new Alert(Alert.AlertType.ERROR, "Voucher Gagal Digunakan", ButtonType.OK);
                        b.showAndWait();
                    }
                    
                    showDataVoucherByIdMember(tbl_member.getSelectionModel().getSelectedItem().getId());
                    
                    tbl_voucher.getSelectionModel().selectFirst();
                    tbl_voucher.requestFocus();
                }
            } else {
                Alert b = new Alert(Alert.AlertType.ERROR, "Voucher Sudah Habis", ButtonType.OK);
                b.showAndWait();
            }
        } 
    }

    @FXML
    private void onInputSearch(ActionEvent event) {
         showDataMember(tf_search.getText());
    }
    
}
