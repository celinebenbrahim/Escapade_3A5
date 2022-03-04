/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import entites.Utilisateur;
import java.net.URL;
import java.util.Comparator;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    ObservableList<Utilisateur> dataList = FXCollections.observableArrayList();
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                ObservableList<String> data = FXCollections.observableArrayList("Nom", "Prenom", "email");
        triBox.setItems(data);
        remplir();
    }

    private void remplir() {

        cl_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        cl_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        cl_age.setCellValueFactory(new PropertyValueFactory<>("DateNaissance"));
        cl_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        cl_ville.setCellValueFactory(new PropertyValueFactory<>("ville"));
        cl_numTel.setCellValueFactory(new PropertyValueFactory<>("numTel"));
        UtilisateurService us = new UtilisateurService();
        for (Utilisateur user : us.afficher()) {
            obl.add(user);
            // code block to be executed
        }

        dataList = obl;
        listeCompte.setItems(dataList);
        FilteredList<Utilisateur> filteredData = new FilteredList<>(dataList, b -> true);
        in_search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(user -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (user.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches username
                } else if (user.getVille().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                } else if (user.getPrenom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                } else if (user.getPrenom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                }  else if (String.valueOf(user.getDateNaissance()).indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                } else if (String.valueOf(user.getNumTel()).indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                } else if (String.valueOf(user.getEmail()).indexOf(lowerCaseFilter) != -1) {
                    return true;// Filter matches email
                } else {
                    return false; // Does not match.
                }
            });
        });

        listeCompte.setItems(filteredData);
    }

    @FXML
    private void trier(ActionEvent event) {
         Comparator<Utilisateur> comparator ;
        if(triBox.getValue()=="Nom"){
         comparator = Comparator.comparing(Utilisateur::getNom);
         
        }
        else if(triBox.getValue()=="Prenom"){
         comparator = Comparator.comparing(Utilisateur::getPrenom);
         
        }
        else{
         comparator = Comparator.comparing(Utilisateur::getEmail);
         
        }
        
        FXCollections.sort(obl, comparator);
           listeCompte.setItems(obl);
 
    }

}
