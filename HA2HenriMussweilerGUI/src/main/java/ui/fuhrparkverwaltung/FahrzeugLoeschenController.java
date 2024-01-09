package ui.fuhrparkverwaltung;

import awk.fuhrparkverwaltung.AnwendungskernException;
import awk.fuhrparkverwaltung.entity.FahrzeugTO;
import awk.fuhrparkverwaltung.entity.internal.Fahrzeug;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import ui.menue.ControlledScreen;
import ui.menue.HauptmenueService;
import ui.menue.ScreensController;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class FahrzeugLoeschenController implements Initializable, ControlledScreen {

    @FXML
    private Button zurueckButton;

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

    @FXML
    private Button loeschenButton;

    @FXML
    private ComboBox<Long> fahrzeugComboBox;

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
        initFahrzeugComboBox();
        initTableView();
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

    @FXML
    public void zurueckButtonClicked(ActionEvent actionEvent) throws AnwendungskernException, awk.teilnehmerverwaltung.AnwendungskernException {
        myController.setScreen(ui.menue.Hauptmenue.FVW_SCREEN);
    }

    private void initTableView() throws AnwendungskernException {
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

    public void loeschenButtonClicked(ActionEvent actionEvent) throws AnwendungskernException {
        FahrzeugTO fahrzeugTO = HauptmenueService.getFahrzeugSuchen().fahrzeugSuchenById(fahrzeugComboBox.getValue());

        // Abfragen ob das Fahrzeug wirklich gelöscht werden soll
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Fahrzeug löschen");
        alert.setHeaderText("Fahrzeug wirklich löschen?");
        alert.setContentText("Soll das Fahrzeug wirklich gelöscht werden?");
        Optional<ButtonType> result = alert.showAndWait();

        // Prüfen ob das Fahrzeug noch in einer Ausleihe geplant ist
        if (HauptmenueService.getAusleihvorgangSuchen().ausleihvorgangSuchenByFahrzeugId(fahrzeugTO.getFahrzeugId()).stream()
                .anyMatch(ausleihvorgangTO -> ausleihvorgangTO.getAbgeschlossen().equals("N")))
        {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setTitle("Fahrzeug löschen");
            alert2.setHeaderText("Fahrzeug kann nicht gelöscht werden");
            alert2.setContentText("Das Fahrzeug ist noch in einer Ausleihe geplant und kann daher nicht gelöscht werden.");
            alert2.showAndWait();
            return;
        }

        if (fahrzeugTO != null) {
            try {
                HauptmenueService.getFahrzeugePflegen().fahrzeugLoeschen(fahrzeugTO.getFahrzeugId());
                initTableView();
            } catch (AnwendungskernException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
