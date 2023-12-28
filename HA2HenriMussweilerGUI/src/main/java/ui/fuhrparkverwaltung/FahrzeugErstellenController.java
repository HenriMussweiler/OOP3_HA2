package ui.fuhrparkverwaltung;

import awk.fuhrparkverwaltung.AnwendungskernException;
import awk.fuhrparkverwaltung.entity.internal.Fahrzeug;
import awk.fuhrparkverwaltung.entity.internal.SharingStandort;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import ui.menue.ControlledScreen;
import ui.menue.HauptmenueService;
import ui.menue.ScreensController;

import java.net.URL;
import java.util.List;
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
    public void initData() {
        initComboBoxes();
    }

    private void initComboBoxes() {
        initKraftstoffComboBox();
        initGetriebeComboBox();
//        initSharingComboBox();
    }

    private void initSharingComboBox() {
        //TODO: SharingStandortService -- Factory und so weiter
//        List<SharingStandort> sharingStandortList = HauptmenueService.getFahrzeugePflegen().getSharingStandortService().findAll();
//        ObservableList<SharingStandort> sharingStandorte = FXCollections.observableArrayList(sharingStandortList);

//        sharingComboBox.setCellFactory(new Callback<>() {
//            @Override
//            public ListCell<SharingStandort> call(ListView<SharingStandort> param) {
//                return new ListCell<>() {
//                    @Override
//                    protected void updateItem(SharingStandort sharingStandort, boolean empty) {
//                        super.updateItem(sharingStandort, empty);
//
//                        if (sharingStandort == null || empty) {
//                            setText(null);
//                        } else {
//                            setText(sharingStandort.getStandortName());
//                        }
//                    }
//                };
//            }
//        });

//        sharingComboBox.setConverter(new StringConverter<SharingStandort>() {
//            @Override
//            public String toString(SharingStandort object) {
//                return object == null ? null : object.getStandortName();
//            }
//
//            @Override
//            public SharingStandort fromString(String string) {
//                return null;
//            }
//        });

//        sharingComboBox.setItems(sharingStandorte);
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
            SharingStandort sharingStandort = (SharingStandort) sharingComboBox.getValue();

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
    public void zurueckButtonClicked(ActionEvent actionEvent) {
        myController.setScreen(ui.menue.Hauptmenue.FVW_SCREEN);
    }
}
