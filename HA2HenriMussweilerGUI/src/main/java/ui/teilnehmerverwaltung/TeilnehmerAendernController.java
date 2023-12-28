package ui.teilnehmerverwaltung;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import ui.menue.ControlledScreen;
import ui.menue.ScreensController;

import java.net.URL;
import java.util.ResourceBundle;

public class TeilnehmerAendernController implements Initializable, ControlledScreen {

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
    public void zurueckButtonClicked(ActionEvent actionEvent) {
        myController.setScreen(ui.menue.Hauptmenue.TVW_SCREEN);
    }

    @FXML
    public void teilnehmerAngeklickt(MouseEvent mouseEvent) {
        myController.setScreen(ui.menue.Hauptmenue.TEILNEHMER_AENDERN_ERWEITERUNG_SCREEN);
    }
}
