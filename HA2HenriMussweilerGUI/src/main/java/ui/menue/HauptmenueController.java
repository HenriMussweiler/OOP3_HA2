package ui.menue;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class HauptmenueController implements Initializable, ControlledScreen{

    @FXML
    Button fuhrparkverwaltungButton;

    @FXML
    Button teilnehmerverwaltungButton;

    @FXML
    Button ausleihvorgangButton;

    @FXML
    Button beendenButton;

    private ScreensController myController;

    private HauptmenueService awkService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        awkService = new HauptmenueService();
    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    @Override
    public void initData() {

    }

    @FXML
    public void fuhrparkverwaltungButtonClicked(ActionEvent actionEvent) {
        myController.setScreen(Hauptmenue.FVW_SCREEN);
    }

    @FXML
    public void teilnehmerverwaltungButtonClicked(ActionEvent actionEvent) {
        myController.setScreen(Hauptmenue.TVW_SCREEN);
    }

    @FXML
    public void ausleihvorgangButtonClicked(ActionEvent actionEvent) {
        myController.setScreen(Hauptmenue.AVW_SCREEN);
    }

    @FXML
    public void beendenButtonClicked(ActionEvent actionEvent) {
        //Abfrage ob wirklich beendet werden soll mit einer Confirmation
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Beenden");
        alert.setHeaderText("Beenden");
        alert.setContentText("Wollen Sie das Programm wirklich beenden?");
        alert.showAndWait();

        //Wenn OK gedrückt wurde, wird das Programm beendet
        if (alert.getResult().getText().equals("OK")){
            System.exit(0);
        }
    }


}
