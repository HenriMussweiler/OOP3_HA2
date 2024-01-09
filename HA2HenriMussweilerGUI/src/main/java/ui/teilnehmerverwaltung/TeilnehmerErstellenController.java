package ui.teilnehmerverwaltung;

import awk.fuhrparkverwaltung.AnwendungskernException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ui.menue.ControlledScreen;
import ui.menue.HauptmenueService;
import ui.menue.ScreensController;

import java.net.URL;
import java.util.ResourceBundle;

public class TeilnehmerErstellenController implements Initializable, ControlledScreen {

    @FXML
    private Button speichernButton;

    @FXML
    private Button zurueckButton;

    @FXML
    private TextField vornameField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField strasseField;

    @FXML
    private TextField hausnummerField;

    @FXML
    private TextField plzField;

    @FXML
    private TextField ortField;

    @FXML
    private TextField ibanField;

    @FXML
    private TextField mailField;

    @FXML
    private TextField telefonField;

    private ScreensController myController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    @Override
    public void initData() {
        testdatenAnlegen();

    }

    private void testdatenAnlegen() {
        //Prüfen ob bereits Testdaten angelegt wurden
        try {
                //Testdaten anlegen
                HauptmenueService.getTeilnehmerPflegen().teilnehmerAnlegen("Max", "Mustermann",
                        "Musterstraße", "1", "12345", "Musterstadt",
                        "DE123456789", "max.mustermann@gmail.com", "0123456789");
                HauptmenueService.getTeilnehmerPflegen().teilnehmerAnlegen("Maxine", "Musterfrau",
                        "Musterstraße", "2", "12345", "Musterstadt",
                        "DE987654321", "maxine.musterfrau@gmail.com", "9876543210");
                HauptmenueService.getTeilnehmerPflegen().teilnehmerAnlegen("Maximilian", "Muster",
                        "Musterstraße", "3", "12345", "Musterstadt",
                        "DE123456789", "maximilian.muster@gmail.com", "1234567890");
        } catch (awk.teilnehmerverwaltung.AnwendungskernException ex) {
            throw new RuntimeException(ex);
        }
    }

        @FXML
    public void speichernButtonClicked(ActionEvent actionEvent) {
        // Textfelder auslesen
        String vorname = vornameField.getText();
        String name = nameField.getText();
        String strasse = strasseField.getText();
        String hausnummer = hausnummerField.getText();
        String plz = plzField.getText();
        String ort = ortField.getText();
        String iban = ibanField.getText();
        String mail = mailField.getText();
        String telefon = telefonField.getText();

        //Prüfen ob alle Felder ausgefüllt sind
        if (vorname.isEmpty() || name.isEmpty() || strasse.isEmpty() || hausnummer.isEmpty() || plz.isEmpty() || ort.isEmpty() || iban.isEmpty() || mail.isEmpty() || telefon.isEmpty()) {
            //Fehlermeldung ausgeben
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fehler");
            alert.setHeaderText("Fehler beim Anlegen des Teilnehmers");
            alert.setContentText("Bitte füllen Sie alle Felder aus.");
            alert.showAndWait();
        } else {
            //Teilnehmer anlegen
            try {
                HauptmenueService.getTeilnehmerPflegen().teilnehmerAnlegen(vorname, name, strasse, hausnummer, plz, ort, iban, mail, telefon);
                //Erfolgsmeldung ausgeben
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Erfolg");
                alert.setHeaderText("Teilnehmer erfolgreich angelegt");
                alert.setContentText("Der Teilnehmer wurde erfolgreich angelegt!");
                alert.showAndWait();

                //Textfelder leeren
                vornameField.clear();
                nameField.clear();
                strasseField.clear();
                hausnummerField.clear();
                plzField.clear();
                ortField.clear();
                ibanField.clear();
                mailField.clear();
                telefonField.clear();
            } catch (Exception e) {
                throw new RuntimeException(e);
            } catch (awk.teilnehmerverwaltung.AnwendungskernException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    public void zurueckButtonClicked(ActionEvent actionEvent) throws AnwendungskernException, awk.teilnehmerverwaltung.AnwendungskernException {
        myController.setScreen(ui.menue.Hauptmenue.TVW_SCREEN);
    }
}
