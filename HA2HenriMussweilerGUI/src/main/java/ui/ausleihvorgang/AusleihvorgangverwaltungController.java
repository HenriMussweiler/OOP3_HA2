package ui.ausleihvorgang;

import awk.fuhrparkverwaltung.AnwendungskernException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import ui.menue.ControlledScreen;
import ui.menue.Hauptmenue;
import ui.menue.ScreensController;

import java.net.URL;
import java.util.ResourceBundle;

public class AusleihvorgangverwaltungController implements Initializable, ControlledScreen {

    @FXML
    private Button ausleihvorgangAnlegenButton;

    @FXML
    private Button ausleihvorgangStornierenButton;

    @FXML
    private Button ausleihvorgangAbschliessenButton;

    @FXML
    private Button zurueckButton;

    @FXML
    private Button rechnungButton;

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
    public void ausleihvorgangAnlegenButtonClicked(ActionEvent actionEvent) throws AnwendungskernException, awk.teilnehmerverwaltung.AnwendungskernException {
        myController.setScreen(Hauptmenue.AUSLEIHVORGANG_ERSTELLEN_SCREEN);
    }

    @FXML
    public void ausleihvorgangStornierenButtonClicked(ActionEvent actionEvent) throws AnwendungskernException, awk.teilnehmerverwaltung.AnwendungskernException {
        myController.setScreen(Hauptmenue.AUSLEIHVORGANG_STORNIEREN_SCREEN);
    }

    @FXML
    public void ausleihvorgangAbschliessenButtonClicked(ActionEvent actionEvent) throws AnwendungskernException, awk.teilnehmerverwaltung.AnwendungskernException {
        myController.setScreen(Hauptmenue.AUSLEIHVORGANG_ABSCHLIESSEN_SCREEN);
    }

    @FXML
    public void rechnungButtonClicked(ActionEvent actionEvent) throws AnwendungskernException, awk.teilnehmerverwaltung.AnwendungskernException {
        myController.setScreen(Hauptmenue.RECHNUNG_SCREEN);
    }

    @FXML
    public void ZurueckButtonClicked(ActionEvent actionEvent) throws AnwendungskernException, awk.teilnehmerverwaltung.AnwendungskernException {
        myController.setScreen(ui.menue.Hauptmenue.MAIN_SCREEN);
    }
}
