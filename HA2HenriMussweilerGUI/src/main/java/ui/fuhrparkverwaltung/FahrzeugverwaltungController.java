package ui.fuhrparkverwaltung;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import ui.menue.ControlledScreen;
import ui.menue.Hauptmenue;
import ui.menue.ScreensController;

import java.net.URL;
import java.util.ResourceBundle;

public class FahrzeugverwaltungController implements Initializable, ControlledScreen {

    @FXML
    private Button fahrzeugAnlegenButton;

    @FXML
    private Button fahrzeugAendernButton;

    @FXML
    private Button fahrzeugLoeschenButton;

    @FXML
    private Button standortButton;

    @FXML
    private Button zurueckButton;

    private ScreensController myController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    @Override
    public void initData() {

    }

    @FXML
    public void fahrzeugAnlegenButtonClicked(ActionEvent actionEvent) {
        myController.setScreen(Hauptmenue.FAHRZEUG_ERSTELLEN_SCREEN);
    }

    @FXML
    public void fahrzeugAendernButtonClicked(ActionEvent actionEvent) {
        myController.setScreen(Hauptmenue.FAHRZEUG_AENDERN_SCREEN);
    }

    @FXML
    public void fahrzeugLoeschenButtonClicked(ActionEvent actionEvent) {
        myController.setScreen(Hauptmenue.FAHRZEUG_LOESCHEN_SCREEN);
    }

    @FXML
    public void standortButtonClicked(ActionEvent actionEvent) {
        myController.setScreen(Hauptmenue.SHARING_STANDORT_SCREEN);
    }

    @FXML
    public void zurueckButtonClicked(ActionEvent actionEvent) {
        myController.setScreen(ui.menue.Hauptmenue.MAIN_SCREEN);
    }
}