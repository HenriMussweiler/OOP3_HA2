package ui.ausleihvorgang;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import ui.menue.ControlledScreen;
import ui.menue.ScreensController;

import java.net.URL;
import java.util.ResourceBundle;

public class AusleihvorgangAbschliessenController implements Initializable, ControlledScreen {

    @FXML
    private Button aendernButton;

    @FXML
    private Button zurueckButton;

    @FXML
    private ComboBox<String> ausleihvorgangComboBox;

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
        myController.setScreen(ui.menue.Hauptmenue.AVW_SCREEN);
    }

    @FXML
    public void abschliessenButtonClicked(ActionEvent actionEvent) {
        myController.setScreen(ui.menue.Hauptmenue.AUSLEIHVORGANG_ABSCHLIESSEN_ERWEITERUNG_SCREEN);
    }
}
