/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entites.Blocked;
import entites.Utilisateur;
import java.net.URL;
import java.util.Comparator;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import service.UtilisateurService;

/**
 * FXML Controller class
 *
 * @author mahdi
 */
public class DashboardAdminFormController implements Initializable {

    @FXML
    private TableView<Utilisateur> listeCompte;
    @FXML
    private TableColumn<Utilisateur, String> cl_nom;
    @FXML
    private TableColumn<Utilisateur, String> cl_prenom;
    @FXML
    private TableColumn<Utilisateur, String> cl_age;
    @FXML
    private TableColumn<Utilisateur, String> cl_email;
    @FXML
    private TableColumn<Utilisateur, String> cl_ville;
    @FXML
    private TableColumn<Utilisateur, String> cl_numTel;
    @FXML
    private TextField in_search;
    @FXML
    private ComboBox<String> triBox;

    ObservableList<Utilisateur> obl = FXCollections.observableArrayList();
    @FXML
    private Button buttonBlock;
    @FXML
    private TableColumn<?, ?> cl_blocked;

    // FilteredList<Utilisateur> filteredData = new FilteredList<>(obl, b -> true);
    //ObservableList<Utilisateur> dataList = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tableInit();

        afficher();
        search();
    }

    public void tableInit() {

        cl_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        cl_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        cl_age.setCellValueFactory(new PropertyValueFactory<>("DateNaissance"));
        cl_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        cl_ville.setCellValueFactory(new PropertyValueFactory<>("ville"));
        cl_numTel.setCellValueFactory(new PropertyValueFactory<>("numTel"));
        cl_blocked.setCellValueFactory(new PropertyValueFactory<>("blocked"));
        ObservableList<String> data = FXCollections.observableArrayList("Nom", "Prenom", "email");
        triBox.setItems(data);

    }

    public void afficher() {
           obl = FXCollections.observableArrayList();
        UtilisateurService us = new UtilisateurService();
        for (Utilisateur user : us.afficher()) {
            obl.add(user);
            // code block to be executed
        }
        listeCompte.setItems(obl);

    }

    public void search() {

//        in_search.textProperty().addListener((observable, oldValue, newValue) -> {
//            filteredData = new FilteredList<>(obl, b -> true);
//            filteredData.setPredicate(user -> {
//                System.out.println("aaaa2");
//                if (newValue == null || newValue.isEmpty()) {
//                    return true;
//                }
//                String lowerCaseFilter = newValue.toLowerCase();
//
//                if (user.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
//                    return true; // Filter matches username
//                } else if (user.getVille().toLowerCase().indexOf(lowerCaseFilter) != -1) {
//                    return true; // Filter matches ville
//                } else if (user.getPrenom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
//                    return true; // Filter matches prenom
//                } else if (String.valueOf(user.getDateNaissance()).indexOf(lowerCaseFilter) != -1) {
//                    return true; // Filter matches datedenaissance
//                } else if (String.valueOf(user.getNumTel()).indexOf(lowerCaseFilter) != -1) {
//                    return true; // Filter matches numTel
//                } else if (String.valueOf(user.getEmail()).indexOf(lowerCaseFilter) != -1) {
//                    return true;// Filter matches email
//                } else {
//                    return false; // Does not match.
//                }
//            });
//            //obl=filteredData;
//            listeCompte.setItems(filteredData);
//        });
        in_search.textProperty().addListener((observable, oldValue, newValue) -> {
            FilteredList<Utilisateur> filteredData = new FilteredList(obl);
            filteredData.setPredicate(user -> {
                System.out.println("aaaa2");
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (user.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches username
                } else if (user.getVille().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches ville
                } else if (user.getPrenom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches prenom
                } else if (String.valueOf(user.getDateNaissance()).indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches datedenaissance
                } else if (String.valueOf(user.getNumTel()).indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches numTel
                } else if (String.valueOf(user.getEmail()).indexOf(lowerCaseFilter) != -1) {
                    return true;// Filter matches email
                } else if (String.valueOf(user.getBlocked()).indexOf(lowerCaseFilter) != -1) {
                    return true;// Filter matches email
                } else {
                    return false; // Does not match.
                }
            });
            //obl=filteredData;
            SortedList<Utilisateur> sortableData = new SortedList<>(filteredData);
            listeCompte.setItems(sortableData);
            sortableData.comparatorProperty().bind(listeCompte.comparatorProperty());
        });

    }

    @FXML
    private void trier(ActionEvent event) {
//        TableViewSelectionModel selectionModel = listeCompte.getSelectionModel();
//        System.out.println(selectionModel);

        Comparator<Utilisateur> comparator;
        if (triBox.getValue() == "Nom") {
            comparator = Comparator.comparing(Utilisateur::getNom);

        } else if (triBox.getValue() == "Prenom") {
            comparator = Comparator.comparing(Utilisateur::getPrenom);

        } else {
            comparator = Comparator.comparing(Utilisateur::getEmail);

        }
        System.out.println("aaaa1");
//        SortedList<Utilisateur>   sortableData = new SortedList<>(filteredData);
//tableView.setItems(sortableData);
//sortableData.comparatorProperty().bind(tableView.comparatorProperty());

//        FXCollections.sort(obl, comparator);
        listeCompte.setItems(obl);

    }

//    @FXML
//    public void blockUser() {
//        if (listeCompte.getSelectionModel().getSelectedItem() != null) {
//            Utilisateur selectedUser = listeCompte.getSelectionModel().getSelectedItem();
//            System.out.println(selectedUser);
//            selectedUser.setBlocked(Blocked.oui);
//
//        } else {
//            Alert a = new Alert(AlertType.INFORMATION);
//            a.setContentText("aucune ligne sélectionné");
//            a.show();
//
//        }
//
//    }
    @FXML
    private void blockUser(ActionEvent event) {
        if (listeCompte.getSelectionModel().getSelectedItem() != null) {
            Utilisateur selectedUser = listeCompte.getSelectionModel().getSelectedItem();
            //System.out.println(selectedUser);
            UtilisateurService us = new UtilisateurService();
            us.blockUtilisateur(selectedUser);
            //us.getUserByEmail(selectedUser.getEmail())
            //us.getUserByEmail(selectedUser.getEmail()).setBlocked(Blocked.oui);
            afficher();

        } else {
            Alert a = new Alert(AlertType.INFORMATION);
            a.setContentText("aucune ligne sélectionné");
            a.show();

        }
    }

    @FXML
    private void unBlockUser(ActionEvent event) {
        if (listeCompte.getSelectionModel().getSelectedItem() != null) {
            Utilisateur selectedUser = listeCompte.getSelectionModel().getSelectedItem();
            //System.out.println(selectedUser);
            UtilisateurService us = new UtilisateurService();
            us.unBlockUtilisateur(selectedUser);
            //us.getUserByEmail(selectedUser.getEmail())
            //us.getUserByEmail(selectedUser.getEmail()).setBlocked(Blocked.oui);
            afficher();

        } else {
            Alert a = new Alert(AlertType.INFORMATION);
            a.setContentText("aucune ligne sélectionné");
            a.show();

        }
    }

}
