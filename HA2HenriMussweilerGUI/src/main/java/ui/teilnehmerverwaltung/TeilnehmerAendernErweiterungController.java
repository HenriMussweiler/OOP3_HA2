package ui.teilnehmerverwaltung;

import awk.fuhrparkverwaltung.AnwendungskernException;
import awk.teilnehmerverwaltung.entity.TeilnehmerTO;
import awk.teilnehmerverwaltung.entity.internal.Teilnehmer;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import ui.menue.ControlledScreen;
import ui.menue.HauptmenueService;
import ui.menue.ScreensController;

import java.net.URL;
import java.util.ResourceBundle;

public class TeilnehmerAendernErweiterungController implements Initializable, ControlledScreen {

    @FXML
    private Button speichernButton;

    @FXML
    private Button zurueckButton;

    @FXML
    private TextField nameField;

    @FXML
    private TextField vornameField;

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
        populateTextFields();
    }

    private void populateTextFields() {
        //Teilnehmer laden
        TeilnehmerTO teilnehmerTO = HauptmenueService.getTeilnehmerAendern().getSelectedTeilnehmer();

        //Textfelder befüllen
        nameField.setText(teilnehmerTO.getName());
        vornameField.setText(teilnehmerTO.getVorname());
        strasseField.setText(teilnehmerTO.getStrasse());
        hausnummerField.setText(teilnehmerTO.getHausnummer());
        plzField.setText(teilnehmerTO.getPostleitzahl());
        ortField.setText(teilnehmerTO.getOrt());
        ibanField.setText(teilnehmerTO.getIban());
        mailField.setText(teilnehmerTO.getMail());
        telefonField.setText(teilnehmerTO.getTelefon());

    }

    @FXML
    public void speichernButtonClicked(ActionEvent actionEvent) {
        try {
            //Aktualisiere die Werte des ausgewählten Teilnehmers
            TeilnehmerTO teilnehmerTO = HauptmenueService.getTeilnehmerAendern().getSelectedTeilnehmer();
            teilnehmerTO.setName(nameField.getText());
            teilnehmerTO.setVorname(vornameField.getText());
            teilnehmerTO.setStrasse(strasseField.getText());
            teilnehmerTO.setHausnummer(hausnummerField.getText());
            teilnehmerTO.setPostleitzahl(plzField.getText());
            teilnehmerTO.setOrt(ortField.getText());
            teilnehmerTO.setIban(ibanField.getText());
            teilnehmerTO.setMail(mailField.getText());
            teilnehmerTO.setTelefon(telefonField.getText());

            //Teilnehmer aktualisieren
            HauptmenueService.getTeilnehmerAendern().teilnehmerAendern(teilnehmerTO);

            //Erfolgsmeldung
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Teilnehmer ändern");
            alert.setHeaderText("Teilnehmer erfolgreich geändert");
            alert.setContentText("Der Teilnehmer wurde erfolgreich geändert.");
            alert.showAndWait();

            //Zurück zum TeilnehmerAendernScreen
            myController.setScreen(ui.menue.Hauptmenue.TEILNEHMER_AENDERN_SCREEN);
        } catch (AnwendungskernException e) {
            throw new RuntimeException(e);
        } catch (awk.teilnehmerverwaltung.AnwendungskernException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void zurueckButtonClicked(ActionEvent actionEvent) throws AnwendungskernException, awk.teilnehmerverwaltung.AnwendungskernException {
        myController.setScreen(ui.menue.Hauptmenue.TEILNEHMER_AENDERN_SCREEN);
    }
}
