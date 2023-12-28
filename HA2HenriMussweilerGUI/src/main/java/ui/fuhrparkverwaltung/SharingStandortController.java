package ui.fuhrparkverwaltung;

import awk.fuhrparkverwaltung.AnwendungskernException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ui.menue.ControlledScreen;
import ui.menue.HauptmenueService;
import ui.menue.ScreensController;

import java.net.URL;
import java.util.ResourceBundle;

public class SharingStandortController implements Initializable, ControlledScreen {

    @FXML
    private Button speichernButton;

    @FXML
    private Button zurueckButton;

    @FXML
    private TextField nameField;

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
    public void speichernButtonClicked(ActionEvent actionEvent) throws AnwendungskernException {
        String name = nameField.getText();
        if (name != null && !name.isEmpty()) {
            HauptmenueService.getStandortErstellen().standortErstellen(name);
        }
    }

    @FXML
    public void zurueckButtonClicked(ActionEvent actionEvent) {
        myController.setScreen(ui.menue.Hauptmenue.FVW_SCREEN);
    }
}
