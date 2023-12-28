package ui.teilnehmerverwaltung;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import ui.menue.ControlledScreen;
import ui.menue.ScreensController;

import java.net.URL;
import java.util.ResourceBundle;

public class TeilnehmerverwaltungController implements Initializable, ControlledScreen {

    @FXML
    private Button teilnehmerErstellenButton;

    @FXML
    private Button teilnehmerAendernButton;

    @FXML
    private Button rechnungButton;

    @FXML
    private Button zurueckButton;

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

    }

    @FXML
    public void teilnehmerErstellenButtonClicked(ActionEvent actionEvent) {
        myController.setScreen(ui.menue.Hauptmenue.TEILNEHMER_ERSTELLEN_SCREEN);
    }

    @FXML
    public void teilnehmerAendernButtonClicked(ActionEvent actionEvent) {
        myController.setScreen(ui.menue.Hauptmenue.TEILNEHMER_AENDERN_SCREEN);
    }

    @FXML
    public void rechnungButtonClicked(ActionEvent actionEvent) {
        myController.setScreen(ui.menue.Hauptmenue.RECHNUNG_SCREEN);
    }

    @FXML
    public void zurueckButtonClicked(ActionEvent actionEvent) {
        myController.setScreen(ui.menue.Hauptmenue.MAIN_SCREEN);
    }
}
