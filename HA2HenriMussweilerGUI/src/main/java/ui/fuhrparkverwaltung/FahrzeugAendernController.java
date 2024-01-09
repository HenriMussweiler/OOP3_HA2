package ui.fuhrparkverwaltung;

import awk.fuhrparkverwaltung.AnwendungskernException;
import awk.fuhrparkverwaltung.entity.FahrzeugTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ui.menue.ControlledScreen;
import ui.menue.Hauptmenue;
import ui.menue.HauptmenueService;
import ui.menue.ScreensController;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class FahrzeugAendernController implements Initializable, ControlledScreen {

    @FXML
    private Button speichernButton;

    @FXML
    private Button zurueckButton;

    @FXML
    private Button aendernButton;

    @FXML
    private ComboBox fahrzeugComboBox;

    @FXML
    private TableView fahrzeugTableView;

    @FXML
    private TableColumn fahrzeugIdColumn;

    @FXML
    private TableColumn modellColumn;

    @FXML
    private TableColumn herstellerColumn;

    @FXML
    private TableColumn ausstattungColumn;

    @FXML
    private TableColumn baujahrColumn;

    @FXML
    private TableColumn getriebeColumn;

    @FXML
    private TableColumn kilometerstandColumn;

    @FXML
    private TableColumn kraftstoffartColumn;

    @FXML
    private TableColumn leistungColumn;

    @FXML
    private TableColumn sitzplaetzeColumn;

    @FXML
    private TableColumn sharingStandortColumn;

    private ScreensController myController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    @Override
    public void initData() throws AnwendungskernException {
        initFahrzeugTableView();
        initFahrzeugComboBox();
    }

    private void initFahrzeugComboBox() throws AnwendungskernException {

        // Prüfen ob Fahrzeuge vorhanden sind
        if (HauptmenueService.getFahrzeuglisteErstellen().fahrzeugListeErstellen().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Keine Fahrzeuge vorhanden");
            alert.setContentText("Es sind keine Fahrzeuge vorhanden. Bitte legen Sie zuerst ein Fahrzeug an.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Fahrzeug auswählen");
            alert.setContentText("Bitte wählen Sie ein Fahrzeug aus.");
            alert.showAndWait();

            // Alle Fahrzeuge laden und in die Combobox einfügen
            ObservableList<Long> fahrzeuge = FXCollections.observableArrayList(HauptmenueService.getFahrzeuglisteErstellen().fahrzeugListeErstellen()).stream()
                    .filter(fahrzeug -> !fahrzeug.isDeleted())
                    .map(FahrzeugTO::getFahrzeugId)
                    .collect(Collectors.toCollection(FXCollections::observableArrayList));

            fahrzeugComboBox.setItems(fahrzeuge);
        }
    }

    private void initFahrzeugTableView() throws AnwendungskernException {
        fahrzeugIdColumn.setCellValueFactory(new PropertyValueFactory<>("fahrzeugId"));
        herstellerColumn.setCellValueFactory(new PropertyValueFactory<>("hersteller"));
        modellColumn.setCellValueFactory(new PropertyValueFactory<>("modell"));
        ausstattungColumn.setCellValueFactory(new PropertyValueFactory<>("ausstattung"));
        baujahrColumn.setCellValueFactory(new PropertyValueFactory<>("baujahr"));
        getriebeColumn.setCellValueFactory(new PropertyValueFactory<>("getriebe"));
        kilometerstandColumn.setCellValueFactory(new PropertyValueFactory<>("kilometerstand"));
        kraftstoffartColumn.setCellValueFactory(new PropertyValueFactory<>("kraftstoffart"));
        leistungColumn.setCellValueFactory(new PropertyValueFactory<>("leistungKw"));
        sitzplaetzeColumn.setCellValueFactory(new PropertyValueFactory<>("sitzplaetze"));
        sharingStandortColumn.setCellValueFactory(new PropertyValueFactory<>("sharingStandort"));

        ObservableList<FahrzeugTO> fahrzeuge = FXCollections.observableArrayList(HauptmenueService.getFahrzeuglisteErstellen().fahrzeugListeErstellen());

        fahrzeuge = FXCollections.observableArrayList(fahrzeuge.stream()
                .filter(fahrzeugTO -> !fahrzeugTO.isDeleted())
                .collect(Collectors.toList()));

        fahrzeugTableView.setItems(fahrzeuge);
    }

    @FXML
    public void zurueckButtonClicked(ActionEvent actionEvent) throws AnwendungskernException, awk.teilnehmerverwaltung.AnwendungskernException {
        myController.setScreen(Hauptmenue.FVW_SCREEN);
    }

    @FXML
    public void aendernButtonClicked(ActionEvent actionEvent) throws AnwendungskernException, awk.teilnehmerverwaltung.AnwendungskernException {

        FahrzeugTO fahrzeug = null;

        //Fahrzeug auswählen
        if (fahrzeugComboBox.getValue() == null) {
            System.out.println("Kein Fahrzeug ausgewählt");
            return;
        } else {
            System.out.println("Fahrzeug ausgewählt" + fahrzeugComboBox.getValue());
            Long selectedFahrzeugId = (Long) fahrzeugComboBox.getValue();
            if (selectedFahrzeugId != null) {
                fahrzeug = HauptmenueService.getFahrzeugSuchen().fahrzeugSuchenById(selectedFahrzeugId);
            }
        }

        HauptmenueService.getFahrzeugAendern().setSelectedFahrzeug(fahrzeug.getFahrzeugId());


        myController.setScreen(Hauptmenue.FAHRZEUG_AENDERN_ERWEITERUNG_SCREEN);
    }
}
