package ui.ausleihvorgang;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import ui.menue.ControlledScreen;
import ui.menue.ScreensController;

import java.net.URL;
import java.util.ResourceBundle;

public class AusleihvorgangErstellenController implements Initializable, ControlledScreen {

    @FXML
    private Button reservierenButton;

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
    public void reservierenButtonClicked(ActionEvent actionEvent) {
    }

    @FXML
    public void zurueckButtonClicked(ActionEvent actionEvent) {
        myController.setScreen(ui.menue.Hauptmenue.AVW_SCREEN);
    }
}
