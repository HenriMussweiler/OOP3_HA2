package ui.ausleihvorgang;

import awk.fuhrparkverwaltung.AnwendungskernException;
import awk.fuhrparkverwaltung.entity.FahrzeugTO;
import awk.leihvorgang.entity.AusleihvorgangTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import jfxtras.scene.control.LocalDateTimeTextField;
import ui.menue.ControlledScreen;
import ui.menue.HauptmenueService;
import ui.menue.ScreensController;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class AusleihvorgangAbschliessenErweiterungController implements Initializable, ControlledScreen {

    @FXML
    private Button speichernButton;

    @FXML
    private Button zurueckButton;

    @FXML
    private ComboBox teilnehmerComboBox;

    @FXML
    private ComboBox fahrzeugComboBox;

    @FXML
    private LocalDateTimeTextField startdatumPicker;

    @FXML
    private LocalDateTimeTextField enddatumPicker;

    @FXML
    private TextField gefahreneKilometerTextField;

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
        initSelectedAusleihvorgang();

    }

    private void initSelectedAusleihvorgang() {
        AusleihvorgangTO selectedAusleihvorgangTO = HauptmenueService.getAusleihvorgangPflegen().getSelectedAusleihvorgang();

        if (selectedAusleihvorgangTO != null) {
            teilnehmerComboBox.setValue(selectedAusleihvorgangTO.getTeilnehmerId());
            fahrzeugComboBox.setValue(selectedAusleihvorgangTO.getFahrzeugId());

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

            // Überprüfen Sie das tatsächliche Format des Datums in selectedAusleihvorgangTO
            System.out.println("Startdatum: " + selectedAusleihvorgangTO.getStartdatum());
            System.out.println("Enddatum: " + selectedAusleihvorgangTO.getEnddatum());

            // Versuchen Sie, das Datum zu parsen
            try {
                LocalDateTime startDatum = LocalDateTime.parse(selectedAusleihvorgangTO.getStartdatum(), dateTimeFormatter);
                LocalDateTime endDatum = LocalDateTime.parse(selectedAusleihvorgangTO.getEnddatum(), dateTimeFormatter);

                // Wenn das Parsen erfolgreich war, setzen Sie das Datum in den Feldern
                startdatumPicker.setLocalDateTime(startDatum);
                enddatumPicker.setLocalDateTime(endDatum);
            } catch (Exception e) {
                // Wenn das Parsen fehlschlägt, geben Sie eine Fehlermeldung aus
                System.out.println("Fehler beim Parsen des Datums: " + e.getMessage());
            }


            gefahreneKilometerTextField.setText(String.valueOf(selectedAusleihvorgangTO.getGefahreneKilometer()));
        }
    }

    @FXML
    public void speichernButtonClicked(ActionEvent actionEvent) throws Exception, AnwendungskernException, awk.teilnehmerverwaltung.AnwendungskernException {
        // Retrieve the selected AusleihvorgangTO object
        AusleihvorgangTO selectedAusleihvorgangTO = HauptmenueService.getAusleihvorgangPflegen().getSelectedAusleihvorgang();

        if (selectedAusleihvorgangTO != null) {
            // Update the AusleihvorgangTO object's properties with the values from the UI fields
            selectedAusleihvorgangTO.setTeilnehmerId((Long) teilnehmerComboBox.getValue());
            selectedAusleihvorgangTO.setFahrzeugId((Long) fahrzeugComboBox.getValue());
            selectedAusleihvorgangTO.setStartdatum(startdatumPicker.getLocalDateTime().toString());
            selectedAusleihvorgangTO.setEnddatum(enddatumPicker.getLocalDateTime().toString());
            selectedAusleihvorgangTO.setGefahreneKilometer(Integer.parseInt(gefahreneKilometerTextField.getText()));

            // Set the status of the AusleihvorgangTO object to "Abgeschlossen"
            selectedAusleihvorgangTO.setAbgeschlossen("Y");

            // Call a method from HauptmenueService to save the updated AusleihvorgangTO object
            HauptmenueService.getAusleihvorgangPflegen().ausleihvorgangAbschliessen(selectedAusleihvorgangTO);

            // Fahrzeugobjekt laden
            Long fahrzeugId = selectedAusleihvorgangTO.getFahrzeugId();
            FahrzeugTO fahrzeugTO = HauptmenueService.getFahrzeugSuchen().fahrzeugSuchenById(fahrzeugId);

            // Kilometerstand des Fahrzeugs aktualisieren
            HauptmenueService.getFahrzeugAendern().fahrzeugAendern(fahrzeugTO);

            // Display a success message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ausleihvorgang abschließen");
            alert.setHeaderText("Ausleihvorgang erfolgreich abgeschlossen!");
            alert.showAndWait();

            // Navigate back to the AusleihvorgangAbschliessen screen
            myController.setScreen(ui.menue.Hauptmenue.AUSLEIHVORGANG_ABSCHLIESSEN_SCREEN);
        }
    }

    @FXML
    public void zurueckButtonClicked(ActionEvent actionEvent) throws AnwendungskernException, awk.teilnehmerverwaltung.AnwendungskernException {
        myController.setScreen(ui.menue.Hauptmenue.AUSLEIHVORGANG_ABSCHLIESSEN_SCREEN);
    }
}
