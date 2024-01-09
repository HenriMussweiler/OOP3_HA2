package ui.fuhrparkverwaltung;

import awk.fuhrparkverwaltung.AnwendungskernException;
import awk.fuhrparkverwaltung.entity.internal.Fahrzeug;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import ui.menue.ControlledScreen;
import ui.menue.HauptmenueService;
import ui.menue.ScreensController;

import java.net.URL;
import java.util.ResourceBundle;

public class FahrzeugErstellenController implements Initializable, ControlledScreen {

    @FXML
    private Button speichernButton;

    @FXML
    private Button zurueckButton;

    @FXML
    private TextField herstellerField;

    @FXML
    private TextField modellField;

    @FXML
    private TextField ausstattungField;

    @FXML
    private TextField leistungField;

    @FXML
    private TextField kilometerstandField;

    @FXML
    private TextField baujahrField;

    @FXML
    private TextField sitzplaetzeField;

    @FXML
    private ComboBox kraftstoffComboBox;

    @FXML
    private ComboBox getriebeComboBox;

    @FXML
    private ComboBox sharingComboBox;

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
//        erstelleStandorte();
        initComboBoxes();

        //Testdaten erstellen
        HauptmenueService.getFahrzeugePflegen().fahrzeugAnlegen("Audi", "A4", "S-Line", 150, "Benzin", 2015, 10000, "Manuell", 5, "Osnabrück", false);
        HauptmenueService.getFahrzeugePflegen().fahrzeugAnlegen("BMW", "M4", "Sport", 415, "Benzin", 2022, 10000, "Automatik", 5, "Münster", false);
        HauptmenueService.getFahrzeugePflegen().fahrzeugAnlegen("Mercedes-Benz", "C63", "Sport", 380, "Benzin", 2023, 10000, "Automatik", 2, "Bielefeld", false);
    }

    private void erstelleStandorte() throws AnwendungskernException {
        //Prüfen ob die Standorte schon bestehen
//        if (HauptmenueService.getStandortlisteErstellen().sharingListeAusgeben().isEmpty())
//        {
//            Standorte erstellen
//            try {
//                HauptmenueService.getStandortErstellen().standortErstellen("Osnabrück");
//                HauptmenueService.getStandortErstellen().standortErstellen("Münster");
//                HauptmenueService.getStandortErstellen().standortErstellen("Bielefeld");
//                HauptmenueService.getStandortErstellen().standortErstellen("Hamburg");
//                HauptmenueService.getStandortErstellen().standortErstellen("Berlin");
//            } catch (AnwendungskernException e) {
//                e.printStackTrace();
//            }
//        }
    }

    private void initComboBoxes() throws AnwendungskernException {
        initKraftstoffComboBox();
        initGetriebeComboBox();
        initSharingComboBox();
    }

    private void initSharingComboBox() throws AnwendungskernException {
        // Zu Testzwecken nur String einfügen
        ObservableList<String> standorte = FXCollections.observableArrayList("Osnabrück", "Münster", "Bielefeld", "Hamburg", "Berlin");
        sharingComboBox.setItems(standorte);
    }



    private void initGetriebeComboBox() {
        ObservableList<String> getriebe = FXCollections.observableArrayList("Automatik", "Manuell");
        getriebeComboBox.setItems(getriebe);
    }

    private void initKraftstoffComboBox() {
        ObservableList<String> kraftstoffe = FXCollections.observableArrayList("Benzin", "Diesel", "Elektro", "Hybrid/Diesel", "Hybrid/Benzin");
        kraftstoffComboBox.setItems(kraftstoffe);
    }

    @FXML
    public void speichernButtonClicked(ActionEvent actionEvent) throws AnwendungskernException {
        // Prüfen ob alle Felder ausgefüllt sind
        if (herstellerField.getText().isEmpty() || modellField.getText().isEmpty() || ausstattungField.getText().isEmpty() ||
                leistungField.getText().isEmpty() || kilometerstandField.getText().isEmpty() || baujahrField.getText().isEmpty() ||
                sitzplaetzeField.getText().isEmpty() || kraftstoffComboBox.getValue() == null || getriebeComboBox.getValue() == null || sharingComboBox.getValue() == null)
        {
            // Fehlermeldung ausgeben
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fehler");
            alert.setHeaderText("Fehler beim Erstellen des Fahrzeugs");
            alert.setContentText("Bitte füllen Sie alle Felder aus!");
            alert.showAndWait();
        } else
        {
            // Fahrzeug erstellen
            String hersteller = herstellerField.getText();
            String modell = modellField.getText();
            String ausstattung = ausstattungField.getText();
            String leistung = leistungField.getText();
            String kraftstoff = kraftstoffComboBox.getValue().toString();
            String baujahr = baujahrField.getText();
            String kilometerstand = kilometerstandField.getText();
            String getriebe = getriebeComboBox.getValue().toString();
            String sitzplaetze = sitzplaetzeField.getText();
            String sharingStandort = sharingComboBox.getValue().toString();

            Fahrzeug fahrzeug = new Fahrzeug(hersteller, modell, ausstattung, Integer.parseInt(leistung),
                                            kraftstoff, Integer.parseInt(baujahr), Integer.parseInt(kilometerstand),
                                            getriebe, Integer.parseInt(sitzplaetze), sharingStandort, false);

            // Fahrzeug speichern
            HauptmenueService.getFahrzeugePflegen().fahrzeugAnlegen(hersteller, modell, ausstattung, Integer.parseInt(leistung),
                    kraftstoff, Integer.parseInt(baujahr), Integer.parseInt(kilometerstand),
                    getriebe, Integer.parseInt(sitzplaetze), sharingStandort, false);

            // Erfolgsmeldung ausgeben
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erfolg");
            alert.setHeaderText("Fahrzeug erfolgreich erstellt");
            alert.setContentText("Das Fahrzeug wurde erfolgreich erstellt!");
            alert.showAndWait();
        }
    }

    @FXML
    public void zurueckButtonClicked(ActionEvent actionEvent) throws AnwendungskernException, awk.teilnehmerverwaltung.AnwendungskernException {
        myController.setScreen(ui.menue.Hauptmenue.FVW_SCREEN);
    }
}
