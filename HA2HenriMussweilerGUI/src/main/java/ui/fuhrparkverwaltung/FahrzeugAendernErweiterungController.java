package ui.fuhrparkverwaltung;

import awk.fuhrparkverwaltung.AnwendungskernException;
import awk.fuhrparkverwaltung.entity.FahrzeugTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import ui.menue.ControlledScreen;
import ui.menue.Hauptmenue;
import ui.menue.HauptmenueService;
import ui.menue.ScreensController;

import java.net.URL;
import java.util.ResourceBundle;

public class FahrzeugAendernErweiterungController implements Initializable, ControlledScreen {

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
    private ComboBox kraftstoffField;

    @FXML
    private TextField baujahrField;

    @FXML
    private ComboBox getriebeField;

    @FXML
    private TextField sitzplaetzeField;

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
        populateFields();
    }

    private void populateFields() throws AnwendungskernException {
        //Fahrzeug laden
        FahrzeugTO fahrzeug = HauptmenueService.getFahrzeugSuchen().fahrzeugSuchenById(HauptmenueService.getFahrzeugAendern().getSelectedFahrzeug());

        //Fahrzeug in die Felder einfügen
        herstellerField.setText(fahrzeug.getHersteller());
        modellField.setText(fahrzeug.getModell());
        ausstattungField.setText(fahrzeug.getAusstattung());
        leistungField.setText(String.valueOf(fahrzeug.getLeistungKw()));
        kilometerstandField.setText(String.valueOf(fahrzeug.getKilometerstand()));
        baujahrField.setText(String.valueOf(fahrzeug.getBaujahr()));
        sitzplaetzeField.setText(String.valueOf(fahrzeug.getSitzplaetze()));

        //Kraftstoffart
        ObservableList<String> kraftstoffe = FXCollections.observableArrayList("Benzin", "Diesel", "Elektro", "Hybrid/Diesel", "Hybrid/Benzin");
        kraftstoffField.setItems(kraftstoffe);
        kraftstoffField.setValue(fahrzeug.getKraftstoffart());

        //Getriebeart
        ObservableList<String> getriebe = FXCollections.observableArrayList("Automatik", "Manuell");
        getriebeField.setItems(getriebe);
        getriebeField.setValue(fahrzeug.getGetriebe());

        //SharingStandort
        ObservableList<String> sharingStandorte = FXCollections.observableArrayList("Osnabrück", "Münster", "Bielefeld", "Hamburg", "Berlin");
        sharingComboBox.setItems(sharingStandorte);
        sharingComboBox.setValue(fahrzeug.getSharingStandort());
    }

    @FXML
    public void speichernButtonClicked(ActionEvent actionEvent) throws AnwendungskernException, awk.teilnehmerverwaltung.AnwendungskernException {
        // Prüfen ob alle Felder ausgefüllt sind
        if (herstellerField.getText().isEmpty() || modellField.getText().isEmpty() || ausstattungField.getText().isEmpty() ||
                leistungField.getText().isEmpty() || kilometerstandField.getText().isEmpty() || baujahrField.getText().isEmpty() ||
                sitzplaetzeField.getText().isEmpty() || kraftstoffField.getValue() == null || getriebeField.getValue() == null || sharingComboBox.getValue() == null)
        {
            // Fehlermeldung ausgeben
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fehler");
            alert.setHeaderText("Fehler beim Erstellen des Fahrzeugs");
            alert.setContentText("Bitte füllen Sie alle Felder aus!");
            alert.showAndWait();
        } else
        {
            //Fahrzeug laden
            FahrzeugTO fahrzeugTO = HauptmenueService.getFahrzeugSuchen().fahrzeugSuchenById(HauptmenueService.getFahrzeugAendern().getSelectedFahrzeug());

            //Fahrzeug aktualisieren
            fahrzeugTO.setHersteller(herstellerField.getText());
            fahrzeugTO.setModell(modellField.getText());
            fahrzeugTO.setAusstattung(ausstattungField.getText());
            fahrzeugTO.setLeistungKw(Integer.parseInt(leistungField.getText()));
            fahrzeugTO.setKilometerstand(Integer.parseInt(kilometerstandField.getText()));
            fahrzeugTO.setBaujahr(Integer.parseInt(baujahrField.getText()));
            fahrzeugTO.setSitzplaetze(Integer.parseInt(sitzplaetzeField.getText()));
            fahrzeugTO.setKraftstoffart((String) kraftstoffField.getValue());
            fahrzeugTO.setGetriebe((String) getriebeField.getValue());
            fahrzeugTO.setSharingStandort((String) sharingComboBox.getValue());

            //Fahrzeug aktualisieren
            HauptmenueService.getFahrzeugAendern().fahrzeugAendern(fahrzeugTO);

            //Erfolgsmeldung
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Fahrzeug ändern");
            alert.setHeaderText("Fahrzeug erfolgreich geändert");
            alert.setContentText("Das Fahrzeug wurde erfolgreich geändert.");
            alert.showAndWait();

            //Zurück zum FahrzeugAendernScreen
            myController.setScreen(Hauptmenue.FAHRZEUG_AENDERN_SCREEN);
        }
    }

    @FXML
    public void zurueckButtonClicked(ActionEvent actionEvent) throws AnwendungskernException, awk.teilnehmerverwaltung.AnwendungskernException {
        myController.setScreen(Hauptmenue.FAHRZEUG_AENDERN_SCREEN);
    }
}
